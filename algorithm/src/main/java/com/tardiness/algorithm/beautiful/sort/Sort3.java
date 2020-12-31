package com.tardiness.algorithm.beautiful.sort;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/10/28 10:10
 * @Description: 线性排序
 */
public class Sort3 {

    public static void main(String[] args) {
        int[] a = new int[]{2,5,3,0,2,3,0,3};

        Sort3 sort3 = new Sort3();
        sort3.countSort(a);

        for (int i=0;i<a.length;i++) {
            System.out.println(a[i]);
        }
    }


    public void countSort(int[] a) {
        int len = a.length;
        if (len <= 1) {
            return;
        }
        //找到最大值
        int max = a[0];
        for (int i=0;i<len;i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        //下标[0,max]
        int[] c = new int[max + 1];

        for (int j =0;j<max+1;j++) {
            c[j] = 0;
        }

        //填充c
        for (int j=0;j < len;j++) {
            c[a[j]]++;
        }

        //顺序累加
        for (int j=1;j<=max;j++) {
            c[j] = c[j]+c[j-1];
        }

        //核心 填值到临时数组中
        int[] temp = new int[len];
        for (int j=len - 1;j>=0;j--) {
            int index = --c[a[j]];
            temp[index] = a[j];
        }

        //temp  copy 到a
        for (int j=0;j<len;j++) {
            a[j] = temp[j];
        }
    }
}
