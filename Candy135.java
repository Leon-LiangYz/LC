package ArrayRelated;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Candy135 {

    @Test
    public void test() {
        int[] ratings1 = new int[]{1, 0, 2};
//        int res1 = candy(ratings1);
//        System.out.println(res1);
        int[] ratings2 = new int[]{1};
        int[] ratings3 = new int[]{1,2,2};
        int[] ratings4 = new int[]{7, 7, 3, 1, 0, 9, 2};
        int[] ratings5 = new int[]{2,2,2};
        List<int[]> list = new ArrayList<>();
        list.add(ratings1);
        list.add(ratings2);
        list.add(ratings3);
        list.add(ratings4);
        list.add(ratings5);
        int pos = 0;
        int[] res = new int[5];
        for (int[] ratings : list) {
            res[pos++] = candy(ratings);
        }

        for (int num : res) {
            System.out.print(num + " ");
        }

    }
    //Time: O(n)
    //Space: O(n)
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        for (int i = 1; i < candies.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = candies.length - 2; i >= 0; i --) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int res = 0;
        for (int candy : candies) {
            res += candy;
        }
        return res;

    }

    @Test
    public void test2() {
        int[] ratings1 = new int[]{1, 0, 2};
//        int res1 = candy(ratings1);
//        System.out.println(res1);
        int[] ratings2 = new int[]{1};
        int[] ratings3 = new int[]{1,2,2};
        int[] ratings4 = new int[]{7, 7, 3, 1, 0, 9, 2};
        int[] ratings5 = new int[]{2,2,2};
        List<int[]> list = new ArrayList<>();
        list.add(ratings1);
        list.add(ratings2);
        list.add(ratings3);
        list.add(ratings4);
        list.add(ratings5);
        int pos = 0;
        int[] res = new int[5];
        for (int[] ratings : list) {
            res[pos++] = candy2(ratings);
        }

        for (int num : res) {
            System.out.print(num + " ");
        }

    }

    public int candy2(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        int firstMinIndex = getFirstMinIndex(ratings);
        int left = firstMinIndex - 1;
        int right = firstMinIndex + 1;
        int count = 1;
        while (left >= 0 || right < ratings.length) {
            if (left >= 0) {
                if (ratings[left] > ratings[left + 1]) {
                    candies[left] = candies[left + 1] + 1;
                }
                count += candies[left--];
                //left--;
            }



            if (right < ratings.length) {
                if (ratings[right] > ratings[right - 1]) {
                    candies[right] = candies[right - 1] + 1;
                }
                count += candies[right++];
                //right++;
            }


        }

        return count;
    }
    private int getFirstMinIndex(int[] ratings) {
        int res = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] < ratings[res]) {
                res = i;
            }
        }
        return res;
    }

}
