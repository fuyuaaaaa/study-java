package top.fuyuaaa.study.thread.redislock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-29 13:36
 */
public class ZooDistributeLock {

    private ZkClient zkClient;
    private String name;
    private String currentLockPath;
    private CountDownLatch countDownLatch;
    private final ConcurrentMap<Thread, LockData> threadData;

    private static final String PARENT_LOCK_PATH = "/distribute_lock_name";

    public ZooDistributeLock(ZkClient zkClient, String name) {
        this.zkClient = zkClient;
        this.name = name;
        threadData = new ConcurrentHashMap<>();
        //判断父节点是否存在，不存在就创建
        if (!zkClient.exists(PARENT_LOCK_PATH)) {
            try {
                //创建唯一父节点
                zkClient.createPersistent(PARENT_LOCK_PATH);
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 解锁
     */
    public void unlock() {
        Thread currentThread = Thread.currentThread();
        LockData lockData = this.threadData.get(currentThread);
        if (lockData == null) {
            throw new IllegalMonitorStateException("You do not own top.fuyuaaa.study.netty.the lock: " + PARENT_LOCK_PATH);
        } else {
            int newLockCount = lockData.lockCount.decrementAndGet();
            if (newLockCount <= 0) {
                if (newLockCount < 0) {
                    throw new IllegalMonitorStateException("Lock count has gone negative for lock: " + PARENT_LOCK_PATH);
                } else {
                    try {
                        System.out.println("delete : " + currentLockPath);
                        zkClient.delete(currentLockPath);
                    } finally {
                        this.threadData.remove(currentThread);
                    }

                }
            }
        }
    }

    /**
     * 加锁
     */
    public void lock() {
        Thread currentThread = Thread.currentThread();
        LockData lockData = threadData.get(currentThread);
        //重入
        if (lockData != null) {
            lockData.lockCount.incrementAndGet();
        } else {
            //创建父节点下的临时顺序节点
            currentLockPath = zkClient.createEphemeralSequential(PARENT_LOCK_PATH + "/", System.currentTimeMillis());
            if (currentLockPath != null) {
                LockData newLockData = new LockData(currentThread, currentLockPath);
                this.threadData.put(currentThread, newLockData);
                //校验是否最小节点
                checkMinNode(currentLockPath);
            }
        }

    }


    private void checkMinNode(String lockPath) {
        //获取父节点下所有子节点
        List<String> children = zkClient.getChildren(PARENT_LOCK_PATH);
        Collections.sort(children);
        //当前节点的排序下标，index=0则最小，获取锁
        int index = children.indexOf(lockPath.substring(PARENT_LOCK_PATH.length() + 1));
        if (index == 0) {
            System.out.println(name + "：success");
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        } else {
            String waitPath = PARENT_LOCK_PATH + "/" + children.get(index - 1);
            //给前一个节点加监听（zk中的watch机制），等待前一个节点释放锁
            waitForLock(waitPath);
        }
    }


    private void waitForLock(String prevNodePath) {
        System.out.println(name + " current path :" + currentLockPath + "：fail add listener" + " wait path :" + prevNodePath);
        countDownLatch = new CountDownLatch(1);
        //监听前一节点的状态变化
        zkClient.subscribeDataChanges(prevNodePath, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                //之前节点失效后，重新检测本节点是否为最小的节点
                System.out.println("前一个节点失效");
                checkMinNode(currentLockPath);
            }
        });
        //再次判断节点是否失效
        if (!zkClient.exists(prevNodePath)) {
            return;
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch = null;
    }

    /**
     * 参照 Curator 中 InterProcessMutex.LockData 类
     */
    private static class LockData {
        final Thread owningThread;
        final String lockPath;
        final AtomicInteger lockCount;

        private LockData(Thread owningThread, String lockPath) {
            this.lockCount = new AtomicInteger(1);
            this.owningThread = owningThread;
            this.lockPath = lockPath;
        }
    }
}

class MyDistributedLockTest {

    private static final ExecutorService EXECUTOR_SERVICE =
            new ThreadPoolExecutor(10, 10, 10,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000),
                    //com.google.guava的ThreadFactoryBuilder
                    //
                    new ThreadFactoryBuilder().setNameFormat("zk-pool-%d").build());

    public static void main(String[] args) {

        ZkClient zk = new ZkClient("127.0.0.1:2181", 5 * 10000);

        for (int i = 0; i < 10; i++) {
            String name = "thread" + i;
            EXECUTOR_SERVICE.execute(() -> {
                ZooDistributeLock myDistributedLock = new ZooDistributeLock(zk, name);
                myDistributedLock.lock();
                try {
                    Thread.sleep(1000);
                    myDistributedLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myDistributedLock.unlock();
                myDistributedLock.unlock();
            });
        }
        EXECUTOR_SERVICE.shutdown();
    }

}
