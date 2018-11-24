package fuyuaaa.shujujiegou;

import java.util.Scanner;

/**
 * 按顺序打印abcd
 */
public class Test {
    private static String charValue = "A";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.close();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < num;) {
                    synchronized(charValue){
                        if (charValue.equals("A")) {
                            System.out.print(charValue);
                            charValue = "B";
                            i++;
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < num; ) {
                    synchronized(charValue){
                        if (charValue.equals("B")) {
                            System.out.print(charValue);
                            charValue = "C";
                            i++;
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < num; ) {
                    synchronized(charValue){
                        if (charValue.equals("C")) {
                            System.out.print(charValue);
                            charValue = "D";
                            i++;
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < num; ) {
                    synchronized(charValue){
                        if (charValue.equals("D")) {
                            System.out.print(charValue);
                            charValue = "A";
                            i++;
                        }
                    }
                }
            }
        }).start();

    }
}
