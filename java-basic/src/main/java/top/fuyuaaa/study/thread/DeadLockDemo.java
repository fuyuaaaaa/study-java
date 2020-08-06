package top.fuyuaaa.study.thread;

/**
 * @author: fuyuaaaaa
 * @description: 死锁demo
 * @program: study-java
 * @creat: 2018-10-31 14:46
 **/
public class DeadLockDemo {
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();


    public static void main (String[] args){
        new Thread(()->{
            synchronized(resource1) {
                try {
                    Thread.sleep(1000);
                } catch( Exception e) {

                }
                System.out.println("===");
                synchronized(resource2) {

                }
            }
        }).start();

        new Thread(()->{
            synchronized(resource2) {
                try {
                    Thread.sleep(1000);
                } catch( Exception e) {

                }
                System.out.println("===");
                synchronized(resource1) {

                }
            }
        }).start();

    }
}
