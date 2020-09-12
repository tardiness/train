package com.tardiness.algorithm.labuladong0;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/7/31 11:52
 * @Description: 全排列
 */
public class Permute {

    private List<List<Integer>> res = new LinkedList<>();

    public static void main(String[] args) {
        Permute permute = new Permute();
        List<Integer> nums = new LinkedList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        List<List<Integer>> lists ;
        lists = permute.permuteGuid(nums);
        for (List<Integer> numList:lists) {
            for (Integer num:numList) {
                System.out.print(num);
            }
            System.out.println();
        }
    }

    public List<List<Integer>> permuteGuid(List<Integer> nums) {
        List<Integer> track = new LinkedList<>();
        backTrack(nums,track);
        return res;
    }

    /**
     * 回溯
     * @param nums
     * @param track
     */
    private void backTrack(List<Integer> nums, List<Integer> track) {
        if (track.size() == nums.size()) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (Integer num:nums) {
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            backTrack(nums,track);
            //撤销选择
            track.remove(track.size()-1);
        }
    }
}
