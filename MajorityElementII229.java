package ArrayRelated;
/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]

 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII229 {
    @Test
    public void test() {
        int[] nums = new int[]{0,0,0};
        List<Integer> res = majorityElement(nums);
        System.out.println(res);
    }

    // Moore voting algorithm
    // 每次都找出一对不同的元素，从数组中删掉，直到数组为空或只有一种元素。
    // 不难证明，如果存在元素e出现频率超过半数，那么数组中最后剩下的就只有e。
    // [1,2,3,3,3]
    // time : O(n) space : O(1)

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        int n1 = 0, n2 = 0;
        int count1 = 0, count2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n1) {
                count1++;
            } else if (nums[i] == n2) {
                count2++;
            } else if (count1 == 0) {
                n1 = nums[i];
                count1++;
            } else if (count2 == 0) {
                n2 = nums[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == n1) {
                count1++;
            } else if (num == n2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            res.add(n1);
        }
        if (count2 > nums.length / 3) {
            res.add(n2);
        }
        return res;
    }
}
