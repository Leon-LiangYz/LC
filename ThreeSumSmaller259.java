package ArrayRelated;

/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?
 */

import org.junit.Test;

import java.util.Arrays;


/*
1, 2, 3, 4, 5, target = 8,

 */


public class ThreeSumSmaller259 {
    @Test
    public void Test() {
        int[] nums = new int[]{-2, 0, 1, 3};
        int target = 2;
        int res = threeSumSmaller(nums, target);
        System.out.println(res);
    }
    //Time: O(n^2)
    //Space: O(1)
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length <= 2 || target == Integer.MIN_VALUE) {
            return 0;
        }
        int res = 0;
        //sort array
        Arrays.sort(nums);


        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    res += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }


    @Test
    public void Test2() {
        int[] nums = new int[]{-2, 0, 1, 3};
        int target = 2;
        int res = threeSumSmaller2(nums, target);
        System.out.println(res);
    }
    //Time: O((N^2) * logN)
    // Space: O(1)
    public int threeSumSmaller2(int[] nums, int target) {
         Arrays.sort(nums);
         int sum = 0;
         for (int i = 0; i < nums.length - 2; i++) {
             sum += twoSumSmaller(nums, i + 1, target - nums[i]);
         }
         return sum;
     }

     private int twoSumSmaller(int[] nums, int startIndex, int target) {
         int sum = 0;
         for (int i = startIndex; i < nums.length - 1; i++) {
             int j = binarySearch(nums, i, target - nums[i]);
             sum += j - i;
         }
         return sum;
     }

     private int binarySearch(int[] nums, int startIndex, int target) {
         int left = startIndex;
         int right = nums.length - 1;
         while (left < right) {
             int mid = (right + left + 1) / 2;
             if (nums[mid] < target) {
                 left = mid;
             } else {
                 right = mid - 1;
             }
         }
         return left;
     }
}
