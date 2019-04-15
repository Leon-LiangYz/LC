package BFSRelated;
/*
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input:

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance
             of 2+2+2=6 is minimal. So return 6.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BestMeetingPoint296 {

    @Test
    public void test() {
        int[][] grid = new int[][]{{1,0,0,0,1}, {0,0,0,0,0},{0,0,1,0,0}};
        int res = minTotalDistance(grid);
        System.out.println(res);
    }


    //Time: time : O(m * n)
    //space : < O(n + m)
    //This is the follow up of LC 317
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        List<Integer> rowList = new ArrayList<>();
        List<Integer> colList = new ArrayList<>();

       int rowTot = grid.length;
       int colTol = grid[0].length;
       for (int r = 0; r < rowTot; r++) {
           for (int c = 0; c < colTol; c++) {
               if (grid[r][c] == 1) {
                   rowList.add(r);
               }
           }
       }

       for (int c = 0; c < colTol; c++) {
           for (int r = 0; r < rowTot; r++) {
               if (grid[r][c] == 1) {
                   colList.add(c);
               }
           }
       }

       return getMinDistance(rowList) + getMinDistance(colList);
    }

    /*
如果是在一维array里面找，如何找？1个1，两个1，三个一，四个1，五个一？
    答案是找所有1的中位数
        这个思路可以被利用到二维数组中
            找所有1与x,y轴投影中位数的位置

     */
    private int getMinDistance(List<Integer> list) {
        int start = 0, end = list.size() -1;
        int minDist = 0;
        int median = start + (end - start) / 2;
        for (int i = 0; i < list.size(); i++) {
            minDist += Math.abs(list.get(i) - list.get(median));
        }
        return minDist;
    }

//    private int getMinDistance(List<Integer> list) {
//        int start = 0, end = list.size() -1;
//        int minDist = 0;
//        while (start < end) {
//            minDist += list.get(end--) - list.get(start++);
//        }
//        return minDist;
//    }
}
