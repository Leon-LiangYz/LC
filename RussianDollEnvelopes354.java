package BinarySearchRelated;

/*
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RussianDollEnvelopes354 {
    @Test
    public void test() {
        int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};


//        for (int i = 0; i < envelopes.length; i++) {
//            for (int j = 0; j < envelopes[0].length; j++) {
//                System.out.print(envelopes[i][j] + " ");
//            }
//            System.out.println();
//        }

        int res = maxEnvelopes(envelopes);
        System.out.println(res);
    }

//    [2, 3] -> [5, 4] -> [6, 7] -> [6, 4]
//    3 4 7 4 -> 3 4 7， 因此就变成了求最长递增序列

    //https://leetcode.com/problems/longest-increasing-subsequence/description/
    //time : O(nlogn)
    //     space : O(n)

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]; //假如宽度相等，高度更高的排在前面
            }
        });
        List<Integer> memo = new ArrayList<>();
        memo.add(envelopes[0][1]);

        for (int[] envelope : envelopes) {
            int i = binarySearch(memo, envelope[1], 0, memo.size());
            if (i == memo.size()) {
                if (envelope[1] > memo.get(memo.size() - 1)) {
                    memo.add(envelope[1]);
                } else {
                    continue;
                }
            } else {
                memo.set(i, envelope[1]);
            }
        }
        return memo.size();
    }
    private int binarySearch(List<Integer> memo, int target, int start, int end) {
        if (memo.size() == 0) return 0;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (memo.get(mid) == target) {
                return mid;
            } else if (memo.get(mid) > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    // 1, 3, 5, t = 4
    //0, t = 4
    // 1, 3, t = 2

}
