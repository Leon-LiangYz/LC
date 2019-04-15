package ArrayRelated;

/*
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagram49 {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagram49 ga49 = new GroupAnagram49();
        List<List<String>> result1 = ga49.groupAnagrams(strs);
        System.out.println(result1);

        List<List<String>> result2 = ga49.groupAnagrams2(strs);
        System.out.println(result2);

    }
    //Time: O(Nklogk), N: length of string array; k : max length of the string in the string array
    public List<List<String>> groupAnagrams(String[] strs) {
        //Time: O(Nklogk), N: length of string array; k : max length of the string in the string array
        List<List<String>> res = new ArrayList<>();
        if(strs == null || strs.length == 0) return res;
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] tmp = str.toCharArray();
            Arrays.sort(tmp);
            String key = String.valueOf(tmp);
            map.putIfAbsent(key, new ArrayList<>());
            // if(!map.contaisKey(key)){
            //     map.put(key, new ArrayList<>());
            // }
            map.get(key).add(str);
        }
        // for(String str : map.keySet()){
        //     res.add(map.get(str));
        // }
        for(List<String> tmpList : map.values()){
            res.add(tmpList);
        }
        return res;
    }
    public static List<List<String>> groupAnagrams2(String[] strs) {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};//最多10609个z

        List<List<String>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String s : strs) {
            int key = 1;
            for (char c : s.toCharArray()) {
                key *= prime[c - 'a'];
            }
            List<String> t;
            if (map.containsKey(key)) {
                t = res.get(map.get(key));
            } else {
                t = new ArrayList<>();
                res.add(t);
                map.put(key, res.size() - 1);
            }
            t.add(s);
        }
        return res;
    }
}
