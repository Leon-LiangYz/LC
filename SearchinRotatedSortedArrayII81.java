package BinarySearchRelated;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?

 */

/*
3，3，3，3，5，6，1，3
 */

import org.junit.Test;

public class SearchinRotatedSortedArrayII81 {
    @Test
    public void test() {
        int[] nums = new int[]{2,5,6,7,9,12,0,0,1,2};
        int target = 0;
        boolean res = search(nums, target);
        System.out.println(res);
    }
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > nums[start]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] < nums[start]) {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            } else {
                start++;
            }
        }
        return false;
    }
}
