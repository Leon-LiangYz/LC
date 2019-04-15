package BitRelated;

/*
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
 */

import org.junit.Test;

public class SingleNumberII137 {
    @Test
    public void test() {
        int[] nums = new int[]{2, 2, 2, 3};
        int res = singleNumber(nums);
        System.out.println(res);
    }
    //Bit
//    1, 存入ones里
//     2，清空ones 存入twos
//     3，twos进行清空
//    time : O(n)
//    space : O(1)

    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }


    @Test
    public void test2() {
        int[] nums = new int[]{2, 2, 2, 3};
        int res = singleNumber2(nums);
        System.out.println(res);
    }

    //通过每个数每一位and 1操作，统计每一位上1出现的次数，是3的倍数即说明出现3次
    public int singleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (count[i] % 3 == 1) {
                res += 1 << i;
            }
        }

        return res;
    }

}
