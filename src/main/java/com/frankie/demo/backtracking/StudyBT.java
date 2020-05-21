package com.frankie.demo.backtracking;

/**
 * @author: Yao Frankie
 * @date: 2020/5/21 16:47
 */

import java.util.LinkedList;
import java.util.List;

/**
 * https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/hui-su-suan-fa-xiang-jie-xiu-ding-ban
 */
public class StudyBT {

    private static List<List<Integer>> ret = new LinkedList<>();

    public static void main(String[] args) {
        fullArrange();
    }

    private static void fullArrange() {
        int[] nums = {1, 2, 3};
        // Selected list.
        LinkedList<Integer> selectedList = new LinkedList<>();
        doFullArrange(nums, selectedList);
        for (List<Integer> l: ret){
            System.out.println(l);
        }
    }

    private static void doFullArrange(int[] nums, LinkedList<Integer> selectedList) {
        // Break condition.
        if (selectedList.size() == nums.length){
            ret.add(new LinkedList<>(selectedList));
            return;
        }

        for (int n: nums){
            // Add unique element into selected list.
            if (selectedList.contains(n)){
                continue;
            }
            selectedList.add(n);
            // Move to next level.
            doFullArrange(nums, selectedList);
            // Remove last element from selected list.
            selectedList.removeLast();
        }
    }
}
