package top.fuyuaaa.study.thread.redislock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-29 13:36
 */
@Slf4j
public class ZooDistributeLock {


    private ZkClient zkClient;
    private String name;
    private String currentLockPath;
    private CountDownLatch countDownLatch;

    private static final String PARENT_LOCK_PATH = "/distribute_lock_name";

    public ZooDistributeLock(ZkClient zkClient, String name) {
        this.zkClient = zkClient;
        this.name = name;
    }

    //加锁
    public void lock() {
        //判断父节点是否存在，不存在就创建
        if (!zkClient.exists(PARENT_LOCK_PATH)) {
            try {
                //创建唯一父节点
                zkClient.createPersistent(PARENT_LOCK_PATH);
            } catch (Exception ignored) {
            }
        }
        //创建父节点下的临时顺序节点
        currentLockPath = zkClient.createEphemeralSequential(PARENT_LOCK_PATH + "/", System.currentTimeMillis());
        //校验是否最小节点
        checkMinNode(currentLockPath);
    }

    //解锁
    public void unlock() {
        System.out.println("delete : " + currentLockPath);
        zkClient.delete(currentLockPath);
    }


    private boolean checkMinNode(String lockPath) {
        //获取父节点下所有子节点
        List<String> children = zkClient.getChildren(PARENT_LOCK_PATH);
        Collections.sort(children);
        //当前节点的排序下标，index=0则最小，获取锁
        int index = children.indexOf(lockPath.substring(PARENT_LOCK_PATH.length() + 1));
        if (index == 0) {
            log.info(name + "：success");
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
            return true;
        } else {
            String waitPath = PARENT_LOCK_PATH + "/" + children.get(index - 1);
            //给前一个节点加监听（zk中的watch机制），等待前一个节点释放锁
            waitForLock(waitPath);
            return false;
        }
    }


    private void waitForLock(String prevNodePath) {
        log.info(name + " current path :" + currentLockPath + "：fail add listener" + " wait path :" + prevNodePath);
        countDownLatch = new CountDownLatch(1);
        //监听前一节点的状态变化
        zkClient.subscribeDataChanges(prevNodePath, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                //之前节点失效后，重新检测本节点是否为最小的节点
                log.info("前一个节点失效");
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
}

class MyDistributedLockTest {

    private static final ExecutorService EXECUTOR_SERVICE =
            new ThreadPoolExecutor(10, 10, 10,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                    //com.google.guava的ThreadFactoryBuilder
                    new ThreadFactoryBuilder().setNameFormat("zk-pool-%d").build());

    public static void main(String[] args) {

        ZkClient zk = new ZkClient("127.0.0.1:2181", 5 * 10000);

        for (int i = 0; i < 20; i++) {
            String name = "thread" + i;
            EXECUTOR_SERVICE.execute(() -> {
                ZooDistributeLock myDistributedLock = new ZooDistributeLock(zk, name);
                myDistributedLock.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myDistributedLock.unlock();
            });
        }
        EXECUTOR_SERVICE.shutdown();
    }

}
