package top.fuyuaaa.study.thread.redislock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-29 13:36
 */
public class ZooDistributeLock {


    private ZkClient zkClient;
    private String name;
    private String currentLockPath;
    private CountDownLatch countDownLatch;

    private static final String PARENT_LOCK_PATH = "/distribute_lock";

    public ZooDistributeLock(ZkClient zkClient, String name) {
        this.zkClient = zkClient;
        this.name = name;
    }

    //加锁
    public void lock() {
        //判断父节点是否存在，不存在就创建
        if (!zkClient.exists(PARENT_LOCK_PATH)) {
            try {
                //多个线程只会成功建立一次
                zkClient.createPersistent(PARENT_LOCK_PATH);
            } catch (Exception ignored) {
            }
        }
        //创建当前目录下的临时有序节点
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
        //获取当前目录下所有子节点
        List<String> children = zkClient.getChildren(PARENT_LOCK_PATH);
        Collections.sort(children);
        //当前节点的排序下标
        int index = children.indexOf(lockPath.substring(PARENT_LOCK_PATH.length() + 1));
        if (index == 0) {
            System.out.println(name + "：success");
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
            return true;
        } else {
            String waitPath = PARENT_LOCK_PATH + "/" + children.get(index - 1);
            //等待前一个节点释放的监听
            waitForLock(waitPath);
            return false;
        }
    }


    private void waitForLock(String prev) {
        System.out.println(name + " current path :" + currentLockPath + "：fail add listener" + " wait path :" + prev);
        countDownLatch = new CountDownLatch(1);
        zkClient.subscribeDataChanges(prev, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("prev node is done");
                checkMinNode(currentLockPath);
            }
        });
        if (!zkClient.exists(prev)) {
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


    public static void main(String[] args) {

        ZkClient zk = new ZkClient("127.0.0.1:2181", 5 * 10000);

        for (int i = 0; i < 20; i++) {

            String name = "thread" + i;
            Thread thread = new Thread(() -> {
                ZooDistributeLock myDistributedLock = new ZooDistributeLock(zk, name);
                myDistributedLock.lock();
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myDistributedLock.unlock();
            });
            thread.start();
        }

    }

}
