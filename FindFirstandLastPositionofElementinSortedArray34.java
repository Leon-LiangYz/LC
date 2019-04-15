package BinarySearchRelated;

import org.junit.Test;

/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

 */
public class FindFirstandLastPositionofElementinSortedArray34 {

    @Test
    public void test() {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        int[] res = searchRange(nums, target);
        for (int num : res) {
            System.out.println(num);
        }
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;

        int first = binarySearchFirst(nums, target, 0, nums.length);
        if (first >= nums.length || nums[first] != target) {
            return res;
        }
        res[0] = first;

        int second = binarySearchSecond(nums, target, 0, nums.length);
        res[1] = second - 1;

        return res;

    }
    private int binarySearchFirst(int[] nums, int target, int start, int end) {
        int left = start, right = end;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int binarySearchSecond(int[] nums, int target, int start, int end) {
        int left = start, right = end;
        while (left < right) {
            int  mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
