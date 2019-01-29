package java.top.fuyuaaa.study_java.shujujiegou.other;

public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] a = {5,3,6,8,4,2};
        for (int num: a)
            bst.add(num);

        bst.preOrder();
        System.out.println();

        bst.inOrder();
        System.out.println();

        bst.postOrder();
    }
}
