package top.fuyuaaa.study.shujujiegou.unionfind;

/**
 * @Auther: fuyuaaaaa
 * @Description:
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-30 22:08
 */
public class UnionFind2 implements UF {
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //find root node O(h)
    private int find(int p) {
        if (p < 0 || p > parent.length) {
            throw new IllegalArgumentException();
        }

        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }
        parent[pRoot] = qRoot;
    }
}
