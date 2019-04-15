package TreeRelated;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BoundaryofBinaryTree545 {
    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        List<Integer> res = boundaryOfBinaryTree(root);
        System.out.println(res);
    }
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        res.add(root.val);
        if (isLeafNode(root)) {
            return res;
        }
        findSideView(root.left, res, res.size(), true);
        findLeaves(root, res);
        findSideView(root.right, res, res.size(), false);

        return res;
    }

    private void findSideView(TreeNode root, List<Integer> res, int preSize, boolean left) {
        if (root == null) return;

        TreeNode cur = root;
        while (cur != null) {
            if (left) {
                if (!isLeafNode(cur)) {
                    res.add(cur.val);
                }
                cur = cur.left != null ? cur.left : cur.right;
            } else {
                if (!isLeafNode(cur)) {
                    res.add(preSize, cur.val);
                }
                cur = cur.right != null ? cur.right : cur.left;
            }
        }
    }

    private void findLeaves(TreeNode root, List<Integer> res) {
        if (root == null) return;
        if (isLeafNode(root)) {
            res.add(root.val);
            return;
        }
        findLeaves(root.left, res);
        findLeaves(root.right, res);
    }

    private boolean isLeafNode(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
