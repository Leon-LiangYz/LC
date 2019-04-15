package ArrayRelated;

import java.util.List;

public class SortColors75 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 1, 2, 0, 1,0};
        SortColors75 sc75 = new SortColors75();
        sc75.sortColors(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    private void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0, index = 0, right = nums.length - 1;
        while (index <= right) {
            if (nums[index] == 0) {
                swap(nums, left++, index++);
            } else if (nums[index] == 1) {
                index++;
            } else {
                swap(nums, index, right--);
            }
        }
    }
    private void swap(int[] nums, int left, int right) {
        int tmpVal = nums[left];
        nums[left] = nums[right];
        nums[right] =tmpVal;
    }

    //using couting sort
    public void sortColors2(int[] nums) {
        if(nums.length <= 1) return;
        int zero = 0;
        int one = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) zero++;
            if(nums[i] == 1) one++;
        }
        for(int j = 0; j < nums.length; j++) {
            if(j < zero) nums[j] = 0;
            else if(j < one + zero) nums[j] = 1;
            else nums[j] = 2;
        }
    }

}
