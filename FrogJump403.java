package DFSRelated;
/*
A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping
1 unit to the 2nd stone, then 2 units to the 3rd stone, then
2 units to the 4th stone, then 3 units to the 6th stone,
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as
the gap between the 5th and 6th stone is too large.

 */

import java.util.HashMap;

public class FrogJump403 {
    public static void main(String[] args) {
        int[] stones = new int[]{0,1,2,3,4,8,9,11};
        FrogJump403 fj403 = new FrogJump403();
        boolean res = fj403.canCross(stones);
        System.out.println(res);
    }
    //DFS
    //Time: ???
    //Space: O(n)
    //还可以用Binary search and DP: https://leetcode.com/articles/frog-jump/
    public boolean canCross(int[] stones) {
        if (stones == null) return false;
        if (stones.length < 2) return true;
        if (stones[1] - stones[0] > 2) return false;
        HashMap<Integer, Boolean>[] cache = new HashMap[stones.length];
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new HashMap<Integer, Boolean>();
        }
        return canCrossHelper(stones, cache, 0, 0);
    }
    private boolean canCrossHelper(int[] stones, HashMap<Integer, Boolean>[] cache,
                                   int idx, int k) {

        HashMap<Integer, Boolean> tmpCache = cache[idx];
        if (tmpCache.containsKey(k)) return tmpCache.get(k);

        if (idx == stones.length - 1) return true;

        for (int i = idx + 1; i < stones.length; i++) {
            int dist = stones[i] - stones[idx];
            if (dist < k - 1) {
                continue;
            } else if (dist > k + 1) {
                break;
            } else { //k - 1 <= dist <= k + 1
                if (canCrossHelper(stones, cache, i, dist)) {
                    tmpCache.put(k, true); // k is the key
                    return true;
                }
            }
        }
        tmpCache.put(k, false);
        return false;
    }
}
