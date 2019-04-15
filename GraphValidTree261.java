package GraphRelated;

/*
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.


 */

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GraphValidTree261 {
    @Test
    public void test() { //dfs
        int[][] edges = new int[][]{{0, 1}, {1, 2}};
        int n = 3;
        boolean res = validTree1(n, edges);
        System.out.println(res);
    }

    //dfs
    //Time: O(edges * nodes)
    //Space: O(nodes)
    public boolean validTree1(int n, int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
            return true;
        }

        //creating graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        boolean res = validTree1Helper(graph, visited, 0, -1); //first parent is -1

        if (res == false) return res;

        return visited.size() == n;
    }

    private boolean validTree1Helper(List<List<Integer>> graph, HashSet<Integer> visited, int curNodeVal, int parent) {
        List<Integer> nodes = graph.get(curNodeVal);

        for (Integer nodeVal : nodes) {
            if (nodeVal == parent) continue;
            if (visited.contains(nodeVal)) return false;
            visited.add(nodeVal);
            boolean res = validTree1Helper(graph, visited, nodeVal, curNodeVal);
            if (res == false) return false;
        }

        return true;
    }


    @Test
    public void test2() { //dfs
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        int n = 5;
        boolean res = validTree1(n, edges);
        System.out.println(res);
    }

    //dfs
    //Time:
    //Space:
//    public boolean validTree2(int n, int[][] edges) {
//        if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
//            return true;
//        }
//
//        //creating graph
//        List<List<Integer>> graph = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            graph.add(new ArrayList<>());
//        }
//
//        for (int i = 0; i < edges.length; i++) {
//            graph.get(edges[i][0]).add(edges[i][1]);
//            graph.get(edges[i][1]).add(edges[i][0]);
//        }
//
//        HashSet<Integer> visited = new HashSet<>();
//        visited.add(0);
//        boolean res = validTree2Helper(graph, visited, 0, -1); //first parent is -1
//
//        if (res == false) return res;
//
//        return visited.size() == n;
//    }
//
//    private boolean validTree2Helper(List<List<Integer>> graph, HashSet<Integer> visited, int curNodeVal, int parent) {
//        List<Integer> nodes = graph.get(curNodeVal);
//
//        for (Integer nodeVal : nodes) {
//            if (nodeVal == parent) continue;
//            if (visited.contains(nodeVal)) return false;
//            visited.add(nodeVal);
//            boolean res = validTree1Helper(graph, visited, nodeVal, curNodeVal);
//            if (res == false) return false;
//        }
//
//        return true;
//    }





    @Test
    public void test3() { //Union Find
        int[][] edges = new int[][]{{0, 1}, {1, 0}};
        int n = 2;
        boolean res = validTree3(n, edges);
        System.out.println(res);
    }

    //Union Find
    public boolean validTree3(int n, int[][] edges) {
        if (n == 1 && edges.length == 0) return true;
        if (n < 1 || edges == null || edges.length != n - 1) return false;

        int[] roots = new int[n];
        Arrays.fill(roots, -1);

        for (int[] pair : edges) {
            int x = findRoot(roots, pair[0]);
            int y = findRoot(roots, pair[1]);
            if (x == y) return false;
            roots[x] = y;
        }
        return true;
    }

    private int findRoot(int[] roots, int i) {
        while (roots[i] != -1) {
            i = roots[i];
        }
        return i;

    }
}
