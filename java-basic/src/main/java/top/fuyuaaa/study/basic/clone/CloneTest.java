package top.fuyuaaa.study.basic.clone;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author : fuyuaaa
 * @date : 2020-05-20 15:57
 */
public class CloneTest implements Serializable{


    private static final long serialVersionUID = -5672223292355291443L;

    @Data
    public class Copy implements Cloneable, Serializable {
        private static final long serialVersionUID = 714074703415205971L;
        private String name;
        private int age;
        private Sex sex;


        /**
         * 浅拷贝
         */
        public Copy shallowClone() throws CloneNotSupportedException {
            return (Copy) super.clone();
        }

        /**
         * 深拷贝，把引用对象也clone一遍
         */
        public Copy deepClone() throws CloneNotSupportedException {
            Copy clone = (Copy) super.clone();
            clone.sex = sex.clone();
            return clone;
        }
    }

    @Data
    class Sex implements Cloneable, Serializable{
        private static final long serialVersionUID = 6170406648458262040L;
        String sex;
        @Override
        public Sex clone() throws CloneNotSupportedException {
            return (Sex)super.clone();
        }
    }

    /**
     * 浅拷贝
     */
    @Test
    public void ShallowCopy() throws Exception {
        Copy copy = new Copy();
        copy.name = "code";
        copy.age = 23;
        Sex sex = new Sex();
        sex.sex = "man";
        copy.sex = sex;

        Copy clone = copy.shallowClone();

        //age, name 不会影响原对象
        clone.age = 24;
        clone.name = "dance";
        //sex修改会影响原对象
        clone.sex.sex = "woman";

        System.out.println(JSON.toJSONString(copy));
        System.out.println(JSON.toJSONString(clone));
    }


    /**
     * 深拷贝，通过实现Cloneable来实现深拷贝
     */
    @Test
    public void deepCopy() throws Exception {
        Copy copy = new Copy();
        copy.name = "code";
        copy.age = 23;
        Sex sex = new Sex();
        sex.sex = "man";
        copy.sex = sex;

        Copy clone = copy.deepClone();

        //age, name 不会影响原对象
        clone.age = 24;
        clone.name = "dance";
        //sex修改也不会影响原对象
        clone.sex.sex = "woman";

        System.out.println(JSON.toJSONString(copy));
        System.out.println(JSON.toJSONString(clone));
    }

    @Test
    public void deepCopyBySerializable() throws Exception {
        Copy copy = new Copy();
        copy.name = "code";
        copy.age = 23;
        Sex sex = new Sex();
        sex.sex = "man";
        copy.sex = sex;

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        // 通过序列化实现深拷贝
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(bos);
        // 序列化以及传递这个对象
        oos.writeObject(copy);
        oos.flush();
        ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
        ois = new ObjectInputStream(bin);
        // 返回新的对象
        Copy clone = (Copy) ois.readObject();

        //age, name 不会影响原对象
        clone.age = 24;
        clone.name = "dance";
        //sex修改也不会影响原对象
        clone.sex.sex = "woman";

        System.out.println(JSON.toJSONString(copy));
        System.out.println(JSON.toJSONString(clone));
    }
}


