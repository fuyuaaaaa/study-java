package fuyuaaa.shujujiegou.unionfind;


import java.util.Random;

/**
 * @Auther: fuyuaaaaa
 * @Description:
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-27 22:44
 */
public class Main {
    private static double testUf(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();

        long starttime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endtime = System.nanoTime();

        return (endtime - starttime) / 1000000000.0;
    }
    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;
//        UnionFind1 unionFind1 = new UnionFind1(size);
//        System.out.println("1: " + testUf(unionFind1, m));
//
//        UnionFind2 unionFind2 = new UnionFind2(size);
//        System.out.println("2: " + testUf(unionFind2, m));

        UnionFind3 unionFind3 = new UnionFind3(size);
        System.out.println("3: " + testUf(unionFind3, m));
        UnionFind4 unionFind4 = new UnionFind4(size);
        System.out.println("4: " + testUf(unionFind4, m));
        UnionFind5 unionFind5 = new UnionFind5(size);
        System.out.println("5: " + testUf(unionFind5, m));
        UnionFind6 unionFind6 = new UnionFind6(size);
        System.out.println("6: " + testUf(unionFind6, m));
    }
}
