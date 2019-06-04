package top.fuyuaaa.study.java8.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author: fuyuaaa
 * @creat: 2019-06-04 16:43
 */
public class OptionalTest {
    public static void main(String[] args) {
        User user1 = new User("fuyu", 22);
        User user2 = new User();
        System.out.println(Optional.of(user1).map(User::getName));
        System.out.println(Optional.of(user2).map(User::getName));

        System.out.println(Optional.ofNullable(user1.getName()).isPresent());
        System.out.println(Optional.ofNullable(user2.getName()).isPresent());

        System.out.println(Optional.ofNullable(user1.getName()).get());
        System.out.println(Optional.ofNullable(user2.getName()).orElse("I am null!"));

        System.out.println(Optional.of(user1).filter(user -> user.getAge() > 23).map(User::getName));

        user2.setName(Optional.ofNullable(user1.getName()).orElse("orElse"));
        System.out.println(user2);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    String name;
    Integer age;
}
