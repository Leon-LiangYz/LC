package ArrayRelated;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum239 {
    int[] nums;
    int k;
    int[] nums2;
    int k2;
    public SlidingWindowMaximum239() {
        nums = new int[]{1,3,-1,-3,5,3,6,7};
        k = 3;

        nums2 = new int[]{1,10,-3,-2,7,5,8,2,-9,4,3,0,12,-30,-11,-7,25};
        k2 = 4;
    }
    @Test
    public void test1() {
        int[] result = maxSlidingWindow(nums, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
        int[] result2 = maxSlidingWindow(nums2, k2);
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    @Test
    public void test2() {
        int[] result = maxSlidingWindow2(nums, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
        int[] result2 = maxSlidingWindow2(nums2, k2);
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


    //Time: O((n - k + 1) * k)
    //Space: O(k)

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] r = new int[n-k+1];
        int ri = 0;
        // store index
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = nums[q.peek()];
            }
        }
        return r;
    }
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peek()];
            }
        }
        return res;
    }


}