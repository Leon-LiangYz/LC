package DPRelated;

public class MaximumProductSubarray152 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        MaximumProductSubarray152 mps152 = new MaximumProductSubarray152();
        System.out.println(mps152.maxProduct(nums));
    }
    //Time : O(n), space: O(1)
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int curMin = nums[0];
        int curMax = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmpMax = curMax;
            curMax = Math.max(Math.max(curMax * nums[i], curMin * nums[i]), nums[i]);
            curMin = Math.min(Math.min(tmpMax * nums[i], curMin * nums[i]), nums[i]);
            res = Math.max(res, curMax);
        }
        return res;
    }
}
