package SortAlgorithms;

import org.junit.Test;

public class quickSort {

    @Test
    public void test() {
        int[] nums = new int[]{1,12,27,4,5,88,24,56,7,6,19, 4};
        int[] res = quickSort(nums);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
    public int[] quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;

        quickSortHelper(nums, 0, nums.length - 1);
        return nums;

    }
    private void quickSortHelper(int[] nums, int left, int right) {
        if (left >= right) return;

        int pivotIdx = partition(nums, left, right);

        if (pivotIdx > left) {
            quickSortHelper(nums, left, pivotIdx - 1);
        }

        if (pivotIdx < right) {
            quickSortHelper(nums, pivotIdx + 1, right);
        }

    }
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];

        int start = left - 1;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, ++start, i);
            }
        }
        swap(nums, ++start, right);
        return start;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] =temp;
    }
}
