package top.fuyuaaa.study.thread.aqssources;


/**
 * AQS.Node类 源码
 * @author: fuyuaaa
 * @creat: 2019-02-14 16:36
 */
public class Node {
    /**
     * 共享模式 
     */
    static final Node SHARED = new Node();

    /**
     * 独占模式 
     */
    static final Node EXCLUSIVE = null;

    /**
     * 场景：当该线程等待超时或者被中断，需要从同步队列中取消等待，则该线程被置1，即被取消（这里该线程在取消之前是等待状态）。
     * 节点进入了取消状态则不再变化。
     */
    static final int CANCELLED = 1;

    /**
     * 标识当前节点的后继节点需要唤醒，通常是在 独占模式下使用
     * 场景：后继的节点处于等待状态，当前节点的线程如果释放了同步状态或者被取消（当前节点状态置为-1），
     *      将会通知后继节点，使后继节点的线程得以运行
     */
    static final int SIGNAL = -1;
    
    /**
     * 场景：节点处于等待队列中，节点线程等待在Condition上，当其他线程对Condition调用了signal()方法后，
     *      该节点从等待队列中转移到同步队列中，加入到对同步状态的获取中
     */
    static final int CONDITION = -2;
    
    /**
     * 场景：表示下一次的共享状态会被无条件的传播下去
     */
    static final int PROPAGATE = -3;
    
    /**
     * 该字段只取上面四种值 
     */
    volatile int waitStatus;
    
    /**
     * 节点在 Sync Queue 里面时的前继节点(主要来进行 skip CANCELLED 的节点)
     * 注意: 根据 addWaiter方法:
     *      1. prev节点在队列里面, 则 prev != null 肯定成立
     *      2. prev != null 成立, 不一定 node 就在 Sync Queue 里面
     */
    volatile Node prev;
    
    /**
     * Node 在 Sync Queue 里面的后继节点, 主要是在release lock 时进行后继节点的唤醒
     * 而后继节点在前继节点上打上 SIGNAL 标识, 来提醒他 release lock 时需要唤醒
     */
    volatile Node next;
    
    /**
     * 获取到同步状态的线程 
     */
    volatile Thread thread;

    /**
     * 作用分成两种:
     *  1. 在 Sync Queue 里面, nextWaiter用来判断节点是 共享模式, 还是独占模式
     *  2. 在 Condition queue 里面, 节点主要是链接且后继节点 (Condition queue是一个单向的, 不支持并发的 list)
     */
    Node nextWaiter;

    /**
     * 判断当前节点是否是共享模式 
     */
    final boolean isShared() {
        return this.nextWaiter == SHARED;
    }

    /**
     * 获取前继节点 
     */
    final Node predecessor() throws NullPointerException {
        Node var1 = this.prev;
        if (var1 == null) {
            throw new NullPointerException();
        } else {
            return var1;
        }
    }

    Node() {
    }

    /**
     * 用于Sync队列里
     */
    Node(Thread var1, Node var2) {      // Used by addWaiter
        this.nextWaiter = var2;
        this.thread = var1;
    }

    /**
     * 用于Condition队列里
     */
    Node(Thread var1, int var2) {       // Used by Condition
        this.waitStatus = var2;
        this.thread = var1;
    }
}
