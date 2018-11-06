package fuyuaaa.leetcode;

/**
 * 给定一个编码字符串 S。为了找出解码字符串并将其写入磁带，从编码字符串中每次读取一个字符，并采取以下步骤：
 * 如果所读的字符是字母，则将该字母写在磁带上。
 * 如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写 d-1 次。
 * 现在，对于给定的编码字符串 S 和索引 K，查找并返回解码字符串中的第 K 个字母。
 */
public class Solution880 {
    public String decodeAtIndex(String S, int K) {
        long size = 0;
        int N = S.length();

        for (int i = 0; i < N; ++i) {
            char c = S.charAt(i);
            if (Character.isDigit(c))
                size *= c - '0';
            else
                size++;
        }

        for (int i = N-1; i >= 0; --i) {
            char c = S.charAt(i);
            K %= size;
            if (K == 0 && Character.isLetter(c))
                return Character.toString(c);

            if (Character.isDigit(c))
                size /= c - '0';
            else
                size--;
        }

        throw null;
    }

    public static void main(String[] args) {
        Solution880 solution880 = new Solution880();
        System.out.println(solution880.decodeAtIndex("a2c33", 12));
    }

}
/**
 *思路
 *
 * 如果我们有一个像 appleappleappleappleappleapple 这样的解码字符串和一个像 K=24 这样的索引，那么如果 K=4，答案是相同的。
 *
 * 一般来说，当解码的字符串等于某个长度为 size 的单词重复某些次数（例如 apple 与 size=5 组合重复6次）时，索引 K 的答案与索引 K % size 的答案相同。
 *
 * 我们可以通过逆向工作，跟踪解码字符串的大小来使用这种洞察力。每当解码的字符串等于某些单词 word 重复 d 次时，我们就可以将 k 减少到 K % (Word.Length)。
 *
 * 算法
 *
 * 首先，找出解码字符串的长度。之后，我们将逆向工作，跟踪 size：解析符号 S[0], S[1], ..., S[i] 后解码字符串的长度。
 *
 * 如果我们看到一个数字 S [i]，则表示在解析 S [0]，S [1]，...，S [i-1] 之后解码字符串的大小将是 size / Integer(S[i])。 否则，将是 size - 1。
 */
