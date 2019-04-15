package TreeRelated;

/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 */

import org.junit.Test;

import java.util.TreeSet;

public class ContainsDuplicateIII220 {

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 1};
        TreeSet<Long> tSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            tSet.add((long)nums[i]);
        }
        System.out.println(tSet.floor((long)nums[0] + 7));
        int k = 3, t = 0;
        boolean res = containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(res);
    }

    //S1: treeset
    //time : O(nlogk)
    //space : O(k)

    //The ceiling(E e) method is used to return the least element in this set greater than or equal to the given element,
    // or null if there is no such element.
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k < 0 || t < 0) {
            return false;
        }

        TreeSet<Long> tSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long floor = tSet.floor((long)nums[i] + t);
            Long ceil = tSet.ceiling((long)nums[i] - t);
            if ((floor != null && floor >= nums[i]) || (ceil != null && ceil <= nums[i])) {
                return true;
            }

            tSet.add((long)nums[i]);
            if (i >= k) {
                tSet.remove((long)nums[i - k]);
            }
        }
        return false;
    }
}
