package ArrayRelated;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BestTimetoBuyandSellStockIII123 {
    List<int[]> stockPrices;
    public BestTimetoBuyandSellStockIII123 () {
        stockPrices = new ArrayList<>();
    }

    //Test for maxProfit
    @Test
    public void test1() { //Test for maxProfit
        int[] prices1 = new int[]{3,3,5,0,0,3,1,4};
        int[] prices2 = new int[]{1,2,3,4,5};
        int[] prices3 = new int[]{7, 6, 4, 3, 1};
        int[] prices4 = new int[]{1,2,3,4,5};
        stockPrices.add(prices1);
        stockPrices.add(prices2);
        stockPrices.add(prices3);
//        int res1 = maxProfit(prices1);
//        System.out.println(res1);
//        int res2 = maxProfit(prices2);
//        System.out.println(res2);
//        int res3 = maxProfit(prices3);
//        System.out.println(res3);
        int res4 = maxProfit(prices4);
        System.out.println(res4);
        System.out.println("End of testing maxProfit");

    }
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for(int price:prices){                              // Assume we only have 0 money at first
            release2 = Math.max(release2, buy2 + price);     // The maximum if we've just sold 2nd stock so far.
            buy2    = Math.max(buy2,    release1 - price);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, buy1 + price);     // The maximum if we've just sold 1nd stock so far.
            buy1    = Math.max(buy1,    -price);          // The maximum if we've just buy  1st stock so far.
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1
    }

    public int maxProfitSlidingWindow(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int profit = 0;
        int res = 0;
        int tran = 0;
        for (int i = 0, j = 1; j < prices.length; j++) {
            if (prices[j] <= prices[i]) {
                i++;
                continue;
            } else if (prices[j] > prices[i]) {
                profit += prices[j] - prices[i];
                tran++;
                if (tran == 2) res = Math.max(res, profit);
            }
        }
        return res;
    }

}
