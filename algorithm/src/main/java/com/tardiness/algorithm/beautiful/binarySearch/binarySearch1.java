package com.tardiness.algorithm.beautiful.binarySearch;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/10/30 11:45
 * @Description: 二分查找
 */
public class binarySearch1 {


    public static void main(String[] args) {
        int[] a = new int[]{1,3,4,5,6,8,8,8,8,11,18};
        binarySearch1 search1 = new binarySearch1();
//        System.out.println(search1.bsearch(a,8,0,a.length-1));

        //        System.out.println(search1.Square(54));

//        System.out.println(search1.findFirstEquals(a,8));
//        System.out.println(search1.findLastEquals(a,8));
//        System.out.println(search1.findFirstBigger(a,11));
        System.out.println(search1.findLastLesser(a,7));

    }

    public int bsearch(int[] a,int n) {
        int low = 0;
        int height = a.length - 1;
        while (low <= height) {
            int mid = low + (height-low)/2;
            if (a[mid] < n) {
                low = mid+1;
            } else if (a[mid] > n) {
                height = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    //递归
    public int bsearch(int[] a,int n,int low,int height) {
        if (low >= height) {
            return -1;
        }
        int mid = low+(height-low)/2;
        if (a[mid] == n) {
            return mid;
        } else if (a[mid] > n) {
            return bsearch(a,n,low,mid -1);
        } else {
            return bsearch(a,n,mid+1,height);
        }
    }

    public double Square(int n) {
        double low = 0.0;
        double height = n;
        while (low <= height) {
            double mid = low+(height-low)/2;
            if (Math.abs(mid*mid - n) > 0.000001) {
                if (mid*mid > n) {
                    height = mid-0.0000001;
                } else {
                    low = mid+0.0000001;
                }
            } else {
                return ((int)(mid*10000000))/10000000.0;
            }
        }
        return -1;
    }


    //查找第一个
    public int findFirstEquals(int[] a,int n) {
        int low = 0;
        int height = a.length-1;
        while (low <= height) {
            int mid = low + ((height-low) >> 1);
            if (a[mid] < n) {
                low = mid + 1;
            } else if (a[mid] > n) {
                height = mid - 1;
            } else {
                if (a[mid - 1] != n || mid == 0) {
                    return mid;
                } else {
                    height = mid - 1;
                }
            }
        }
        return -1;
    }

    //查找最后一个
    public int findLastEquals(int[] a,int n) {
        int low = 0;
        int height = a.length-1;
        while (low <= height) {
            int mid = low + ((height-low) >> 2);
            if (a[mid] < n) {
                low = mid + 1;
            } else if (a[mid] > n) {
                height = mid - 1;
            } else {
                if (mid == a.length-1 || n != a[mid+1]) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public int findFirstBigger(int[] a,int n) {
        int low = 0;
        int height = a.length -1;
        while (low <= height) {
            int mid = low + ((height - low) >> 2);
            if (a[mid] < n) {
                low = mid + 1;
            } else {
                if (a[mid-1] < n) {
                    return mid;
                } else {
                    height = mid - 1;
                }
            }
        }

        return -1;
    }

    public int findLastLesser(int[] a,int n) {
        int low = 0;
        int height = a.length -1;
        while (low <= height) {
            int mid = low + ((height - low) >> 2);
            if (a[mid] > n) {
                height = mid - 1;
            } else {
                if (a[mid+1] > n) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public void test () {

    }
}
