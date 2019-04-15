package DFSRelated;

import java.util.ArrayList;

import java.util.List;

public class CanIWin464 {
    public static void main(String[] args) {
        int maxChoosableInteger = 10;
        int desiredTotal = 40;
        CanIWin464 ciw464 = new CanIWin464();
        boolean res = ciw464.canIWin(maxChoosableInteger, desiredTotal);
        System.out.println(res);
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger <= 0 || maxChoosableInteger > 20 || desiredTotal < 0
            || desiredTotal > 300) return false;
//        //HashSet<Integer> set = new HashSet<>();
//        List<Integer> list = new ArrayList<>();
//        Boolean[] cache = new Boolean[maxChoosableInteger];
//        for (int i = 1; i <= maxChoosableInteger; i++) {
//            list.add(i);
//        }
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) return false;
        int map = ( 1 << maxChoosableInteger) - 1;
        Boolean[] memo = new Boolean[map + 1];
        return canIWinHelper(maxChoosableInteger, desiredTotal, 0, map, memo);
    }
    private boolean canIWinHelper(int maxChoosableInteger, int desiredTotal, int curTotal,
                                  int map, Boolean[] memo) {
        if (memo[map] != null) {
            return memo[map];
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            int mask = 1 << (i - 1);
            if ((mask & map) != 0) {
                if (curTotal + i >= desiredTotal) {
                    return true;
                }

            }
            int newMap = map - mask;
            if (!canIWinHelper(maxChoosableInteger, desiredTotal, curTotal + i,
                    newMap, memo)) {
                memo[map] = true;
                return true;
            }
        }
        memo[map] = false;
        return false;
    }
}
