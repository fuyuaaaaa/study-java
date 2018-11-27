package fuyuaaa.thread.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitNotifyDemo {
    static Object lock = new Object();
    static boolean flag = true;

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();

        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }


    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized(lock){
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true, wait@ "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread() + " flag is flase. running @ "
                 + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock. notify @ "
                 + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again. notify @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
