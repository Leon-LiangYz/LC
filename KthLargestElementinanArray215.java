package HeapAndPriorityQueueRelated;


/*
Find the kth largest element in an unsorted array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargestElementinanArray215 {

    @Test
    public void test() {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        int k = 4;
        int res = findKthLargest(nums, k);
        System.out.println(res);
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();

    }

    @Test
    public void test2() {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        int k = 6;
        int res2 = findKthLargest2(nums, k);
        System.out.println(res2);
    }
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
             for(int num : nums){
                 maxHeap.offer(num);
             }
             while(k >= 2){
                 maxHeap.poll();
                 k--;
             }
             return maxHeap.poll();
    }


    @Test
    public void test3() {
        int[] nums = new int[]{3,2,1,5,6,4};
        int k = 2;
        int res = findKthLargest3(nums, k);
        System.out.println(res);
    }

    //Quick selection
    //Time: worst case O(n^2)
    public int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        return findKthLargestHelper(nums, k, 0, nums.length - 1);

    }

    public int findKthLargestHelper(int[] nums, int k, int left, int right) {
        if (left >= right) return nums[left];

        int index = partition(nums, k, left, right);
        int rank = index - left + 1;

        if (rank == k) {
            return nums[index];
        } else if (rank > k) {
            return findKthLargestHelper(nums, k, left, index - 1);
        } else {
            return findKthLargestHelper(nums, k - rank, index + 1, right);
        }
    }

    private int partition(int[] nums, int k, int left, int right) {
        int pivot = nums[right];

        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (nums[j] > pivot) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, ++i, right);
        return i;
    }
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
