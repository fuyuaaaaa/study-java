package java.top.fuyuaaa.study_java.thread;

/**
 * @author: fuyuaaaaa
 * @description: 死锁demo
 * @program: study-java
 * @creat: 2018-10-31 14:46
 **/
public class DeadLockDemo {
    static StringBuffer sb1 = new StringBuffer();
    static StringBuffer sb2 = new StringBuffer();

    public static void main(String[] args){

        new Thread(){
            public void run(){
                synchronized (sb1){
                    try {
                        Thread.currentThread().sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sb1.append("A");
                    synchronized (sb2){
                        sb2.append("B");
                        System.out.println(sb1);
                        System.out.println(sb2);
                    }
                }
            }
        }.start();
        new Thread(){
            public void run(){
                synchronized (sb2){
                    sb1.append("C");
                    synchronized (sb1){
                        sb2.append("D");
                        System.out.println(sb1);
                        System.out.println(sb2);
                    }
                }
            }
        }.start();
    }
}
