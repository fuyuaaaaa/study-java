package java.top.fuyuaaa.study_java.thread.tongxin;

/**
 * @author: fuyuaaaaa
 * @description: 线程通信demo
 * @program: study-java
 * @creat: 2018-10-31 15:17
 **/

class PrintNumTread implements Runnable {
    int num = 100;

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
                notify();
                if (num > 0) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + num--);
                } else break;

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

public class PrintNumDemo {
    public static void main(String[] args) {
        PrintNumTread p = new PrintNumTread();
        Thread thread1 = new Thread(p);
        Thread thread2 = new Thread(p);
        thread1.setName("甲");
        thread2.setName("乙");
        thread1.start();
        thread2.start();
    }

}
