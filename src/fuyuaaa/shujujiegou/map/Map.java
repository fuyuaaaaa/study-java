package fuyuaaa.shujujiegou.map;

/**
 * @Package_Name: map
 * @Auther: fuyua
 * @Date: created in 16:49 2018/6/23
 */
public interface Map<K, V> {
    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key, V value);
    int getSize();
    boolean isEmpty();
}
