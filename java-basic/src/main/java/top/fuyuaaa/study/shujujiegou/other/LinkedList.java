package top.fuyuaaa.study.shujujiegou.other;

/**
 * @Package_Name: com.fuyu
 * @Auther: fuyua
 * @Date: created in 13:04 2018/5/22
 */
public class LinkedList<E> {

    private class Node {
        private E e;

        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        public Node(Node next) {
            this(null, next);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void add(int index, E e){
//        if (index < 0 || index >= size) {
//            throw new IllegalArgumentException("Add failed");
//        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;

        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size-1, e);
    }

    public void update(int index, E e) {
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public E remove(int index) {
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev =prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        return delNode.e;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null){
            res.append(cur + "-->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}

