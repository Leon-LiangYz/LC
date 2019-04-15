package DPRelated;

/*
http://www.geeksforgeeks.org/dynamic-programming-set-36-cut-a-rope-to-maximize-product/
Given a rope of length n meters, cut the rope in different parts of integer lengths in a way that maximizes product of lengths of all parts. You must make at least one cut. Assume that the length of rope is more than 2 meters

 */

public class CutRope {
    public static void main(String[] args) {
        int len = 18;
        CutRope cr = new CutRope();
        System.out.println(cr.cutRopeMax(len));
    }
    public int cutRopeMax(int ropeLen) {
        if (ropeLen <= 2) return ropeLen;
        //dp[i] maximum product of all parts when you handle i meters scope with one cut
        int[] dp = new int[ropeLen + 1];
        dp[0] = 1;
        dp[1] = 1;
        int max = 0;
        for (int i = 2; i <= ropeLen; i++) {
            for (int j = 2; j <= i / 2; j++) {

                max = Math.max(max, Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
            dp[i] = max;
        }
        return dp[ropeLen];
    }
}
