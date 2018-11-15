package fuyuaaa.offer;

/**
 * @author: fuyuaaaaa
 * @description: 旋转数组的最小数字
 * 题目：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * @program: study-java
 * @creat: 2018-11-15 10:26
 **/
public class Solution006 {
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0)
            return 0;
        int left = 0, right = array.length - 1, mid = 0;
        while (left < right) {
            if (right == left + 1) {
                mid = right;
                break;
            }
            mid = left + (right - left) / 2;
            if (array[left] <= array[mid])
                left = mid;
            else if (array[right] >= array[mid])
                right = mid;
        }
        return array[mid];
    }
}
