package fuyuaaa.shujujiegou.map;

import fuyuaaa.shujujiegou.set.FileOperation;

import java.util.ArrayList;

/**
 * @Package_Name: map
 * @Auther: fuyua
 * @Date: created in 16:49 2018/6/23
 */
public class Main {

    public static double test(Map<String, Integer> map, String filename) {
        long startTime = System.nanoTime();
        System.out.println(filename);
        ArrayList<String> words  = new ArrayList<>();
        if (FileOperation.readFile(filename,words)){
            System.out.println("total words:" + words.size());

            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else map.add(word,1);
            }
        System.out.println("total different words:" + map.getSize());
        System.out.println("pride:" + map.get("pride"));
        System.out.println("prejudice:" + map.get("prejudice"));
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime)/1000000000.0;
        return time;
    }

    public static void main(String[] args) {
        String filename = "pride-and-prejudice.txt";
        BSTMap<String , Integer> bstMap = new BSTMap<>();
        double time1 = test(bstMap, filename);
        System.out.println(time1);


        LinkedListMap<String , Integer> linkedListMap = new LinkedListMap<>();
        double time2 = test(linkedListMap, filename);
        System.out.println(time2);
    }
}
