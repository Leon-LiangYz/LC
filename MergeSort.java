package SortAlgorithms;

import org.junit.Test;

public class MergeSort {

    @Test
    public void test() {
        int[] nums = new int[]{1,12,27,4,5,88,24,56,7,6,19, 4};
        int[] res = mergeSort(nums);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
    public int[] mergeSort(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        int[] helper = new int[nums.length];
        mergeSortHelper(nums, helper, 0, nums.length - 1);
        return nums;
    }

    private void mergeSortHelper(int[] nums, int[] helper, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSortHelper(nums, helper, left, mid);
        mergeSortHelper(nums, helper,mid + 1, right);

        mergeAndSort(nums, left, right, mid, helper);
    }


    private void mergeAndSort(int[] nums, int left, int right, int mid ,int[] helper) {
        for (int i = left; i <= right; i++) {
            helper[i] = nums[i];
        }

        int curLeft = left;
        int curRight = mid + 1;

        while (curLeft <= mid && curRight <= right) {
            if (helper[curLeft] < helper[curRight]) {
                nums[left++] = helper[curLeft++];
            } else {
                nums[left++] = helper[curRight++];
            }
        }

        while (curLeft <= mid) {
            nums[left++] = helper[curLeft++];
        }
        while (curRight <= right) {
            nums[left++] = helper[curRight++];
        }
    }
}
