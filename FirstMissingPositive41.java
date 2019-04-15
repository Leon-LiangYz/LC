package ArrayRelated;

/*
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.


 */

import org.junit.Test;

import java.util.Arrays;

public class FirstMissingPositive41 {

    @Test
    public void test() {
        int[] nums = new int[]{3, -1,1};
        int res = firstMissingPositive(nums);
        System.out.println(res);
    }

    //bucket sort思想？？
    //time: O(n)
    //space: O(1)
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;

                //这种不行， 因为num[i]在第二行时对应array中的值被修改，所以会影响第三行的结果
//                int temp = nums[i];
//                nums[i] = nums[nums[i] - 1];
//                nums[nums[i] - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }


}
