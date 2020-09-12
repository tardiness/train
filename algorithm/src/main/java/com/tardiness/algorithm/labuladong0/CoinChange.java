package com.tardiness.algorithm.labuladong0;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/7/30 11:22
 * @Description: 凑零钱
 */
public class CoinChange {

    private int[] coins = new int[]{1,3,5};

    private Map<Integer,Integer> visited = new HashMap<>();

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        long begin = System.currentTimeMillis();
        //dps  花费时间: 算不出来
        System.out.println(coinChange.recursiveDp(11));
        //备忘录递归   花费时间: 12-14ms (有可能内存不够)
//        System.out.println(coinChange.bakDp(5111));
        //dp数组 花费时间: 1 ms
//        System.out.println(coinChange.dpTable(5111));
        System.out.println("花费时间:"+(System.currentTimeMillis()-begin)+" ms");

    }

    /**
     * 暴力递归
     * @param amount
     * @return
     */
    public Integer recursiveDp(Integer amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int midTemp;
        int min = Integer.MAX_VALUE;
        for (int coin:coins) {
            midTemp = recursiveDp(amount - coin);
            if (midTemp == -1) {
                continue;
            }

            if (min > midTemp+1) {
                min = midTemp+1;
            }

        }

        return min > 0 && min < Integer.MAX_VALUE? min: -1;
    }

    /**
     * 备忘录递归
     * @param amount
     * @return
     */
    public Integer bakDp(Integer amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        if (visited.containsKey(amount)) {
            return visited.get(amount);
        }

        int midTemp;
        int res = Integer.MAX_VALUE;
        for (int coin :coins) {
            midTemp = bakDp(amount - coin);
            if (midTemp == -1) {
                continue;
            }
            res = Integer.min(res,1+midTemp);
        }

        res = res > 0 && res < Integer.MAX_VALUE ? res:-1;
        visited.put(amount,res);
        return res;
    }

    /**
     * 动态规划 dp数组
     * @param amount
     * @return
     */
    public Integer dpTable(Integer amount) {
        int[] dp = new int[amount+1];
        for (int i=1;i<=amount;i++) {
            dp[i] = amount+1;
        }
        dp[0]=0;

        for (int i=0;i<=amount;i++) {
            for (int coin:coins) {
                if (i-coin < 0) {
                    continue;
                }
                dp[i] = Integer.min(dp[i],1+dp[i-coin]);
            }
        }

        return dp[amount] == amount+1 ? -1 : dp[amount];
    }
}
