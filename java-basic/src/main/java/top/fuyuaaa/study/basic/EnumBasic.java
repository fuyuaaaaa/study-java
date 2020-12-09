package top.fuyuaaa.study.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;

/**
 * 枚举类尝试 
 * @author : fuyuaaa
 * @date : 2020-02-02 15:50
 */
public class EnumBasic {

    public static void main(String[] args) {
        // return enum name
        System.out.println(TestEnum.APPLE.name());
        System.out.println(TestEnum.BANANA.name());

        // order
        System.out.println(TestEnum.APPLE.ordinal());
        System.out.println(TestEnum.BANANA.ordinal());

        // return enum
        System.out.println(TestEnum.valueOf("APPLE"));
        System.out.println(TestEnum.valueOf("BANANA"));

        // test enum map
        // 可以用于groupBy
        EnumMap<TestEnum, AtomicInteger> testEnumMap = new EnumMap<>(TestEnum.class);
        testEnumMap.put(TestEnum.APPLE, new AtomicInteger(1));
        testEnumMap.put(TestEnum.BANANA, new AtomicInteger(2));
        System.out.println(testEnumMap.get(TestEnum.APPLE).incrementAndGet());
        System.out.println(testEnumMap.get(TestEnum.BANANA).incrementAndGet());

        // 简单策略
        TestEnum.APPLE.selfIntroduction();
        TestEnum.BANANA.selfIntroduction();

        // jackson enum
        // override toString method
        System.out.println(TestEnum.APPLE);
        System.out.println(TestEnum.BANANA);

    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    enum TestEnum {
        /**
         *
         */
        APPLE("苹果", "apple") {
            @Override
            public void selfIntroduction() {
                System.out.println("我是苹果！");
            }
        },
        BANANA("香蕉", "banana") {
            @Override
            public void selfIntroduction() {
                System.out.println("我是香蕉！");
            }
        },
        ;
        @Getter
        String desc;
        @Getter
        String code;

        TestEnum(String desc, String code) {
            this.desc = desc;
            this.code = code;
        }

        @SuppressWarnings("unused")
        public abstract void selfIntroduction();


        @Override
        public String toString() {
            try {
                return new ObjectMapper().writeValueAsString(this);
            } catch (JsonProcessingException e) {
                System.out.println("error");
                return null;
            }
        }
    }
}
