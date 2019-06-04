package top.fuyuaaa.study.jvm;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author: fuyuaaa
 * @creat: 2019-06-04 11:20
 */
public class OOMDemo {
    public static void main(String[] args) {
        //OOM DEMO: Requested array size exceeds VM limit
//        List<User> userList = new ArrayList<>(Integer.MAX_VALUE);
        List<User> userList = new ArrayList<>();
        while (true) {
            userList.add(new User(UUID.randomUUID().toString()));
        }
    }
}

@AllArgsConstructor
class User {
    String name;
}
