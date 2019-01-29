package java.top.fuyuaaa.study_java.shujujiegou.trie;

import fuyuaaa.shujujiegou.set.BSTSet;
import fuyuaaa.shujujiegou.set.FileOperation;

import java.util.ArrayList;

/**
 * @Auther: fuyuaaaaa
 * @Description: trie main
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-13 21:57
 */
public class TrieMain {
    public static void main(String[] args) {
        System.out.println("pride-and-prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words)
                set.add(word);

            for (String word : words)
                set.contains(word);

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("total different words:" + set.getSize());
            System.out.println("BSTSet:" + time + "s");

            //
            startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words)
                trie.add(word);

            for (String word : words)
                trie.contains(word);

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;

            System.out.println("total different words:" + trie.getSize());
            System.out.println("trie:" + time + "s");
        }
    }
}
