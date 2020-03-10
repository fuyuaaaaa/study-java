package top.fuyuaaa.study.guavacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author : fuyuaaa
 * @date : 2020-03-03 11:24
 */
public class GuavaCacheStudy {

    static Cache<String, Object> cache = CacheBuilder.newBuilder()
        .initialCapacity(8)
        .maximumSize(32)
        .expireAfterAccess(3, TimeUnit.SECONDS)
        .build();

    public static void main(String[] args) throws InterruptedException {
        cache.put("test",123);
        Object test = cache.getIfPresent("test");
        System.out.println(test);
        Thread.sleep(5 * 1000);
        Object test1 = cache.getIfPresent("test");
        System.out.println(test1);
    }
}
