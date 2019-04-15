package GraphRelated;

import org.junit.Test;

import java.util.*;

public class AlienDictionary269 {

    @Test
    public void test() {
        //String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        String[] words = new String[]{"wr", "er"};
        String res = alienOrder(words);
        System.out.println(res);

    }
    //time : (V + E) -> O(n * words(max))
    // space : O(n) -> O(26) -> O(1)
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        int[] inDegree = new int[26];
        int totCharCounts = 0;
        //initialize in-degree and count total distinct characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (inDegree[c - 'a'] == 0) {
                    inDegree[c - 'a'] = 1;
                    totCharCounts++;
                }
            }
        }

        //creating graph
        HashMap<Character, HashSet<Character>> map = new HashMap<>();

        for (int i = 0; i < words.length - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] next = words[i + 1].toCharArray();
            int minLen = Math.min(cur.length, next.length);
            for (int j = 0; j < minLen; j++) {
                if (cur[j] != next[j]) {
                    if (!map.containsKey(cur[j])) {
                        map.put(cur[j], new HashSet<>());
                    }
                    if (map.get(cur[j]).add(next[j])) {
                        inDegree[next[j] - 'a']++;
                    }
                    break;
                }
            }
        }

        //initialize and setup queue
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (inDegree[i] == 1) {
                queue.offer((char)('a' + i));

            }
        }

        //bfs
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            res.append(c);
            if (map.containsKey(c)) {
                for (Character tmpChar : map.get(c)) {
                    if (--inDegree[tmpChar - 'a'] == 1) {
                        queue.offer(tmpChar);
                    }
                }
            }
        }

        if (res.length() != totCharCounts) return "";
        return res.toString();
    }

    @Test
    public void test2() { //test DFS
        //String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        String[] words = new String[]{"wr", "er"};
        String res = alienOrder2(words);
        System.out.println(res);

    }

    private int curLab = 0;
    private class V {
        public char ch;
        public boolean visited;
        public List<V> neighbors;

        public V(char ch) {
            this.ch = ch;
            this.visited = false;
            this.neighbors = new ArrayList<>();
        }
    }

    public String alienOrder2(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) return words[0];
        List<V> vs = new ArrayList<>();
        V[] graph = new V[26];
        String prev = words[0];
        for (int i = 1; i < words.length; i++) {
            String cur = words[i];
            int idx1 = 0, idx2 = 0;
            boolean valid = false;
            while (idx1 < prev.length() && idx2 < cur.length()) {
                char c1 = prev.charAt(idx1++), c2 = cur.charAt(idx2++);
                if (graph[c1 - 'a'] == null) {
                    graph[c1 - 'a'] = new V(c1);
                    vs.add(graph[c1 - 'a']);
                }

                if (graph[c2 - 'a'] == null) {
                    graph[c2 - 'a'] = new V(c2);
                    vs.add(graph[c2 - 'a']);
                }

                if (c1 != c2) {
                    graph[c1 - 'a'].neighbors.add(graph[c2 - 'a']);
                    valid = true;
                    break;
                }
            }
            while (idx1 < prev.length()) {
                if (!valid) return "";

                char c = prev.charAt(idx1++);
                if (graph[c - 'a'] == null) {
                    graph[c - 'a'] = new V(c);
                    vs.add(graph[c - 'a']);
                }
            }

            while (idx2 < cur.length()) {
                char c = cur.charAt(idx2++);
                if (graph[c - 'a'] == null) {
                    graph[c - 'a'] = new V(c);
                    vs.add(graph[c - 'a']);
                }
            }
            prev = cur;
        }

        char[] res = new char[vs.size()];
        curLab = res.length - 1;
        for (V v : vs) {
            if (!isTopo(res, v, new HashSet<V>())) {
                return "";
            }
        }
        return new String(res);
    }
    private boolean isTopo(char[] res, V cur, HashSet<V> cycle) {
        if (cycle.contains(cur)) return false;
        if (cur.visited) return true;

        cur.visited = true;
        cycle.add(cur);

        for (V neighbor : cur.neighbors) {
            if (isTopo(res, neighbor, cycle)) {
                continue;
            } else {
                return false;
            }
        }

        cycle.remove(cur);
        res[curLab--] = cur.ch;
        return true;
    }

}
