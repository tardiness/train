package com.tardiness.algorithm.labuladong1;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/8/13 11:57
 * @Description: 最长递增子序列
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        int[] array = new int[]{10,9,2,5,3,7,101,18};
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
//        Integer maxLength = lengthOfLIS.getMaxLength(array);
        Integer maxLength = lengthOfLIS.maxLengthBinarySearch(array);
        System.out.println("maxLength :" + maxLength);
    }

    /**
     * 获得最长递增子序列 (动态规划)
     */
    private Integer getMaxLength (int[] array) {
        int[] dp = new int[array.length];
        //base case
        for (int i=0;i<dp.length;i++) {
            dp[i] = 1;
        }

        for (int i=0;i<array.length;i++) {
            for (int j=0;j<i;j++) {
                if (array[i] > array[j]) {
                    dp[i] = Integer.max(dp[i],dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i=0;i<array.length;i++) {
            res = Integer.max(res,dp[i]);
        }
        return res;
    }

    /**
     * 最长递增子序列(二分法)
     * @param array
     * @return
     */
    private Integer maxLengthBinarySearch(int[] array) {
        //扑克牌每次只和最上边的比较
        int[] top = new int[array.length];
        //poker 分了几组
        int group = 0;
        int poker;

        int left,right,mid;
        for (int i=0;i<array.length;i++) {
            poker = array[i];
            left=0;
            right = group;
            while (left < right) {
                mid = (left + right) / 2;
                if (poker < top[mid]) {
                    right = mid;
                } else if (poker > top[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            System.out.println(" poker:"+poker+"; left:"+left);
            if (left == group) {
                group++;
            }
            top[left] = poker;
        }
        return group;
    }

}
