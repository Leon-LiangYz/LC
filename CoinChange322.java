package DPRelated;

import HeapAndPriorityQueueRelated.EmployeeFreeTime759;
import org.junit.Test;

public class CoinChange322 {
    @Test
    public void test() {
        int amount = 12;
        int[] coins = new int[]{1, 2, 5};
        int res = coinChange(coins, amount);
        System.out.println(res);
    }
    //time: O(n*amount)
    //Space: O(amount)
    public int coinChange(int[] coins, int amount) {
        if (amount == 0 || coins == null || coins.length == 0) return 0;
        //dp[i] means at least how many coins need when amount is i
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != -1) {
                    min = Math.min(min, dp[i - coins[j]] + 1);
                }
                dp[i] = min == Integer.MAX_VALUE ? -1 : min;
            }
        }
        return dp[amount];
    }



    @Test
    public void test2() {
        int amount = 12;
        int[] coins = new int[]{1, 2, 5};
        int res = coinChange2(coins, amount);
        System.out.println(res);
    }

    //using search
    //Time: O(exponential)
    //space: O(worst case of number of values to reach target amount)
    public int coinChange2(int[] coins, int amount) {
        if (amount == 0 || coins == null || coins.length == 0) return 0;
        int[] res = new int[]{Integer.MAX_VALUE};
        coinChangeHelper(coins, 0, coins.length, amount, 0, res);
        return res[0] == Integer.MAX_VALUE ? -1 : res[0];
    }

    private void coinChangeHelper(int[] nums, int start, int end, int sum, int count ,int[] res) {
        if (sum == 0) {
            res[0] = Math.min(res[0], count);
            return;
        }
        if (sum < 0) return;

        for (int i = start; i < end; i++) {
            sum -= nums[i];

            if (sum >= 0) {
                coinChangeHelper(nums, i, end, sum, count + 1, res);
            }
            sum += nums[i];
        }
    }
}
