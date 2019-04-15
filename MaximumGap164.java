package ArrayRelated;
/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Return 0 if the array contains less than 2 elements.

Example 1:

Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.
Example 2:

Input: [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.
Note:

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
Try to solve it in linear time/space.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumGap164 {
    @Test
    public void test() {
        List<int[]> list = new ArrayList<>();
        //int[] nums1 = new int[]{5};
        int[] nums2 = new int[]{3, 6, 9, 1,4,8,2};
//        int[] nums3 = new int[]{1, 2, 8, 9};
//        int[] nums4 = new int[]{5, 4};
//        int[] nums5 = new int[]{5, 4, 8};
        //list.add(nums1);
        list.add(nums2);
//        list.add(nums3);
//        list.add(nums4);
//        list.add(nums5);
        for (int[] nums : list) {
            int res = maximumGap(nums);
            System.out.println(res);
        }

    }
    //bucket sort
    //time : O(n)
    //     space : O(n)

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int len = nums.length;
        int max = nums[0], min = nums[0];

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        int gap = (int)Math.ceil((double)(max - min) / (len - 1));
        int[] minBucket = new int[len - 1];
        int[] maxBucket = new int[len - 1];
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        Arrays.fill(maxBucket, Integer.MIN_VALUE);

        //Assign to buckets
        for (int num : nums) {
            if (num == min || num == max) {
                continue;
            }
            int bucketInd = (num - min) / gap;
            minBucket[bucketInd] = Math.min(num, minBucket[bucketInd]);
            maxBucket[bucketInd] = Math.max(num, maxBucket[bucketInd]);
        }

        int maxGap = 0;
        int pre = min;

        for (int i = 0; i < len - 1; i++) {
            if (minBucket[i] == Integer.MAX_VALUE && maxBucket[i] == Integer.MIN_VALUE) {
                continue;
            }
            maxGap = Math.max(maxGap, minBucket[i] - pre);
            pre = maxBucket[i];
        }
        maxGap = Math.max(maxGap, max - pre);
        return maxGap;
    }
}
