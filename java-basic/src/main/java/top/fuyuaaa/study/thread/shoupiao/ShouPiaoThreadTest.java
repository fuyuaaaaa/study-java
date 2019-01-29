package top.fuyuaaa.study.thread.shoupiao;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: study-java
 * @creat: 2018-10-31 14:29
 **/

//窗口售票问题
class ShoupiaoThreadDemo implements Runnable {
    int ticket = 100;

    @Override
    public void run() {
        while (true) {
            synchronized(this) {
                if (ticket > 0) {
                    try {
                        Thread.currentThread().sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+ "售票，票号为：" + ticket--);
                }
            }
        }
    }
}

public class ShouPiaoThreadTest {
    public static void main(String[] args) {
        ShoupiaoThreadDemo s = new ShoupiaoThreadDemo();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        Thread t3 = new Thread(s);
        t1.start();
        t2.start();
        t3.start();
    }
}
