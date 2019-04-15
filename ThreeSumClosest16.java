package ArrayRelated;


import java.util.Arrays;
//https://leetcode.com/problems/3sum-closest/description/

//Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
//
//        Example:
//
//        Given array nums = [-1, 2, 1, -4], and target = 1.
//
//        The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

public class ThreeSumClosest16 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        ThreeSumClosest16 tsc16 = new ThreeSumClosest16();
        int result = tsc16.threeSumClosest(nums, 1);
        System.out.print(result);
    }
    private int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int tmpSum = nums[i] + nums[start] + nums[end];
                if (tmpSum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(tmpSum - target) < Math.abs(res - target)) {
                    res = tmpSum;
                }
            }
        }
        return res;
    }
}
