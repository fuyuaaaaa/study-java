package top.fuyuaaa.study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: fuyuaaaaa
 * @creat: 2019-01-14 19:18
 */
public class StreamTest {

    public static void main(String[] args) {
        Student student1 = new Student(1, "1");
        Student student2 = new Student(1, "1");
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        Map<Integer, Student> map = list.stream().distinct().collect(Collectors.toMap(Student::getId, student -> student, (old, n) -> old));

    }
}

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
