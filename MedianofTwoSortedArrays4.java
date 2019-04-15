package BinarySearchRelated;

/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */

import org.junit.Test;

public class MedianofTwoSortedArrays4 {
    @Test
    public void test() {
        int[] nums1 = new int[]{3, 5, 8, 9};
        int[] nums2 = new int[]{1, 2, 7, 10, 11, 12};
        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }
    //time : O(log(min(m,n)))
    //     space : O(1)

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;

        int cut1 = 0, cut2 = 0; //左有几个元素
        int cutL = 0, cutR = nums1.length;

        while (cut1 <= nums1.length) {
            cut1 = cutL + (cutR - cutL) / 2;
            cut2 = len / 2 - cut1;

            double l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double r1 = cut1 == nums1.length ? Integer.MAX_VALUE : nums1[cut1];
            double r2 = cut2 == nums2.length ? Integer.MAX_VALUE : nums2[cut2];

            if (l1 > r2) {
                cutR = cut1 - 1;
            } else if (l2 > r1) {
                cutL = cut1 + 1;
            } else {
                if (len % 2 == 0) {
                    l1 = l1 > l2 ? l1 : l2;
                    r1 = r1 < r2 ? r1 : r2;
                    return (l1 + r1) / 2;
                } else {
                    return r1 < r2 ? r1 : r2;
                }
            }
        }
        return -1;
    }

    @Test
    public void test2() {
        int[] nums1 = new int[]{3, 5, 8, 9};
        int[] nums2 = new int[]{1, 2, 7, 10, 11, 12};
        double res = findMedianSortedArrays2(nums1, nums2);
        System.out.println(res);
    }
    //O(log (m+n))
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = 0, len2 = 0;
        if (nums1 == null) {
            len2 = nums2.length;
        } else if (nums2 == null) {
            len1 = nums1.length;

        } else {
            len1 = nums1.length;
            len2 = nums2.length;
        }

        if ((len1 + len2) % 2 == 0) {
            return (double)(findKthVal(nums1, 0, len1, nums2, 0, len2, (len1 + len2) / 2)
                    +  findKthVal(nums1, 0, len1, nums2, 0, len2, (len1 + len2) / 2 + 1)) / 2.0;
        } else {
            return (double)findKthVal(nums1, 0, len1, nums2, 0, len2, (len1 + len2) / 2 + 1);
        }
    }
    private int findKthVal(int[] nums1, int start1, int len1, int[] nums2, int start2, int len2, int k) {
        if (len1 > len2) return findKthVal(nums2, start2, len2, nums1, start1, len1, k);
        if (len1 == 0) return nums2[start2 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int q1 = Math.min(len1, k / 2);
        int q2 = k - q1;
        int val1 = nums1[start1 + q1 - 1], val2 = nums2[start2 + q2 - 1];

        if (val1 == val2) {
            return val1;
        } else if (val1 < val2) {
            return findKthVal(nums1, start1 + q1, len1 - q1, nums2, start2, len2, k - q1);
        } else { // val1 > val2
            return findKthVal(nums1, start1, len1, nums2, start2 + q2, len2 - q2, k - q2);
        }
    }
}
