package top.fuyuaaa.study.leetcode.suanfa;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author : fuyuaaa
 * @date : 2020-09-22 15:12
 */
public class ConsistentHash {

    /**
     * hash值->ip地址
     */
    private TreeMap<Integer, String> virtualNode = new TreeMap<>();

    private Integer preVirtualNum = 1024;

    private List<String> ipList;

    public ConsistentHash(List<String> ipList) {
        this.ipList = ipList;
        for (String ip : ipList) {
            for (int i = 0; i < preVirtualNum; i++) {
                String ipName = ip + "_" + i;
                int hash = getHash(ipName);
                virtualNode.put(hash, ipName);
            }
        }
    }

    /**
     * 通过key去查找该key的数据在哪个ip上，或者说应该调用哪个ip
     *
     * @param key 比如redis缓存的key
     * @return ip地址
     */
    public String getServer(String key) {
        int hash = getHash(key);
        NavigableMap<Integer, String> navigableMap = virtualNode.tailMap(hash, true);
        Map.Entry<Integer, String> entry = navigableMap.firstEntry();
        String ip = entry == null ? virtualNode.firstEntry().getValue() : entry.getValue();
        return ip.substring(0, ip.indexOf("_"));
    }

    /**
     * FNV1_32_HASH算法
     */
    private int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值  
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    public static void main(String[] args) {
        List<String> ipList = Lists.newArrayList("172.0.0.1", "172.0.0.2", "172.0.0.3");
        ConsistentHash consistentHash = new ConsistentHash(ipList);

        Map<String, Integer> statistics = new HashMap<>(3);

        for (int i = 0; i < 1000000; i++) {
            String key = UUID.randomUUID().toString();
            String server = consistentHash.getServer(key);
            if (statistics.containsKey(server)) {
                statistics.put(server, statistics.get(server) + 1);
            } else {
                statistics.put(server, 0);
            }
        }
        System.out.println(JSON.toJSONString(statistics));
    }

}
