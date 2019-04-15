package HeapAndPriorityQueueRelated;

/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.

 */

import org.junit.Test;

import java.util.*;

public class ReconstructItinerary332 {

    @Test
    public void test() {
        String[][] tickets = new String[][]{{"MUC", "ABC"}, {"JFK", "MUC"}, {"MUC", "GFG"}, {"ABC", "GFG"}};
        List<String> res = findItinerary(tickets);
        System.out.println(res);

        String s1 = "GFG";
        String s2 = "ABC";
        System.out.println(s2.compareTo(s1));
    }

    //
    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (String[] ticket : tickets) {
            map.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).offer(ticket[1]);
        }
        helper("JFK", map, res);
        return res;
    }
    private void helper(String airport, HashMap<String, PriorityQueue<String>> map, List<String> res) {
        while (map.containsKey(airport) && !map.get(airport).isEmpty()) {
            helper(map.get(airport).poll(), map, res);
        }
        res.add(0, airport);
    }


    @Test
    public void test2() {
        String[][] tickets = new String[][]{{"MUC", "ABC"}, {"JFK", "MUC"}, {"MUC", "GFG"}, {"ABC", "GFG"}};
        List<String> res = findItinerary2(tickets);
        System.out.println(res);

//        String s1 = "GFG";
//        String s2 = "ABC";
//        System.out.println(s2.compareTo(s1));
    }

    //Time: up to O(nlogn) ???
    //Space: O(n)

    public List<String> findItinerary2(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (String[] ticket : tickets) {
            map.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).offer(ticket[1]);
        }

        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            while (map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty()) {
                stack.push(map.get(stack.peek()).poll());
            }
            res.add(0, stack.pop());
        }
        return res;
    }


}
