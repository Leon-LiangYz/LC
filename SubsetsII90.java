package DFSRelated;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII90 {
    @Test
    public void test1 () {
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> res = new ArrayList<>();
        res = subsetsWithDup(nums);
        System.out.print(res);
        System.out.println();
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;


        if (nums == null) {
            return res; // 空列表
        }

        Arrays.sort(nums);
        subsetsWithDupHelper(nums, res, new ArrayList<>(), 0);
        return res;
    }
    private void subsetsWithDupHelper(int[] nums, List<List<Integer>> res,
                                      List<Integer> tmpList, int start) {
        res.add(new ArrayList<>(tmpList));

        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            tmpList.add(nums[i]);
            subsetsWithDupHelper(nums, res, tmpList, i + 1);
            tmpList.remove(tmpList.size() - 1);
        }
    }


    @Test
    public void test2 () {
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> res2;
        res2 = subsetsWithDup2(nums);
        System.out.print(res2);
        System.out.println();
    }
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;


        if (nums == null) {
            return res; // 空列表
        }

        Arrays.sort(nums);
        subsetsWithDupHelper2(nums, res, new ArrayList<>(), 0);
        return res;
    }
    private void subsetsWithDupHelper2(int[] nums, List<List<Integer>> res,
                                      List<Integer> tmpList, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(tmpList));
            return;
        }
        //add
        tmpList.add(nums[index]);
        subsetsWithDupHelper2(nums, res, tmpList, index + 1);
        tmpList.remove(tmpList.size() - 1);

        //handle duplicates
        while (index < nums.length - 1 && nums[index] == nums[index + 1]) {
            index++;
        }
        //not add
        subsetsWithDupHelper2(nums, res, tmpList, index + 1);

    }
}
