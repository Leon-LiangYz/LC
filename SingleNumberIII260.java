package BitRelated;

/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]
Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */

import org.junit.Test;

public class SingleNumberIII260 {

    @Test
    public void test() {
        int[] nums = new int[]{1,2,1,3,2,5};
        int[] res = singleNumberIII(nums);

    }
    public int[] singleNumberIII(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int xor = 0;
        for (int num : nums) {//通过异或的方式，找到两个单独值的bit位为1的位置
            xor ^= num;
        }

        int mask = 1, copy = xor;
        while ((xor & mask) == 0) {
            mask <<= 1;
        }
        //跳出while loop时，mask的值是2的k次方

        //mask在这里相当于一个partitioner, & mask == 0的为一组， != 0的为另外一组
        for (int num : nums) {
            if ((num & mask) == 0) {
                xor ^= num;
            }
        }

        int[] res = new int[2];
        res[0] = xor;
        res[1] = xor ^ copy;
        return res;
    }
}
