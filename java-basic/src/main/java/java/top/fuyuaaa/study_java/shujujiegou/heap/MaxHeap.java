package java.top.fuyuaaa.study_java.shujujiegou.heap;

/**
 * @Package_Name: heap
 * @Auther: fuyua
 * @Date: created in 13:02 2018/7/7
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);

    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
    //父节点索引
    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index 0 doesn't hava parent.");
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }

    //向堆中add
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int k){
        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //查看最大元素
    public E findMax(){
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("data is null");
        }
        return data.get(0);
    }

    //从堆中取出最大
    public E extractMax(){
        E ret = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int k){
        while(leftChild(k) < data.getSize()){
            int j  =leftChild(k);
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0)
                j++;
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            data.swap(k, j);
            k = j;
        }
    }

    //取出最大，替换成新 e
    public E replace(E e) {
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }


}
