package ArrayRelated;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/missing-ranges/description/
//Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
//        Example:
//        Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
//        Output: ["2", "4->49", "51->74", "76->99"]

public class MissingRanges163 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,3,50,75};
        int lower = 0;
        int upper = 99;
        MissingRanges163 mr163 = new MissingRanges163();
        List<String> result = mr163.findMissingRanges(nums, lower, upper);
        System.out.print(result);
    }
    private List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        //in case low is -2^32
        long dynLower = (long)lower;
        long newUpper = (long)upper;

        for (int num : nums) {
            if (dynLower == num) {
                dynLower++;
            } else if (dynLower < num) {
                if (dynLower + 1 == num) {
                    res.add(String.valueOf(dynLower));
                } else {
                    res.add(dynLower + "->" + (num - 1));
                }
                dynLower = (long)num + 1;
            }
        }
        if (dynLower == newUpper) res.add(String.valueOf(dynLower));
        if (dynLower < newUpper) res.add(dynLower + "->" + newUpper);
        return res;
    }
}
