package com.frankie.demo.justForFun.week24;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0611 {

    public static void main(String[] args) {
//        p394(); // 394. Decode String
        p127(); // 127. Word Ladder
    }

    private static void p127() {
        String beginWord = "hot";
        String endWord = "dog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dog");
        int a = ladderLength(beginWord, endWord, wordList);
        System.out.println(a);
    }

    private static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Early break.
        if (!wordList.contains(endWord)) return 0;
        int ans = 0;
        ArrayDeque<String> q = new ArrayDeque<>();
        q.offer(beginWord);

        while (!q.isEmpty()){
            ans++;
            int size = q.size();
            while (size-- > 0){
                String cur = q.poll();
                char[] ch = cur.toCharArray();
                for (int i = 0; i < cur.length(); i++){
                    char back = ch[i];
                    for (char c = 'a'; c <= 'z'; c++){
                        if (c == back) continue;
                        ch[i] = c;
                        String tmp = new String(ch);
                        if (tmp.equals(endWord)) return ans + 1;
                        if (!wordList.contains(tmp)) continue;
                        wordList.remove(tmp);
                        q.offer(tmp);
                    }
                    ch[i] = back;
                }
            }
        }
        return 0;
    }

    private static void p394() {
        String s = "3[a]2[bc]";
        String a = decodeString(s);
        System.out.println(a);
    }

    static int i = 0;

    private static String decodeString(String s) {
        int len = s.length();
        int times = 0;
        StringBuilder sb = new StringBuilder();

        while (i < len){

            // digit
            while (i < len && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                times = times * 10 + s.charAt(i) - '0';
                i++;
            }

            // [
            if (s.charAt(i) == '['){
                i++;
                String tmp = decodeString(s);

                // Process times
                while (times-- > 0){
                    sb.append(tmp);
                }
                times++;
            }

            // ]
            else if (s.charAt(i) == ']'){
                i++;
                return new String(sb);
            }

            // letters
            else {
                sb.append(s.charAt(i));
                i++;
            }
        }

        return new String(sb);
    }
}