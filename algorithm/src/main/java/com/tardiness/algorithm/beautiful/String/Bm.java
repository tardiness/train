package com.tardiness.algorithm.beautiful.String;

/**
 * @project train
 * @author: shishaopeng
 * @description: bm算法
 * @create: 2020/12/31-15:12
 **/
public class Bm {

    private static final int SIZE = 256;

    /**
     * 构建坏字符hash表
     * @param b 模式串
     * @param m 模式串长度
     * @param bc 散列表
     */
    private void generateBC(char[] b,int m,int[] bc) {
        for (int i=0;i<SIZE;i++) {
            bc[i] = -1;
        }
        for (int i=0;i<m;i++) {
            int ascii = (int) b[i];
            bc[ascii] = i;
        }
    }

    /**
     *  好后缀规则 预处理
     * @param b 模式串
     * @param m 模式串长度
     * @param suffix 模式串后缀
     * @param prefix 模式串前缀
     */
    private void generateGS(char[] b,int m,int[] suffix,boolean[] prefix) {
        for (int i=0;i<m;i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i=0;i<m-1;i++) {
            int j = i;
            int k= 0;//公共后缀字串长度
            while (j >=0 && b[j] == b[m-1-k]) { //与b[0,m-1] 的公共后缀字串
                --j;
                ++k;
                suffix[k] = j+1; //j+1 表示公共后缀字串在b[0,i]中的起始下标
            }
            if (j == -1) {
                prefix[k] = true; //如果公共字串也是模式串的前缀字串
            }
        }
    }

    /**
     *
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     */
    private int bm(char[] a,int n,char[] b,int m) {
        int[] bc = new int[SIZE]; //记录模式串中每个字串出现的位置
        generateBC(b,m,bc); //构建坏字符hash表
        int i=0;//表示主串与模式串对其的第一个字符
        while (i <= n-m) {
            int j;
            for (j =m-1;j>=0;j--) {//模式串从后往前匹配
                if (a[i+j] != b[j]) {
                    break; //坏字符对应的下标j
                }
            }
            if (j < 0) {
                return i; //匹配成功 返回主串与模式串第一个匹配的字符的位置
            }
            i = i+(j-bc[(int) a[i+j]]); //这里等同于把模式串往后滑动 j-bc[(int) a[i+j]] 位
        }
        return -1;
    }


}
