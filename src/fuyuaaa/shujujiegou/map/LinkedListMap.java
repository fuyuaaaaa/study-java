package fuyuaaa.shujujiegou.map;



import fuyuaaa.shujujiegou.set.FileOperation;

import java.util.ArrayList;

/**
 * @Package_Name: map
 * @Auther: fuyua
 * @Date: created in 16:53 2018/6/23
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }
    @Override
    public int getSize(){
        return size;
    }
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null){
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else
            node.value = value;

    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null)
            throw new IllegalArgumentException(key + "donsn't exist!");
        node.value = newValue;
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    private class Node{
        public K key;
        public V value;
        public Node next;
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public Node(K key) {
            this(key, null, null);
        }
        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }


    public static void main(String[] args) {
        System.out.println("pride-and-prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("pride-and-prejudice word size" + words.size());
            long startTime = System.nanoTime();
            LinkedListMap<String,Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            long endTime = System.nanoTime();
            double time = (endTime - startTime)/1000000000.0;
            System.out.println(time);
            System.out.println("different words size:"+ map.getSize());
            System.out.println("frequendcy of pride:"+map.get("pride"));
            System.out.println("frequendcy of prejudice:"+map.get("prejudice"));
        }
    }
}
