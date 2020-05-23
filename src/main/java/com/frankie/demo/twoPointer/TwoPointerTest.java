package com.frankie.demo.twoPointer;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/5/14 15:27
 */
public class TwoPointerTest {

    public static void main(String[] args) {
//        p167();
//        p633();
//        p345();
//        p680();
        p125();
    }

    /**
     * 125. Valid Palindrome
     */
    private static void p125() {
        String s = "A man, a plan, a canal: Panama";
        boolean ret1 = isPalindrome125(s);
        System.out.println(ret1);
    }

    private static boolean isAlphanumeric(String s) {
        return s.matches("^[a-zA-Z0-9]+$");
    }

    /**
     * https://leetcode.com/problems/valid-palindrome/discuss/40029/Accepted-pretty-Java-solution(271ms)
     */
    private static boolean isPalindrome125(String s) {
        s = s.toLowerCase();
        int lo = 0, len = s.length(), hi = len - 1;
        while (lo < hi){
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(lo))){
                lo++;
            }
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(hi))){
                hi--;
            }

            if (Math.abs(s.charAt(lo) - s.charAt(hi)) != 0){
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    /**
     * 680. Valid Palindrome II
     */
    private static void p680() {
//        String s1 = "aba";
//        boolean ret1 = validPalindrome(s1);
//        System.out.println(ret1);
//        String s2 = "abca";
//        boolean ret2 = validPalindrome(s2);
//        System.out.println(ret2);
        String s3 = "abc";
//        boolean ret3 = validPalindrome(s3);
//        System.out.println(ret3);

        boolean ret4 = validPalindrome20200523(s3);
        System.out.println(ret4);
    }

    private static boolean validPalindrome20200523(String s) {
        int lo = 0, hi = s.length() - 1;

        while (lo < hi){
            if (s.charAt(lo) != s.charAt(hi)){
                return isPalindrome(s, lo + 1, hi) || isPalindrome(s, lo, hi - 1);
            }
            lo++;
            hi--;
        }

        return true;
    }

    private static boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi){
            if (s.charAt(lo++) != s.charAt(hi--)){
                return false;
            }
        }
        return true;
    }

    private static boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--){
            // 遇到不相等的两个字符，进行内部校验。
            if (s.charAt(i) != s.charAt(j)){
                return doValidPalindrome(s, i, j - 1) || doValidPalindrome(s, i + 1, j);
            }
        }
        return true;
    }

    private static boolean doValidPalindrome(String s, int lo, int hi) {
        while (lo < hi){
            if (s.charAt(lo) != s.charAt(hi)){
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    /**
     * 345. Reverse Vowels of a String
     */
    private static void p345() {
//        String s1 = "leetcode";
//        String ret1 = reverseVowelsUsingStack(s1);
//        System.out.println(ret1);
//
//        String ret2 = reverseVowels(s1);
//        System.out.println(ret2);
//        charArrayTest();

        String s3 = "hello";
        String ret1 = reverseVowels20200523(s3);
        System.out.println(ret1);
    }

    /**
     * https://leetcode.com/problems/reverse-vowels-of-a-string/discuss/81225/Java-Standard-Two-Pointer-Solution
     * @param s
     * @return
     */
    private static String reverseVowels20200523(String s) {
//        HashSet<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        String vowels = "aeiouAEIOU";
        char[] arr = s.toCharArray();
        int lo = 0, len = arr.length, hi = len - 1;
        while (lo < hi){
            // Find the leftmost vowel.
            while (lo < hi && vowels.indexOf(arr[lo]) == -1){
                lo++;
            }

            // Find the rightmost vowel.
            while (lo < hi && vowels.indexOf(arr[hi]) == -1){
                hi--;
            }

            if (lo < hi){
                char tmp = arr[lo];
                arr[lo]  = arr[hi];
                arr[hi]  = tmp;
                lo++;
                hi--;
            }
        }

        return new String(arr);
    }

    private static void charArrayTest() {

        char[] arr = new char[10];
        arr[0] = 'a';
        arr[1] = 'b';
        arr[2] = 'c';
        arr[1] = 'd';
        System.out.println(new String(arr));
    }

    private static String reverseVowels(String s) {
        if (s == null || s.length() == 0){
            return "";
        }
        char[] chars = new char[s.length()];
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        int lo = 0, hi = s.length() - 1;
        while (lo <= hi){
            char clo = s.charAt(lo);
            char chi = s.charAt(hi);

            if (!vowels.contains(clo)){
                chars[lo++] = clo;
            } else if (!vowels.contains(chi)){
                chars[hi--] = chi;
            } else {
                chars[lo++] = chi;
                chars[hi--] = clo;
            }
        }
        return new String(chars);
    }

    private static String reverseVowelsUsingStack(String s) {
        if (s == null || s.length() == 0){
            return "";
        }
        ArrayDeque<Character> stack = new ArrayDeque<>();
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');


        for (char c: s.toCharArray()){
            if (vowels.contains(c)){
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()){
            if (vowels.contains(c)){
                sb.append(stack.pop());
            } else {
                sb.append(c);
            }
        }
        return new String(sb);
    }

    /**
     * 633. Sum of Square Numbers
     */
    private static void p633() {
//        int c1 = 5;
//        boolean ret1 = judgeSquareSum(c1);
//        System.out.println(ret1);
//        int c2 = 1;
//        boolean ret2 = judgeSquareSum(c2);
//        System.out.println(ret2);
//        int c3 = 0;
//        boolean ret3 = judgeSquareSum(c3);
//        System.out.println(ret3);
//        int c4 = 100000;
//        boolean ret4 = judgeSquareSum(c4);
//        System.out.println(ret4);
//        int c5 = 9;
//        boolean ret5 = judgeSquareSum(c5);
//        System.out.println(ret5);
//        int c6 = 3;
//        boolean ret6 = judgeSquareSum(c6);
//        System.out.println(ret6);
        boolean ret7 = judgeSquareSumAmazing(5);
        System.out.println(ret7);
    }

    private static boolean judgeSquareSumAmazing(int c) {
        for (int i = 0; i < (int) Math.sqrt(c); i++){
            double rest = Math.sqrt(c - i * i);
            if (Math.floor(rest) == rest) {
                return true;
            }
        }
        return false;
    }

    private static boolean judgeSquareSum(int c) {
        int lo = 0, hi = (int) Math.sqrt(c);
        while (lo <= hi){
            int squareSum = lo * lo + hi * hi;
            if (squareSum == c){
                return true;
            } else if (squareSum > c){
                hi--;
            } else {
                lo++;
            }
        }
        return false;
    }

    /**
     * 167. Two Sum II - Input array is sorted
     */
    private static void p167() {
        int[] numbers = {2, 3, 6, 8};
        int target = 9;
//        int[] ret1 = twoSum(numbers, target);
//        System.out.println(Arrays.toString(ret1));

        int[] ret2 = twoSumUsingWhile(numbers, target);
        System.out.println(Arrays.toString(ret2));
    }

    /**
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/51253/A-simple-O(n)-solution
     */
    private static int[] twoSumUsingWhile(int[] numbers, int target) {
        int lo = 0, hi = numbers.length - 1;
        while (numbers[lo] + numbers[hi] != target){
            if (numbers[lo] + numbers[hi] < target){
                lo++;
            } else {
                hi--;
            }
        }
        return new int[]{lo + 1, hi + 1};
    }

    private static int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                if (numbers[i] + numbers[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

}
