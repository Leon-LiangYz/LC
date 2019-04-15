package ArrayRelated;
/*Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
*/

public class JumpGame55 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        JumpGame55 jg55 = new JumpGame55();
        boolean dfsRes = jg55.canJumpDFS(nums);
        System.out.println("dfsRes: " + dfsRes);
        boolean DPFromEnd = jg55.canJumpDFS(nums);
        System.out.println("DPFromEnd: " + DPFromEnd);
        boolean greedy = jg55.canJumpGreedy(nums);
        System.out.println("DPFromEnd: " + greedy);
    }
    //DFS, time O(k^n), k is max number in the array, system space: O(n)
    public boolean canJumpDFS(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        return canJumpDFSHelper(nums, 0);

    }
    private boolean canJumpDFSHelper(int[] nums, int pos) {
        if (pos >= nums.length - 1) return true;
        int curMaxSteps = nums[pos];
        for (int i = 1; i <= curMaxSteps; i++) {
            if (canJumpDFSHelper(nums, pos + i)) return true;
        }
        return false;
    }

    //DP from End, time: O(kN), k is max number in the array
    //space:
    //dp[i] = true, if there is a j, i < j <= i + array[i](不能出界) && dp[j] == true
    //false, elsewhere
    //
    public boolean canJumpDPFromEnd(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j <= i + nums[i]; j++) {
                if (j < nums.length && dp[j] == true) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    //S3: Greedy, Time: O(n)
    public boolean canJumpGreedy(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, i + nums[i]);
            if (max >= nums.length - 1) return true;
            if (i > max) return false;
        }
        return false;
    }

}
