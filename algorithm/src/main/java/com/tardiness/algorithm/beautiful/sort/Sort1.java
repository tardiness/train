package com.tardiness.algorithm.beautiful.sort;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/9/3 14:27
 * @Description: 排序算法 O(n^2)
 */
public class Sort1 {


    public static void main(String[] args) {
        int[] a = new int[]{4,5,6,3,2,1};
        Sort1 sort1 = new Sort1();
//        sort1.bubbleSort(a);
//        sort1.insertionSort(a);
        sort1.selectionSort(a);
        for (int i:a){
            System.out.println(i);
        }
    }

    private void bubbleSort(int[] a) {
        int len = a.length;
        if (len <=1) {
            return;
        }

        for (int i=0;i<len;i++) {
            boolean flag = false;
            for (int j=0;j<len-i-1;j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public void insertionSort(int[] a) {
        int len = a.length;
        if (len <=1) {
            return;
        }

        for (int i=0;i<len;i++) {
            int j = i-1;
            int value = a[i];
            for (;j>=0;j--) {
                if (a[j] > value) {
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            a[j+1] = value;
        }

    }

    //不稳定
    public void selectionSort(int[] a) {
        int len = a.length;
        if (len <=1) {
            return;
        }
        for (int i=0;i<len;i++) {
            int min = a[i];
            int j= i;
            int k = -1;
            for (;j<len;j++) {
                if (a[j] < min) {
                    min = a[j];
                    k = j;
                }
            }
            //交换
            if (k != -1 && i != k) {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }
}
