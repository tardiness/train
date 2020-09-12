package com.tardiness.leetcode.sort;

import com.sun.glass.ui.Size;

import java.util.*;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/9/7 13:55
 * @Description: 排序 n*log(n)
 */
public class Sort2 {

    public static void main(String[] args) {
        Sort2 sort2 = new Sort2();
        int[] a = new int[]{4,5,6,3,2,1,7,8,7,36,1,938,87,876};

        sort2.mergeSort(a);
        for (int i:a){
            System.out.println(i);
        }

    }

    /**
     * 归并排序
     * @param a
     */
    private void mergeSort(int[] a) {
        int len = a.length;
        if (len <= 1) {
            return;
        }
        dfs(a,0,len-1);
    }

    private void dfs(int[] a, int i, int j) {
        if (i >= j) {
            return;
        }
        int mid = (i+j)/2;
        dfs(a,i,mid);
        dfs(a,mid+1,j);
        //并
        merge(a,i,j,mid);
    }

    private void merge(int[] a, int i, int j, int mid) {
        int[] temp = new int[j-i+1];
        int s1 = i;
        int s2 = mid+1;
        int k=0;
        while (s1 <= mid && s2 <= j) {
            if (a[s1] <= a[s2]) {
                temp[k++] = a[s1++];
            } else {
                temp[k++] = a[s2++];
            }
        }
        while (s1 <= mid) {
            temp[k++] = a[s1++];
        }
        while (s2 <= j) {
            temp[k++] = a[s2++];
        }
        for (int m=i;m<=j;m++) {
            a[m] = temp[m-i];
        }
    }


    /**
     * 快速排序
     * @param a
     */
    public void quickSort(int[] a) {

    }


}
