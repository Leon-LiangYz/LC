package BinarySearchRelated;

import org.junit.Test;

/*
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */
public class SplitArrayLargestSum410 {
    @Test
    public void test() {
        int[] nums = new int[]{7, 5, 2, 8, 10};
        int m = 2;
        int res = splitArray(nums, m);
        System.out.println(res);

    }

    //Binary Search
    //Time: O(n*log(sum of array))
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) return 0;

        long low = 0; //low bound of sum, or the largest element of the array
        long high = 0; //upper bound of sum, or the sum of the array

        for (int i = 0; i < nums.length; i++) {
            high += nums[i];
            if (low < nums[i]) {
                low = nums[i];
            }
        }

        long res = high;

        while (low <= high) {
            long mid = (low + high) / 2;
            long sum = 0;
            int count = 1; //start from 1
            for (int i = 0; i < nums.length; i++) {
                if (sum + nums[i] > mid) {
                    count++;
                    sum = nums[i];
                } else {
                    sum += nums[i];
                }
            }

            if (count <= m) {
                res = Math.min(res, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int)res;
    }

}
