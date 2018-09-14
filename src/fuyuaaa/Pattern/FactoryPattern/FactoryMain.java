package fuyuaaa.Pattern.FactoryPattern;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: StudyDemo
 * @creat: 2018-07-27 10:00
 **/
public class FactoryMain {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Circle circle = (Circle) factory.getClass(Circle.class);
        circle.draw();

        Triangle triangle = (Triangle)factory.getClass(Triangle.class);
        triangle.draw();
    }
}
