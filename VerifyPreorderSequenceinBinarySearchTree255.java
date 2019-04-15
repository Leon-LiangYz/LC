package TreeRelated;

import org.junit.Test;

import java.util.Stack;

public class VerifyPreorderSequenceinBinarySearchTree255 {
    @Test
    public void test() {
        int[] preorder = new int[]{5, 2, 1, 3, 6};
        boolean res = verifyPreorder(preorder);
        System.out.println(res);
    }

    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return true;
        int min = Integer.MIN_VALUE;
        int i = -1;
        for (int num : preorder) {
            if (num < min) return false;
            while (i >= 0 && num > preorder[i]) {
                min = preorder[i--];
            }
            preorder[++i] = num;
        }
        return true;
    }

    @Test
    public void test2() {
        int[] preorder = new int[]{5, 2, 1, 3, 6};
        boolean res = verifyPreorder2(preorder);
        System.out.println(res);
    }

    public boolean verifyPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) return true;
        int min = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();

        for (int num : preorder) {
            if (num < min) return false;
            while (!stack.isEmpty() && num > stack.peek()) {
                min = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }
}
