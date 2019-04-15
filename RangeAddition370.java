package MatrixRelated;
/*
Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]
Explanation:

Initial state:
[0,0,0,0,0]

After applying operation [1,3,2]:
[0,2,2,2,0]

After applying operation [2,4,3]:
[0,2,5,5,3]

After applying operation [0,2,-2]:
[-2,0,3,5,3]
 */

import org.junit.Test;

public class RangeAddition370 {

    @Test
    public void test() {
        int k = 5;
        int[][] updates = new int[][]{{0,0,1}};
        int[] res = getModifiedArray(k, updates);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }

    //Time: O(n + k)
    //Space: O(n) for storing results
    public int[] getModifiedArray(int length, int[][] updates) {
        if (length < 0 || updates == null || updates.length == 0 || updates[0] == null || updates[0].length < 3) {
            return new int[1];
        }

        int[] res = new int[length];

        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int delta = update[2];
            res[start] += delta;
            if (end + 1 < length) {
                res[end + 1] -= delta;
            }
//            for (int i = start; i <= end; i++) {
//                res[i] += delta;
//            }
        }

        for (int i = 1; i < length; i++) {
            res[i] += res[i - 1];
        }

        return res;
    }
    //0, 2, 2, 2, 0
    //0, 2, 5, 5, 3
    //-2, 0, 3, 5, 3
}
