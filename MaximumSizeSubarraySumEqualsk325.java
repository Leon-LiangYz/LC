package ArrayRelated;

/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:

Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
Follow Up:
Can you do it in O(n) time?
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class MaximumSizeSubarraySumEqualsk325 {
    @Test
    public void test() {
        int[] nums = new int[]{-2, -1, 2, 1};
        int k = 1;
        int res = maxSubArrayLen(nums, k);
        System.out.println(res);
    }

    //Time: O(n)
    //Space: O(n)

    /*
    0: nums[0]
    1: nums[1] + nums[0]
    2: nums[2] + nums[1] + nums[0]
    so, if nums[2] + nums[1] + nums[0] - k == nums[0], the index of nums[0] will be starting index of the result
    */
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        //if num[i] - k == 0 is at the first position, the index should be -1, not 0
        map.put(0, -1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] - k)) {
                res = Math.max(res, i - map.get(nums[i] - k));
            }
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}
