package ArrayRelated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ThreeSum15 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        ThreeSum15 ts15 = new ThreeSum15();
//        List<List<Integer>> res = ts15.threeSum(nums);
//        for (List<Integer> list : res) {
//            System.out.println(list);
//        }
        int[] nums2 = new int[]{0, 0, 0};
        System.out.println("Using S2:");
        List<List<Integer>> res2 = ts15.threeSum2(nums2);
        for (List<Integer> list : res2) {
            System.out.println(list);
        }
    }
    //S1: Time: O(N^2), space: O(n) to store result
    private List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        //index i is the index of base(first) number, left and right are indexes of two sum
        for (int i = 0; i < nums.length - 2; i++) {
            //i + 1 when i > 0 and nums[i] == nums[i - 1]
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            int sumLeft = 0 - nums[i];
            while (left < right) {
                if (nums[left] + nums[right] == sumLeft) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (nums[left] + nums[right] > sumLeft) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
    //S2: not using sort
        //using hashset
    private List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        HashSet<Integer> firstNumSet = new HashSet<>();

        //i is the index of first number
        for (int i = 0; i < nums.length; i++) {
            if (firstNumSet.contains(nums[i])) continue;
            int sumLeft = 0 - nums[i];
            HashSet<Integer> secondNumSet = new HashSet<>();
            HashSet<Integer> thirdNumSet = new HashSet<>();
            for (int j = i + 1; j <= nums.length - 1; j++) {
                if (!firstNumSet.contains(nums[j]) && !thirdNumSet.contains(nums[j])) {
                    if (secondNumSet.contains(sumLeft - nums[j])) {
                        res.add(Arrays.asList(nums[i], nums[j], sumLeft - nums[j]));
                        thirdNumSet.add(nums[j]);
                    } else {
                        secondNumSet.add(nums[j]);
                    }
                }
            }
            firstNumSet.add(nums[i]);
        }
        return res;
//        List<List<Integer>> res = new ArrayList<>();
//        if (nums == null || nums.length < 3){
//            return res;
//        }
//        HashSet<Integer> firstNum = new HashSet<>();
//        //[-1, 0, 1, 2, -1, -4]
//        //firstNum:-1
//        //i = 1 sum = 0
//        //secondNum:
//        //thirdNum:
//        //j = 2
//        //res:[-1,1,0] [-1, -1, 2]
//        for (int i = 0; i < nums.length; i++){
//            if (firstNum.contains(nums[i])){
//                continue;
//            }
//            int sum = -nums[i];
//            HashSet<Integer> secondNum = new HashSet<>();
//            HashSet<Integer> thirdNum = new HashSet<>();
//            for (int j = i + 1; j < nums.length; j++){
//                if (!firstNum.contains(nums[j]) && !thirdNum.contains(nums[j])){
//                    if (secondNum.contains(sum - nums[j])){
//                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], sum - nums[j])));
//                        thirdNum.add(nums[j]);
//                    } else {
//                        secondNum.add(nums[j]);
//                    }
//                }
//            }
//            firstNum.add(nums[i]);
//        }
//        return res;


    }
}
