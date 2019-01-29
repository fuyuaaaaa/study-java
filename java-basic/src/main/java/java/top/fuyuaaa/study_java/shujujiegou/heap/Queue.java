package java.top.fuyuaaa.study_java.shujujiegou.heap;

public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
