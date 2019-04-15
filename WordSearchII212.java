package TrieRelated;

/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example:

Input:
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Output: ["eat","oath"]
Note:
You may assume that all inputs are consist of lowercase letters a-z.
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII212 {

    @Test
    public void test() {
        char[][] board = new char[][]{{'o','a','a','n'}, {'e','t','a','e'},
                {'i','h','k','r'}, {'i','f','l','v'}};

        String[] words = new String[]{"oath","pea","eat","rain"};

        List<String> res = findWords(board, words);
        System.out.println(res);
    }
    //Trie
    //time : O(m * n * TrieNode)
    //     space : TrieNode
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWordsHelper(board, i, j, root, res);
            }
        }
        return res;
    }
    private void findWordsHelper(char[][] board, int curRow, int curCol, TrieNode node, List<String> res) {
        if (curRow < 0 || curRow >= board.length || curCol < 0 || curCol >= board[0].length) return;

        char c = board[curRow][curCol];
        if (c == '#' || node.children[c - 'a'] == null) return;
        TrieNode newNode = node.children[c - 'a'];
        if (newNode.word != null) {
            res.add(newNode.word);
            newNode.word = null;
        }
        board[curRow][curCol] = '#';

        findWordsHelper(board, curRow - 1, curCol, newNode, res);
        findWordsHelper(board, curRow + 1, curCol, newNode, res);
        findWordsHelper(board, curRow, curCol - 1, newNode, res);
        findWordsHelper(board, curRow, curCol + 1, newNode, res);

        board[curRow][curCol] = c;

    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int pos = c - 'a';
                if (node.children[pos] == null) {
                    node.children[pos] = new TrieNode();
                }
                node = node.children[pos];
            }
            node.word = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] children;
        String word;
        public TrieNode() {
            children = new TrieNode[26];

        }
    }
}
