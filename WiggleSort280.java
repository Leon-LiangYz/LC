package ArrayRelated;
/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]

 */

import org.junit.Test;

public class WiggleSort280 {
    @Test
    public void test() {
        int[] nums = new int[]{1,5,3,4};
        wiggleSort(nums);
    }
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 1) return;

        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0 && nums[i] > nums[i + 1]) ||(i % 2 == 1 && nums[i] < nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
