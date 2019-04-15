package HeapAndPriorityQueueRelated;

/*
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)


Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
 */

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointstoOrigin973 {

    @Test
    public void test() {
        int[][] points = new int[][]{{3, 3}, {5, -1}, {-2, 4},{18, 7}, {5, 23}};
        int k = 2;



        int[][] res = kClosest(points, k);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < k; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }

    }
    //Time: O(nlogK)
    //Space: O(k)
    public int[][] kClosest(int[][] points, int K) {
        if (K <= 0 || points == null || points.length == 0 || points[0] == null || points[0].length == 0 || K > points.length) {
            return new int[0][0];
        }

        // Arrays.sort(res, new Comparator<String>(){
        //     @Override
        //     public int compare(String s1, String s2){
        //         String str1 = s1 + s2;
        //         String str2 = s2 + s1;
        //         return str2.compareTo(str1);
        //     }
        // });

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                int n1 = a[0] * a[0] + a[1] * a[1];
                int n2 = b[0] * b[0] + b[1] * b[1];
                if (n1 < n2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        for (int i = 0; i < points.length; i++) {
            if (maxHeap.size() < K) {
                maxHeap.offer(points[i]);
                continue;
            }

            int val1 = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            int[] cur = maxHeap.peek();
            int val2 = cur[0] * cur[0] + cur[1] * cur[1];

            if (val1 < val2) {
                maxHeap.poll();
                maxHeap.offer(points[i]);
            }
        }

        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            int[] cur = maxHeap.poll();
            res[i][0] = cur[0];
            res[i][1] = cur[1];
        }

        return res;

    }
}
