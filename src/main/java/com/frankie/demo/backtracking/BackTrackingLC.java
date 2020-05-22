package com.frankie.demo.backtracking;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/5/22 14:25
 */
public class BackTrackingLC {

    public static void main(String[] args) {
//        p17();
        p93();
    }

    private static List<String> ipAns = new LinkedList<>();

    /**
     * 93. Restore IP Addresses
     */
    private static void p93() {
        String s = "25525511135";
        restoreIpAddresses(s, new StringBuilder(), 0);
        System.out.println(ipAns);
    }

    private static void restoreIpAddresses(String s, StringBuilder ready, int count) {
        if (count == 4 && s.length() == 0){
            ipAns.add(ready.toString());
            return;
        }

        for (int i = 0; i < s.length() && i <= 2; i++){
            if (i != 0 && s.charAt(i) == '0'){
                break;
            }
            String part = s.substring(0, i + 1);
            if (Integer.parseInt(part) <= 255){
                if (ready.length() != 0){
                    part = "." + part;
                }
                ready.append(part);
                restoreIpAddresses(s.substring(i + 1), ready, count + 1);
                ready.delete(ready.length() - part.length(), ready.length());
            }
        }
    }

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private static List<String> letterAns = new LinkedList<>();
    /**
     * 17. Letter Combinations of a Phone Number
     */
    private static void p17() {
        String digits = "23";
        StringBuilder ready = new StringBuilder();
        letterCombinations(digits, ready);
        System.out.println(letterAns);
    }

    private static void letterCombinations(String digits, StringBuilder ready) {
        if (ready.length() == digits.length()){
            letterAns.add(new String(ready));
            return;
        }

        int curDigit = digits.charAt(ready.length()) - '0';
        for (char c: KEYS[curDigit].toCharArray()){
            ready.append(c);
            letterCombinations(digits, ready);
            ready.deleteCharAt(ready.length() - 1);
        }
    }
}
