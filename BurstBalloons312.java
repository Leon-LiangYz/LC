package DPRelated;

import org.junit.Test;

public class BurstBalloons312 {
    @Test
    public void test() {
        int[] nums = new int[]{3, 1, 5, 8};
        int res = maxCoins(nums);
        System.out.println(res);
    }
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];

        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = arr[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];
        return maxCoinsHelper(arr, dp, 1, n);

    }
    private int maxCoinsHelper (int[] nums, int[][] dp, int i, int j) {
        if (i > j) return 0;
        if (dp[i][j] > 0) return dp[i][j];

        for (int x = i; x <= j; x++) {
            dp[i][j] = Math.max(dp[i][j], maxCoinsHelper(nums, dp, i, x - 1)
                                          + nums[i - 1] * nums[x] * nums[j + 1]
                                          + maxCoinsHelper(nums, dp, x + 1, j));
        }

        return dp[i][j];

    }

    @Test
    public void test2() {
        int[] nums = new int[]{3,1,5,8};
        int res = maxCoins2(nums);
        System.out.println(res);
    }
    //DP
    //Time: O(n^3)
    //space: O(n^2
    public int maxCoins2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];

        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = arr[n + 1] = 1;

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 1; len <= n; len++) {
            for (int left = 1; left <= n - len + 1; left++) {
                int right = left + len - 1;
                for (int k = left; k <= right; k++) {
                    dp[left][right] = Math.max(dp[left][right],
                            dp[left][k - 1] + arr[left - 1] * arr[k] * arr[right + 1] + dp[k + 1][right]);
                }
                for (int i = 0; i < dp.length; i++) {
                    for (int j = 0; j < dp[0].length; j++) {
                        System.out.print(dp[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
        System.out.println(dp[1][2]);


        return dp[1][n];
    }
}
