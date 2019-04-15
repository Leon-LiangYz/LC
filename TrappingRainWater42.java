package StackRelated;

import org.junit.Test;

import java.util.Stack;

public class TrappingRainWater42 {
//    public static void main(String[] args) {
//        int[] height = new int[]{2,1,0,2,3};
//        System.out.print("Original array: ");
//        for (Integer num : height) {
//            System.out.print(num + " ");
//        }
//        System.out.println();
//        TrappingRainWater42 trw42 = new TrappingRainWater42();
//        int res = trw42.trap(height);
//        System.out.println();
//        System.out.print("Result: " + res);
//    }
    @Test
    public void test1 () {
        int[] height = new int[]{2,1,0,2,3};
        System.out.print("Original array: ");
        for (Integer num : height) {
            System.out.print(num + " ");
        }
        int res = trap(height);
        System.out.println();
        System.out.print("Result: " + res);
        System.out.println();
        height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.print("Original array: ");
        for (Integer num : height) {
            System.out.print(num + " ");
        }
        res = trap(height);
        System.out.println();
        System.out.print("Result: " + res);
        System.out.println();
    }



    private int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        //stack中存入index of position
        Stack<Integer> stack = new Stack<>();
        int result = 0, curInd = 0;
        while (curInd < height.length) {
            while (!stack.isEmpty() && height[curInd] > height[stack.peek()]) {
                int indOfheightBase = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = curInd - stack.peek() - 1;
                int waterHeight = Math.min(height[curInd], height[stack.peek()]) - height[indOfheightBase];
                result += waterHeight * distance;
            }
            stack.push(curInd++);
        }
        return result;
    }
    @Test
    public void test2 () {
        int[] height = new int[]{2,1,0,2,3};
        System.out.print("Original array: ");
        for (Integer num : height) {
            System.out.print(num + " ");
        }
        int res = trap2(height);
        System.out.println();
        System.out.print("Result2: " + res);
        System.out.println();
        height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.print("Original array: ");
        for (Integer num : height) {
            System.out.print(num + " ");
        }
        res = trap2(height);
        System.out.println();
        System.out.print("Result2: " + res);
        System.out.println();

    }
    private int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;
        int res = 0;
        //stack中存入index of position
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            int curHigh = height[i];
            while (!stack.isEmpty() && curHigh > height[stack.peek()]) {
                int lowerHeight = height[stack.pop()];
                if (stack.isEmpty()) break;
                int upperHeight = Math.min(height[stack.peek()], height[i]);
                int waterHeight = upperHeight - lowerHeight;
                res += waterHeight * (i - stack.peek() - 1);
            }
            stack.push(i);
        }
        return res;
    }

    @Test
    public void test3 () {
        int[] height = new int[]{0,1,0,9,1,0,1,3,2,1,2,1};
        System.out.print("Original array: ");
        for (Integer num : height) {
            System.out.print(num + " ");
        }
        int res = trap3(height);
        System.out.println();
        System.out.print("Result3: " + res);
        System.out.println();
        height = new int[]{0,1,0,9,1,0,1,3,2,1,2,1};
        System.out.print("Original array: ");
        for (Integer num : height) {
            System.out.print(num + " ");
        }
        res = trap3(height);
        System.out.println();
        System.out.print("Result3: " + res);

    }

    //S3: two pointers
    public int trap3(int[] height) {
        if (height == null || height.length == 0) return 0;
        int res = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;

        while (left <= right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);

            if (leftMax <= rightMax) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;

//        while (left < right) {
//            if (height[left] <= height[right]) {
//                if (height[left] >= leftMax) {
//                    leftMax = height[left];
//                } else {
//                    res += leftMax - height[left];
//                }
//                left++;
//            } else {
//                if (height[right] >= rightMax) {
//                    rightMax = height[right];
//                } else {
//                    res += rightMax - height[right];
//                }
//                right--;
//            }
//        }
//        return res;
    }

}
