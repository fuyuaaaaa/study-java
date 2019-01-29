package top.fuyuaaa.study.offer;

/**
 * 替换空格：请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy
 * 分析：
 */
public class Solution002 {
    public String replaceSpace(StringBuffer str) {
        String temp = "%20";
        return str.toString().replaceAll(" ", temp);
    }
}
