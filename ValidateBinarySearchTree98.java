package TreeRelated;


public class ValidateBinarySearchTree98 {
    public static void main(String[] args) {
        ValidateBinarySearchTree98 vbst98 = new ValidateBinarySearchTree98();
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(18);
        root.right.left = new TreeNode(12);
        root.left.right.left = new TreeNode(6);
        boolean res = vbst98.isValidBST(root);
        System.out.println(res);
        TreeDrawer td = new TreeDrawer();
        td.draw(root);
    }
    // time : O(n)
    // space : O(n)
    private boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isValidBSTHelper(root, null, null);
    }
    private boolean isValidBSTHelper(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
    }

}
