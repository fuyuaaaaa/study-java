package java.top.fuyuaaa.study_java.spring.ioc;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: StudyDemo
 * @creat: 2018-08-11 16:50
 **/
public class Car {
    private String name;
    private String length;
    private String width;
    private String height;
    private Wheel wheel;

    public Car() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }
}