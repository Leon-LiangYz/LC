package TreeRelated;

import java.util.ArrayList;
import java.util.List;

public class PathSumII113 {
    public static void main(String[] args) {
        PathSumII113 ps113 = new PathSumII113();
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(18);
        root.right.left = new TreeNode(12);
        root.left.right.left = new TreeNode(6);
        int sum = 16;
        List<List<Integer>> res = ps113.pathSum(root, sum);
        System.out.println(res);
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> tmpList = new ArrayList<>();
        pathSumHelper(root, sum, res, tmpList);
        return res;
    }
    private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> tmpList) {
        if (root == null) return;
        tmpList.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                res.add(new ArrayList<>(tmpList));
            }
            tmpList.remove(tmpList.size() - 1);
            return;
        }
        pathSumHelper(root.left, sum - root.val, res, tmpList);
        pathSumHelper(root.right, sum - root.val, res, tmpList);
        tmpList.remove(tmpList.size() - 1);
    }
}
