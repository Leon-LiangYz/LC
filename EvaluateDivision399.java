package GraphRelated;

/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction
 */

import org.junit.Test;

import java.util.*;

public class EvaluateDivision399 {
    @Test
    public void test3() {
        String s1 = "ab";
        System.out.println(s1.substring(0));
    }

    @Test
    public void test() {
        String[][] equations = new String[][]{{"a", "b"}, {"b", "c"}};
        double[] values = new double[]{2.0, 3.0};
        String[][] queries = new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        double[] res = calcEquation(equations, values, queries);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    class GraphNode {
        String node;
        double result;
        GraphNode(String node, double result) {
            this.node = node;
            this.result = result;
        }

    }
    //S1: DFS
    //Time: O(V+E), E is length of equations
    //Space: O(N)
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        HashMap<String, List<GraphNode>> map = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            createGraph(equation[0], equation[1], values[i], map);
        }

        double[] res = new double[queries.length];

        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            res[i] = calculate(query[0], query[1], 1.0, map, new HashSet<>());
        }
        return res;
    }

    private double calculate(String numerator, String denominator, double baseValue,
                             HashMap<String, List<GraphNode>> map, HashSet<String> visited) {
        if (visited.contains(numerator)) return -1;
        if (!map.containsKey(numerator)) return -1;

        if (numerator.equals(denominator)) return baseValue;
        visited.add(numerator);

        for (GraphNode neighbor : map.get(numerator)) {
            double result = calculate(neighbor.node, denominator, baseValue * neighbor.result, map, visited);
            if (result != -1.0) {
                return result;
            }
        }
        visited.remove(numerator);
        return -1;
    }

    private void createGraph(String numerator, String denominator,
                             double result, HashMap<String, List<GraphNode>> map) {
        if (!map.containsKey(numerator)) {
            map.put(numerator, new ArrayList<>());
        }
        map.get(numerator).add(new GraphNode(denominator, result));


        if (!map.containsKey(denominator)) {
            map.put(denominator, new ArrayList<>());
        }
        map.get(denominator).add(new GraphNode(numerator, 1 / result));



    }
}
