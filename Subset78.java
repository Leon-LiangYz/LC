package DFSRelated;

/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

 */

import org.junit.Test;

import java.util.*;

public class Subset78 {
    @Test
    public void test1 () {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> res = new ArrayList<>();
        res = subsets(nums);
        System.out.print(res);
        System.out.println();
    }
    //Time: O(2^n)
    //Space: O(n)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> tmpList = new ArrayList<>();
        subsetsHelper(nums, res, tmpList, 0);
        return res;
    }
    public void subsetsHelper(int[] nums, List<List<Integer>> res, List<Integer> tmpList, int start) {
        res.add(new ArrayList<>(tmpList));
        for (int i = start; i < nums.length; i++) {
            tmpList.add(nums[i]);
            subsetsHelper(nums, res, tmpList, i + 1);
            tmpList.remove(tmpList.size() - 1);
        }
    }

    @Test
    public void test2 () {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        List<List<Integer>> res = new ArrayList<>();
        res = subsets2(nums);
        System.out.print(res);
        System.out.println();
    }
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> tmpList = new ArrayList<>();
        subsetsHelper2(nums, res, tmpList, 0);
        return res;
    }
    private void subsetsHelper2(int[] nums, List<List<Integer>> results, List<Integer> subset,int index) {
        // 3. 递归的出口
        if (index == nums.length) {
            results.add(new ArrayList<Integer>(subset));
            return;
        }
        // 2. 递归的拆解
        // (如何进入下一层)

        // 选了 nums[index]
        subset.add(nums[index]);
        subsetsHelper2(nums, results, subset, index + 1);

        // 不选 nums[index]
        subset.remove(subset.size() - 1);
        subsetsHelper2(nums, results, subset, index + 1);

    }

    @Test
    //using BFS
    public void test3 () {
        int[] nums = new int[]{1, 2};
        List<List<Integer>> res = new ArrayList<>();
        res = subsets3(nums);
        System.out.print(res);
        System.out.println();
    }
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;


        if (nums == null) {
            return res; // 空列表
        }

        Arrays.sort(nums);

        // BFS
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<Integer>());
        while (!queue.isEmpty()) {
            List<Integer> subset = queue.poll();
            res.add(subset);

            for (int i = 0; i < nums.length; i++) {
                if (subset.size() == 0 || subset.get(subset.size() - 1) < nums[i]) {
                    List<Integer> nextSubset = new ArrayList<>(subset);
                    nextSubset.add(nums[i]);
                    queue.offer(nextSubset);
                }
            }
        }

        return res;
    }

}
