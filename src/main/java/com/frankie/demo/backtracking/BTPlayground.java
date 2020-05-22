package com.frankie.demo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/5/22 11:03
 */
public class BTPlayground {

    private static List<List<Integer>> ans = new ArrayList<>();

    public static void main(String[] args) {
//        playPermutation20200522();
        permuteUsingInsertion();
    }

    private static void permuteUsingInsertion() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> ret = doPermuteUsingInsertion(nums, nums.length - 1);
        for (List<Integer> tmp: ret){
            System.out.println(tmp);
        }
    }

    /**
     *
     * @param nums
     * @param end：当前新增数字的位置
     */
    private static List<List<Integer>> doPermuteUsingInsertion(int[] nums, int end) {

        if (end == 0){
            List<List<Integer>> ans = new LinkedList<>();
            LinkedList<Integer> tmpList = new LinkedList<>();
            tmpList.add(nums[0]);
            ans.add(tmpList);
            return ans;
        }

        List<List<Integer>> ret = doPermuteUsingInsertion(nums, end - 1);
        int curSize = ret.size();
        for (int i = 0; i < curSize; i++){
            for (int j = 0; j <= end; j++){
                LinkedList<Integer> tmpList = new LinkedList<>(ret.get(i));
                tmpList.add(j, nums[end]);
                ret.add(tmpList);
            }
        }

        for (int i = 0; i < curSize; i++){
            ret.remove(0);
        }

        return ret;
    }


    private static void playPermutation20200522() {
        int[] nums = {1};
        LinkedList<Integer> selectedList = new LinkedList<>();
        permutation(nums, selectedList);
        printAns();
    }

    private static void printAns() {
        for (int i = 0; i < ans.size(); i++){
            System.out.println(ans.get(i));
        }
    }

    private static void permutation(int[] nums, LinkedList<Integer> selectedList) {
        if (selectedList.size() == nums.length){
            ans.add(new LinkedList<>(selectedList));
            return;
        }
        for (int n: nums){
            if (selectedList.contains(n)){
                continue;
            }
            selectedList.add(n);
            permutation(nums, selectedList);
            selectedList.removeLast();
        }
    }
}
