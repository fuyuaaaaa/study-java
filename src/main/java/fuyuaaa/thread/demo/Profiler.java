package fuyuaaa.thread.demo;

import sun.java2d.cmm.Profile;

import java.util.concurrent.TimeUnit;

/**
 * @author: fuyuaaaaa
 * @creat: 2018-12-18 22:01
 * @description: ThreadLocal Demo
 */
public class Profiler {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static  final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final Long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        Thread.sleep(1);
        System.out.println(Profiler.end());
    }
}
