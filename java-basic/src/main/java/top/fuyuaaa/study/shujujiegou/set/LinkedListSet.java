package top.fuyuaaa.study.shujujiegou.set;

import java.util.ArrayList;

/**
 * @Package_Name: PACKAGE_NAME
 * @Auther: fuyua
 * @Date: created in 14:15 2018/6/23
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;

    public LinkedListSet(){
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> word1 = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", word1)){
            System.out.println("Total words:" + word1.size());

            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String word : word1)
                set1.add(word);
            System.out.println("Total different words:"+ set1.getSize());
        };

        System.out.println();
        System.out.println("a-tale-of-two-cities");
        ArrayList<String> word2 = new ArrayList<>();
        if (FileOperation.readFile("a-tale-of-two-cities.txt", word2)) {
            System.out.println("total words:" + word2.size());

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for (String word : word2)
                set2.add(word);
            System.out.println("different words:" + set2.getSize());
        }
    }
}
