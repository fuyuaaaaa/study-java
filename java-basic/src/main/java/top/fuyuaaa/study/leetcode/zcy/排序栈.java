package top.fuyuaaa.study.leetcode.zcy;

import lombok.val;

import java.util.Stack;

/**
 * @author : fuyuaaa
 * @date : 2020-07-13 17:52
 */
public class 排序栈 {


    private Stack<Integer> dataStack;
    private Stack<Integer> helpStack;

    public 排序栈() {
        dataStack = new Stack<>();
        helpStack = new Stack<>();
    }

    public void push(int val) {
        if (dataStack.isEmpty()) {
            dataStack.push(val);
            return;
        }
        int cur = dataStack.peek();
        if (cur > val) {
            dataStack.push(val);
            return;
        }
        while (!dataStack.isEmpty() && dataStack.peek() < val) {
            helpStack.push(dataStack.pop());
        }
        dataStack.push(val);
        while (!helpStack.isEmpty()) {
            dataStack.push(helpStack.pop());
        }
    }

    public void pop() {
        dataStack.pop();
    }

    public int peek() {
        return dataStack.peek();
    }

    public boolean isEmpty() {
        return dataStack.isEmpty();
    }

    public static void main(String[] args) {
        排序栈 p = new 排序栈();
        p.push(1);
        p.push(3);
        p.push(4);
        p.push(2);
        System.out.println();
    }
}
