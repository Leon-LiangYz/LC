package BinarySearchRelated;

/*
Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
             received 0, 1, 3, 5, 6 citations respectively.
             Since the researcher has 3 papers with at least 3 citations each and the remaining
             two with no more than 3 citations each, her h-index is 3.
Note:

If there are several possible values for h, the maximum one is taken as the h-index.

Follow up:

This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
Could you solve it in logarithmic time complexity?
 */

import org.junit.Test;

public class HIndexII275 {

    @Test
    public void test () {
        int[] citations = new int[]{0,1,4,5,6};
        int res = hIndex(citations);
        System.out.println(res);

//        int[] citations2 = new int[]{0,7};
//        int res2 = hIndex(citations2);
//        System.out.println(res2);
//
//        int[] citations3 = new int[]{1};
//        int res3 = hIndex(citations3);
//        System.out.println(res3);
//
//        int[] citations4 = new int[]{1,2,5,8,8,9};
//        int res4 = hIndex(citations4);
//        System.out.println(res4);


    }
    //Time: LogN
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int left = 0, right = citations.length;
        int len = citations.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return len - left;
    }

    @Test
    public void test2 () {
        int[] citations = new int[]{0,1,4,5,6};
        int res = hIndex2(citations);
        System.out.println(res);

//        int[] citations2 = new int[]{0,7};
//        int res2 = hIndex(citations2);
//        System.out.println(res2);
//
//        int[] citations3 = new int[]{1};
//        int res3 = hIndex(citations3);
//        System.out.println(res3);
//
//        int[] citations4 = new int[]{1,2,5,8,8,9};
//        int res4 = hIndex(citations4);
//        System.out.println(res4);


    }
    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int left = 0, right = citations.length - 1;
        int len = citations.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return len - left;
    }
}
