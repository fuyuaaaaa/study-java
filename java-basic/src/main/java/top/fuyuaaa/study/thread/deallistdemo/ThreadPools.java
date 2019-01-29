package top.fuyuaaa.study.thread.deallistdemo;

import java.util.concurrent.*;

/**
 * @author: fuyuaaaaa
 * @creat: 2019-01-11 15:19
 */
public class ThreadPools {

    public static ExecutorService newCachedThreadPool(){
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

}
