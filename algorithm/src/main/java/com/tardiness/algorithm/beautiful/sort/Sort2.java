package com.tardiness.algorithm.beautiful.sort;


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

//        sort2.mergeSort(a);

        sort2.quickSort(a);
        for (int i:a){
            System.out.println(i);
        }

    }


    //# merge
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
        //填充剩余的
        while (s1 <= mid) {
            temp[k++] = a[s1++];
        }
        while (s2 <= j) {
            temp[k++] = a[s2++];
        }
        //从temp copy到a
        for (int m=i;m<=j;m++) {
            a[m] = temp[m-i];
        }
    }



    //#quick
    public void quickSort(int[] a) {
        int len = a.length;
        if (len <= 1) {
            return;
        }
        //分
        part(a,0,len-1);
    }

    private void part(int[] a, int i, int j) {
        if (i >= j) {
            return;
        }
        //找到分区点
        int pivot = partition(a,i,j);
        part(a,i,pivot-1);
        part(a,pivot+1,j);
    }
    //找分区点
    public int partition(int[] a, int i, int j) {
        int k =i;
        int pivot = a[j];
        for (;i<j;i++) {
            if (a[i] < pivot) {
                swap(a,i,k++);
            }
        }
        swap(a,k,j);
        return k;
    }

    //交换
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
