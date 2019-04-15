package DPRelated;

public class HouseRobber198 {
    public static void main(String[] args) {
        int[] num = new int[]{3, 5, 8, 1, 12, 3, 3, 0, 8};
        HouseRobber198 hr198 = new HouseRobber198();
        int res = hr198.rob(num);
        System.out.println(res);
    }
    //Time: O(n), space: O(1)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int preMax = 0;
        int curMax = 0;
        for (int num : nums) {
            int temp = curMax;
            curMax = Math.max(preMax + num, curMax);
            preMax = temp;
        }
        return curMax;
    }
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        if (nums[1] >= nums[0]) {
            dp[1] = nums[1];
        } else {
            dp[1] = nums[0];
        }

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
