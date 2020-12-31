package com.tardiness.leetcode.daily;

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
class ListNode {

    int val;

    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
public class DailyOne {

    public static void main(String[] args) {
        DailyOne dailyOne = new DailyOne();

//        int[] a = new int[]{3,1,3,5,1,1};

//        dailyOne.combinationSum2(a,8);

//        dailyOne.combinationSum3(3,9);

        //
//        TreeNode node1 = new TreeNode(20);
//        node1.left = new TreeNode(15);
//        node1.right = new TreeNode(7);
//
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = node1;
//        dailyOne.averageOfLevels(root);
//        dailyOne.inorderTraversal(root);
//        dailyOne.zhongxu(root);


//        char[][] board = new char[][]{
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
//        };
//        dailyOne.exist(board,"ABCB");

        //#226
//        TreeNode node1 = new TreeNode(5);
//        node1.left = new TreeNode(2);
//        node1.right = new TreeNode(13);
//
//        TreeNode root = new TreeNode(14);
//        root.right = new TreeNode(15);
//        root.left = node1;
//        TreeNode node = dailyOne.invertTree(root);
//        System.out.println(node.val);

        //47
//        int[] a = new int[]{1,1,2};
//        dailyOne.permuteUnique(a);

        //538
//        TreeNode node1 = new TreeNode(1);
//        node1.left = new TreeNode(0);
//        node1.right = new TreeNode(4);
//
//        node1.left.left = new TreeNode(-2);
//        node1.right.left = new TreeNode(3);
//
//        dailyOne.convertBST(node1);

        //106
//        inorder = [4,9,5,3,15,20,7]
//     * @ postorder = [4,5,9,15,7,20,3]
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        dailyOne.buildTree(inorder,postorder);

        //235
//        TreeNode node1 = new TreeNode(5);
//        node1.left = new TreeNode(2);
//        node1.right = new TreeNode(13);
//
//        TreeNode root = new TreeNode(14);
//        root.right = new TreeNode(15);
//        root.left = node1;
//        dailyOne.lowestCommonAncestor(root,node1,node1.right);

        //145
//        TreeNode node1 = new TreeNode(5);
//        node1.left = new TreeNode(2);
//        node1.right = new TreeNode(13);
//
//        TreeNode root = new TreeNode(14);
//        root.right = new TreeNode(15);
//        root.left = node1;
//        dailyOne.postorderTraversal(root);

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
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     * @param board
     * @param word
     * @return
     */
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};//←↓↑→
    public boolean exist(char[][] board, String word) {

        int[][] marked = new int[board.length][board[0].length];
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[i].length;j++) {
                if (dfsExist(board,word,i,j,0,marked)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsExist(char[][] board, String word, int i, int j,int index,int[][] marked) {
        if (index == word.length()-1) {
            return board[i][j] == word.charAt(index);
        }
        if (board[i][j] == word.charAt(index)) {
            marked[i][j] = 1;
            for (int k = 0; k < 4; k++) {
                int newX = i+direction[k][0];
                int newY = j+direction[k][1];
                if (newX >=0 && newX <board.length  && newY >=0 && newY < board[newX].length && marked[newX][newY] == 0) {
                    if (dfsExist(board,word,newX,newY,index+1,marked)) {
                        return true;
                    }
                }
            }
            marked[i][j] = 0;
        }
        return false;
    }


    //中序递归
    public void zhongxu(TreeNode root) {
        if (root== null) {
            return;
        }
        zhongxu(root.left);
        System.out.println(root.val);
        zhongxu(root.right);
    }


    /**
     * 中序遍历 (非递归) #94
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                list.add(stack.peek().val);
                node = stack.pop().right;
            }
        }
        return list;
    }


    /**
     * 解数独 #37
     * @param board
     */
    public void solveSudoku(char[][] board) {
        backTrackSudoku(board,0,0);
    }

    private void backTrackSudoku(char[][] board, int i, int j) {
        if (board[i][j] != '.') {
            backTrackSudoku(board,i,j+1);
        }
        if (j > board[i].length -1) {
            backTrackSudoku(board,i+1,0);
        }


        for (int k=1;k<=9;k++) {
            if (isValid(board,k)) {
                board[i][j] = (char) k;
                backTrackSudoku(board,i,j+1);
            }
        }
        board[i][j] = '.';
        backTrackSudoku(board,i,j-1);
    }

    private boolean isValid(char[][] board, int k) {
        return false;
    }


    /**
     * 翻转二叉树 #226
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            if (left != null) {
                stack.push(left);
            }
            if (right != null) {
                stack.push(right);
            }
        }
        return root;
    }

    /**
     * 全排列 II #47
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backTrackPermuteUnique(lists,list,nums,0);
        return lists;
    }

    private void backTrackPermuteUnique(List<List<Integer>> lists, List<Integer> list, int[] nums, int index) {
        if (list.size() == nums.length) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i=index;i<nums.length;i++) {
            list.add(nums[i]);
            backTrackPermuteUnique(lists,list,nums,i+1);
            list.remove(list.size()-1);
        }
    }

    /**
     * 把二叉搜索树转换为累加树  #538
     * @param root
     * @return
     */
    private int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        dfsConvertBST(root);
        return root;
    }
//[1,0,4,-2,null,3]
    private void dfsConvertBST(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.right != null) {
            dfsConvertBST(root.right);
        }

        int sum = root.val + pre;
        root.val = sum;

        pre = sum;
        if (root.left != null) {
            dfsConvertBST(root.left);
        }

    }

    /**
     * #106. 从中序与后序遍历序列构造二叉树
     * @param inorder
     * @param postorder
     * @ inorder = [4,9,5,3,15,20,7]
     * @ postorder = [4,5,9,15,7,20,3]
     * @return
     */
    HashMap<Integer,Integer> orderMap = new HashMap<>();
    int[] post;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i=0;i<inorder.length;i++) {
            orderMap.put(inorder[i],i);
        }
        post = postorder;
        TreeNode node = this.buildTree(0,inorder.length-1,0,postorder.length-1);
        return node;
    }

    private TreeNode buildTree(int is, int ie, int ps, int pe) {
        if (is > ie || ps > pe) {
            return null;
        }
        int ri = orderMap.get(post[pe]);
        TreeNode node = new TreeNode(post[pe]);
        node.left = buildTree(is,ri-1,ps,ps+ri-is-1);
        node.right = buildTree(ri+1,ie,ps + ri - is,pe-1);
        return node;
    }


    /**
     *  #235. 二叉搜索树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode bigger = p.val < q.val ? q:p;
        TreeNode lower = p.val > q.val ? q:p;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (lower.val <= node.val && bigger.val >= node.val) {
                return node;
            }
            if (bigger.val < node.val) {
                stack.push(node.left);
            }
            if (lower.val > node.val) {
                stack.push(node.right);
            }
        }
        return null;
    }

    /**
     *  #145. 二叉树的后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            list.add(node.val);
        }
        return list;
    }



}
