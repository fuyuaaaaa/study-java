package fuyuaaa.shujujiegou.set;

import java.util.ArrayList;

/**
 * @Package_Name: PACKAGE_NAME
 * @Auther: fuyua
 * @Date: created in 13:32 2018/6/23
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;
    public BSTSet() {
        bst = new BST<>();
    }
    @Override
    public int getSize() {
        return bst.size();
    }
    @Override
    public boolean isEmpty(){
        return bst.isEmpty();
    }
    @Override
    public void add(E e){
        bst.add(e);
    }
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }
    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> word1 = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", word1)){
            System.out.println("Total words:" + word1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String word : word1)
                set1.add(word);
            System.out.println("Total different words:"+ set1.getSize());
        };

        System.out.println();
        System.out.println("a-tale-of-two-cities");
        ArrayList<String> word2 = new ArrayList<>();
        if (FileOperation.readFile("a-tale-of-two-cities.txt", word2)) {
            System.out.println("total words:" + word2.size());

            BSTSet<String> set2 = new BSTSet<>();
            for (String word : word2)
                set2.add(word);
            System.out.println("different words:" + set2.getSize());
        }
    }
}
