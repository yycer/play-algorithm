package com.frankie.demo.justForFun;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Yao Frankie
 * @date: 2020/5/29 15:14
 */
public class GetNextProblem {

    private static final String likePath = "C:\\Users\\15900\\Desktop\\liked.txt";
    private static final String interviewPath = "C:\\Users\\15900\\Desktop\\interview.txt";

    /**
     * Next most important!!!
     * -----------------------------------------------------------------------------
     * 22   // Generate Parentheses         - back tracking
     * 49   // Group Anagrams               - Hash, Array, String
     * 208  // Implement Trie (Prefix Tree) - Trie
     * 105  // Construct Binary Tree from Preorder and Inorder Traversal - Tree
     * 200  // Number of Islands            - DFS
     * 207  // Course Schedule              - Topological Sort / DFS
     * 148  // Sort List                    - LinkedList
     * 139  // Word Break                   - DP
     * 56   // Merge Intervals              - Sweep line
     * 138  // Copy List with Random Pointer - HashTable / LinkedList
     * 79   // Word Search                  - DFS[Depth First Search]
     * 55   // Jump Game                    - Greedy / DP / Back Tracking
     * 146  // LRU Cache                    - HashTable and Double Linked List
     * 152  // Maximum Product Subarray     - DP
     * 5    // Longest Palindromic Substring - DP
     * 15   // 3Sum                         - Sorting and Two Pointer
     * 42   // Trapping Rain Water          - Burte Force / DP / Two Pointer
     * 297  //
     * 295
     * 239
     * 23
     * 84
     * 76
     * 124
     * 41
     * 4
     * 10
     * -----------------------------------------------------------------------------
     */
    public static void main(String[] args) {
        List<String> likeList      = getContent(likePath);
        List<String> interviewList = getContent(interviewPath);
        likeList.retainAll(interviewList);
        System.out.println(likeList.size());
        System.out.println(likeList);
    }

    private static List<String> getContent(String pathstr) {
        Path path = Paths.get(pathstr);
        List<String> ret = new ArrayList<>();
        try {
            ret = Files.lines(path, StandardCharsets.UTF_8).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
