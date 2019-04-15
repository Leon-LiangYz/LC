package MathRelated;

/*
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
 */

import org.junit.Test;

import java.util.Random;

public class RandomPickIndex398 {
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 3, 3, 3,3,3,3,3,3,2,1,2,2,1};
        int target = 2;
        int res = pick(target, nums);
        System.out.println(res);
    }
//    public Solution(int[] nums) {
//        this.nums = nums;
//        rmd = new Random();
//    }

    //Time: O(n)
    //Space: O(1)
    public int pick(int target, int[] nums) {
        Random rmd = new Random();
        int res = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;

            if (rmd.nextInt(++count) == 0) {
                res = i;
            }
        }
        return res;
    }
}
