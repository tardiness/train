package com.tardiness.leetcode.daily;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.tardiness.leetcode.list.ListNode;

import java.util.*;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/9/9 11:34
 * @Description:
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class DailyOne {

    public static void main(String[] args) {
        DailyOne dailyOne = new DailyOne();

        int[] a = new int[]{3,1,3,5,1,1};

//        dailyOne.combinationSum2(a,8);

//        dailyOne.combinationSum3(3,9);

        //
        TreeNode node1 = new TreeNode(20);
        node1.left = new TreeNode(15);
        node1.right = new TreeNode(7);

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = node1;
        dailyOne.averageOfLevels(root);

    }


    /**
     * 组合 #77
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        dfs(res,list,n,1,k);
        return res;
    }

    public void dfs (List<List<Integer>> res,LinkedList<Integer> list,int n,int begin, int k) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i<=(n-k+list.size()+1); i++) {
            list.addLast(i);
            dfs(res,list,n,i+1,k);
            list.removeLast();
        }
    }


    /**
     * 组合总和 #39
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfsCombinationSum(res,list,candidates,target,0);
        return res;
    }

    private void dfsCombinationSum(List<List<Integer>> res, List<Integer> list,int[] candidates, int target,int index) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        int value;
        for (int i=index;i<candidates.length;i++) {
            value = candidates[i];
            if (value > target) {
                continue;
            }
            list.add(value);
            target -=value;
            dfsCombinationSum(res,list,candidates,target,i);
            int temp = list.remove(list.size()-1);
            target +=temp;
        }
    }


    /**
     * 组合总和 II  #40
     *  [1,1,2,5,6,7,10]
     *  [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        dfsCombinationSum2(list,candidates,target,0);
        return res;
    }

    private void dfsCombinationSum2(List<Integer> list, int[] candidates, int target, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        int value;
        for (int i=index;i<candidates.length;i++) {
            value = candidates[i];
            if (value > target) {
                continue;
            }
            if (i > index && value == candidates[i-1]) {
                continue;
            }
            list.add(value);
            target -= value;
            dfsCombinationSum2(list,candidates,target,i+1);
            int temp = list.remove(list.size()-1);
            target += temp;
        }
    }


    /**
     * 组合总和 III  #216
     * @param k k个数
     * @param n 总和
     * @return
     */
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> list = new ArrayList<>();
        dfsCombinationSum3(list,k,n,1);
        return res;
    }

    private void dfsCombinationSum3(List<Integer> list, int k, int n, int index) {
        if (list.size() == k || n < 0) {
            if (n == 0 && list.size() == k) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i=index;i<=9;i++) {
            if (i > n) {
                break;
            }
            list.add(i);
            n-=i;
            dfsCombinationSum3(list,k,n,++index);
            list.remove(list.size()-1);
            n+=i;
        }
    }

    /**
     * 二叉树的层平均值 #637
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> doubles = new ArrayList<>();
        bfs(root,doubles);
        return doubles;
    }

    private void bfs(TreeNode root, List<Double> doubles) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i=0;i<size;i++) {
                TreeNode treeNode = queue.poll();
                sum += treeNode.val;
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            doubles.add(sum/size);
        }
    }


    /**
     * 单词搜索 #79
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {

        return false;
    }
}
