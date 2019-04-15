package DPRelated;

public class MaximumSubarray53 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        MaximumSubarray53 ms53 = new MaximumSubarray53();
        System.out.println(ms53.maxSubArray(nums));
    }
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max >= 0) {
                max += nums[i];
            } else {
                max = nums[i];
            }
            if (max > res) res = max;
            //res = Math.max(res, max);
        }
        return res;
    }
    //Time: O(n), space: O(n)
//    public int maxSubArray(int[] nums) {
//        if (nums == null || nums.length == 0) return 0;
//        int[] dp = new int[nums.length + 1];
//        dp[0] = nums[0];
//        int res = Integer.MIN_VALUE;
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = dp[i - 1] >= 0 ? dp[i - 1] + nums[i] : nums[i];
//            res = Math.max(res, dp[i]);
//        }
//        return res;
//    }


    //follow up:follow up是返回最大的sub array:
    // 记录max时的left bound and right bound,当cursum > maxSum时,更新leftbound and rightbound
}
