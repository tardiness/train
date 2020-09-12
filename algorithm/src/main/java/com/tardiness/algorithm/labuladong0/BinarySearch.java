package com.tardiness.algorithm.labuladong0;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/8/3 11:32
 * @Description: 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
//        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        int[] nums = new int[]{1,1,2,2,2,3,3,3,3,3,4,5,8};
        BinarySearch binarySearch = new BinarySearch();
        //普通二分
//        int index = binarySearch.normal(nums,4);
        //左边界二分
        int index = binarySearch.leftBound(nums,0);
        //右边界二分
//        int index = binarySearch.rightBound(nums,0);
        System.out.println("index :" +index);
    }

    /**
     * 普通二分
     * @param nums
     * @param target
     * @return
     */
    private int normal(int[] nums,int target) {
        int left=0;
        int right = nums.length-1;
        int mid=0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid -1;
            }
        }
        return left;
    }

    /**
     * 左边界
     * @param nums
     * @param target
     * @return
     */
    private int leftBound(int[] nums,int target) {
        int left=0;
        int right = nums.length;
        int mid=0;
        //[left,right) 左闭右开
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if (nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 👉边界
     * @param nums
     * @param target
     * @return
     */
    private int rightBound(int[] nums,int target) {
        int left=0;
        int right = nums.length;
        int mid=0;
        //[left,right) 左闭右开
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left - 1;
    }
}
