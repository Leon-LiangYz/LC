package ArrayRelated;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/summary-ranges/description/
//Given a sorted integer array without duplicates, return the summary of its ranges.
//
//        Example 1:
//
//        Input:  [0,1,2,4,5,7]
//        Output: ["0->2","4->5","7"]
//        Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
//        Example 2:
//
//        Input:  [0,2,3,4,6,8,9]
//        Output: ["0","2->4","6","8->9"]
//        Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.


public class SummaryRanges228 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,5,8,90,91,93,96,98,99,101,110};
        SummaryRanges228 sr228 = new SummaryRanges228();
        List<String> result = sr228.summaryRanges(nums);
        System.out.print(result);
    }
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            if (num != nums[i]) {
                res.add(num + "->" + nums[i]);
            } else {
                res.add(String.valueOf(num));
            }
        }
        return res;
    }

        public List<String> summaryRanges2(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        if (nums.length == 1) {
            res.add(String.valueOf(nums[0]));
            return res;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {
                int dynLower = nums[i];
                while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
                    i++;
                }
                res.add(dynLower + "->" + nums[i]);
            } else {
                res.add(String.valueOf(nums[i]));
            }
            if (i == nums.length - 2) {
                res.add(String.valueOf(nums[i + 1]));
                break;
            }
        }
        return res;

//        while (index < nums.length) {
//            int dynUpper = 0;
//            if (nums[index - 1] + 1 == nums[index]) {
//                dynLower = nums[index - 1];
//                dynUpper = nums[index];
//                while (index < nums.length && nums[index - 1] + 1 == nums[index]) {
//                    dynUpper = nums[index];
//                    index++;
//                }
//                res.add(dynLower + "->" + dynUpper);
//                if (index < nums.length - 2) index++;
//                if (index == nums.length - 1) {
//                    res.add(String.valueOf(nums[index++]));
//                    //index++;
//                }
//            } else {
//                res.add(String.valueOf(nums[index - 1]));
//                res.add(String.valueOf(nums[index]));
//                index++;
//            }
//        }
//        return res;
    }
}
