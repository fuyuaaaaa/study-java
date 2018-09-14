package fuyuaaa.shujujiegou.set;

/**
 * @Package_Name: PACKAGE_NAME
 * @Auther: fuyua
 * @Date: created in 13:30 2018/6/23
 */
public interface Set<E> {

    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
