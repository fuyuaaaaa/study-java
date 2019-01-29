package java.top.fuyuaaa.study_java.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: fuyuaaaaa
 * @creat: 2019-01-04 18:05
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(5, 10, 300, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        Runnable run = () -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "正在执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 15; i++) {
            service.execute(run);
        }
        //这里一定要做关闭
        service.shutdown();
    }

}
