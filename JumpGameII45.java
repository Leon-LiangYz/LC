package ArrayRelated;

public class JumpGameII45 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 0,1,6,0,0,0};
        JumpGameII45 jgII45 = new JumpGameII45();
//        int resBFS = jgII45.jumpBFS(nums);
//        System.out.println(resBFS);

//        int resDP = jgII45.jumpDP(nums);
//        System.out.println(resDP);

        int resGreedy = jgII45.jumpGreedy(nums);
        System.out.println(resGreedy);
    }
    public int jumpBFS(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int minSteps = 0;
        int nextMaxSteps = 0;
        int nextMaxTo = 0;
        int i = 0;
        while (nextMaxSteps - i + 1 > 0) { //num of node
            minSteps++;
            for (; i <= nextMaxSteps; i++) {
                nextMaxTo = Math.max(nextMaxTo, i + nums[i]);
                if (nextMaxTo >= nums.length - 1) return minSteps;
//                if (i == nextMaxSteps) {
//                    nextMaxSteps = nextMaxTo;
//                }
            }
            nextMaxSteps = nextMaxTo;
        }
        return 0;
    }
    //DP:
    //dp[i]: 从ith position出发，到达最后一步的最少次数
    //dp[i] = min(dp[j]) + 1, for all i < j <= i + A[i] find min index by for loop, then dp[min] + 1;
    //从dp[lenght - 1]初始化为0

    public int jumpDP(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < nums.length) {
                    min = Math.min(min, dp[i + j]);
                }
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = Integer.MAX_VALUE;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[0];
    }
    //Greedy
    public int jumpGreedy(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int curMaxto = 0;
        int preMaxto = 0;
        int jumpTimes = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curMaxto >= nums.length - 1) return jumpTimes + 1;
            if (i > preMaxto) {
                jumpTimes++;
                preMaxto = curMaxto;
            }
            if (i + nums[i] > curMaxto && nums[i] != 0) curMaxto = i + nums[i];
        }
        return -1;//no solution
    }
}
