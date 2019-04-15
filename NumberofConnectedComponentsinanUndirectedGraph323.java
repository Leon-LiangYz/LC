package GraphRelated;

/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NumberofConnectedComponentsinanUndirectedGraph323 {

    @Test
    public void test() {
        int n = 5;
        int[][] edges = new int[][]{{0,1}, {0, 2}, {0,3},{1, 4}};

        int res = countComponents(n, edges);
        System.out.println(res);
    }

    //Union Find
    //time : O(edges * nodes)
    //space : O(n)

    public int countComponents(int n, int[][] edges) {
        if (n <= 0) return 0;

        int[] roots = new int[n];
        Arrays.fill(roots, -1);

        int count = n;
        for (int[] pair : edges) {
            int rootOfX = findRoot(roots, pair[0]);
            int rootOfY = findRoot(roots, pair[1]);

            if (rootOfX != rootOfY) {
                count--;
                roots[rootOfX] = rootOfY;
            }


        }
        return count;
    }

    private int findRoot(int[] roots, int id) {
        while (roots[id] != -1) {

            id = roots[id];
        }
        return id;
    }



    @Test
    public void test2() {
        int n = 5;
        int[][] edges = new int[][]{{0,1}, {0, 2}, {0,3},{1, 4}};

        int res = countComponents2(n, edges);
        System.out.println(res);
    }

    //Graph

    public int countComponents2(int n, int[][] edges) {
        //c.c
        List<V> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new V(i));
        }

        for (int i = 0; i < edges.length; i++) {
            V v0 = nodes.get(edges[i][0]);
            V v1 = nodes.get(edges[i][1]);
            v0.neibors.add(v1);
            v1.neibors.add(v0);
        }

        int count = 0;
        for (V node : nodes) {
            if (!node.visited) {
                count++;
                visitChildren(node, nodes);
            }
        }
        return count;
    }

    private void visitChildren(V node, List<V> nodes) {
//        if (node.visited) {
//            return;
//        }
        node.visited = true;
        for (V curNode : node.neibors) {
            if (!curNode.visited) {
                visitChildren(curNode, nodes);
            }
        }

    }

    class V {
        public int label;
        public List<V> neibors;
        public boolean visited;

        public V(int x) {
            this.label = x;
            neibors = new ArrayList<>();
            visited = false;
        }
    }
}
