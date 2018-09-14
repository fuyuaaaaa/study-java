package fuyuaaa.shujujiegou.segmentTree;

/**
 * @Auther: fuyuaaaaa
 * @Description: 融合器
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-08 23:25
 */
public interface Merger<E> {
    E merge(E a, E b);
}
