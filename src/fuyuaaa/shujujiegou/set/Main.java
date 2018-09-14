package fuyuaaa.shujujiegou.set;

import java.util.ArrayList;

public class Main {
    private static double testSet(Set<String> set, String filename) {
        long startTime = System.nanoTime();
        System.out.println(filename);
        ArrayList<String> word1 = new ArrayList<>();
        if (FileOperation.readFile(filename, word1)){
            System.out.println("Total words:" + word1.size());
            for (String word : word1)
                set.add(word);
            System.out.println("Total different words:"+ set.getSize());
        };
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        String filename = "pride-and-prejudice.txt";
        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet,filename);
        System.out.println("set.BST set.Set" + time1 + "s");

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, filename);
        System.out.println("set.LinkedList set.Set" + time2 + "s");
    }
}
