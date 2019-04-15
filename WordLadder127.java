package BFSRelated;
/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 */

import java.util.*;

public class WordLadder127 {
    public static void main(String[] args) {
        String beginWord = "hit";
        //String endWord = "ait";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
//        wordList.add("aic");
//        wordList.add("aio");
//        wordList.add("ait");
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        WordLadder127 wl127 = new WordLadder127();
        int result = wl127.ladderLength(beginWord, endWord, wordList);
        System.out.println(result);

    }

/*
时间复杂度是个坑，正确答案是：
如果用了set.remove去重, 时间复杂度是O(V), 因为一旦一个node被摸到，就从set中被拿掉了
如果用visited来判断重复，是O(V+E),因为虽然没放到queue里，但是摸了一下该node,也就是产生了一个边的时间
*/

    //BFS
    //Time: O(V), V is number of nodes
    //Space: O(V)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) {
            return -1;
        }
        HashSet<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int minLen = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String curStr = queue.poll();
                char[] curStrChar = curStr.toCharArray();
                for (int i = 0; i < curStr.length(); i++) {
                    char temp = curStrChar[i];
                    for (char tmpChar = 'a'; tmpChar <= 'z'; tmpChar++) {
                        curStrChar[i] = tmpChar;
                        String nextWord = String.valueOf(curStrChar);
                        if (temp != tmpChar && set.contains(nextWord)) {
                            if (nextWord.equals(endWord)) {
                                return minLen + 1;
                            }
                            queue.offer(nextWord);
                            set.remove(nextWord);//相当于为以后去除重复
                        }
                    }
                    curStrChar[i] = temp;
                }
            }
            minLen++;
        }
        return 0;
    }
}
