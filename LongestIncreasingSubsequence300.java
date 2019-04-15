package DPRelated;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence300 {
    @Test
    public void test1() {
        int[] arr = new int[]{10,9,2,5,3,7,101,18};
        int res = lengthOfLIS(arr);
        System.out.println(res);
    }

    //S1: DP
    //Time: O(n^2)
    //Space: O(n)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        //memo[i] represents the # of LIS as of position i
        int[] memo = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                }

            }
            if (memo[i] > res) res = memo[i];
        }
        return res;
    }

    @Test
    public void test2() {
        int[] arr = new int[]{10,9,2,5,3,7,101,18};
        int res2 = lengthOfLIS2(arr);
        System.out.println(res2);
        List<Integer> tmpList = new ArrayList<>();
//        tmpList.add(12);
//        tmpList.set(0, 9);
//        System.out.println(tmpList);
    }

    //S2:Dynamic Programming with Binary Search
    //Time: O(nlogn)
    //Space: O(n)

    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        //memo[i] represents the element from the input array which creates the i size increasing sequence
        List<Integer> memo = new ArrayList<>();
        memo.add(nums[0]);

        for (int num : nums) {
            int i = binarySearchIndex(memo, num, 0, memo.size());
            if (i == memo.size()) {
                //if num is greater than the last element of memo, add num to the last digit
                //otherwise no need to change anything
                if (num > memo.get(memo.size() - 1)) {
                    memo.add(num);
                } else {
                    continue;
                }
            } else {
                //update value at index i of memo
                memo.set(i, num);
            }
        }
        return memo.size();
    }
    private int binarySearchIndex(List<Integer> memo, int target, int start, int end) {
        if (memo.size() == 0) {
            //return 0;
            throw new IllegalArgumentException("invalid input");
        }
        //if target is found in memo, return its index promptly
        //otherwise, keep going until finding the index right after the element smaller to our target
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (target == memo.get(mid)) {
                return mid;
            } else if (target > memo.get(mid)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
