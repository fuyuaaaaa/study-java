package top.fuyuaaa.provider;

import top.fuyuaaa.api.TestService;

/**
 * @author: fuyuaaaaa
 * @creat: 2018-12-12 23:36
 */
public class TestServiceImpl implements TestService {
    public String demo(String words) {
        System.out.println(System.currentTimeMillis() + words);
        return "fuyu:hello" + words;
    }
}
