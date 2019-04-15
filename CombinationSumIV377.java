package DPRelated;

import org.junit.Test;

import java.util.HashMap;

public class CombinationSumIV377 {


    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        int res = combinationSum4(nums, target);
        System.out.println(res);
    }

    //time : O(n*k), k = target;
    //Space: O(1)
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < 0) return 0;
        int[] res = new int[target + 1];
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    res[i] += res[i - num];
                }
            }
        }
        return res[target];
    }

    @Test
    public void test2() {
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        int res = combinationSum42(nums, target);
        System.out.println(res);
    }
    //time : < O(2^n) space : O(n)
    public int combinationSum42(int[] nums, int target) {
        if (nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //int[] res = new int[1];
        return helper(nums, target, map);

    }

    private int helper(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        if (map.containsKey(target)) {
            return map.get(target);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += helper(nums, target - nums[i], map);
        }
        map.put(target, res);
        return res;

    }

}
