package com.op.arithmetic;

public class SelectionProblem {
    public static void main(String[] args) {
        int[] arr = {12, 23, 34, 45, 53, 98, 6, 68, 17, 80, 18, 29, 83, 59, 40};

        // 采用方法1，输出数组arr中第十个最大值
        //System.out.println(sortAndReturn(10, arr));
        // 采用方法2，数组arr中第十个最大值
        //System.out.println(readInAndReturn(10, arr));
    }

    // 方法1 整体冒泡排序 然后直接返回第K个最大值 总的时间复杂度为O(n*logn + k)
    public static int sortAndReturn(int k, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr[k - 1];
    }

    // 方法2 利用选择排序或交互排序，K次选择后即可得到第k大的数，算法结束时返回位置K上的元素 总的时间复杂度为O(n*k)
    public static int readInAndReturn(int k, int[] arr) {
        // K个元素的新数组
        int[] newArr = new int[k];
        System.arraycopy(arr, 0, newArr, 0, newArr.length);
        // 选择排序实现新数组newArr的递减排序
        for (int i = 0; i < newArr.length; i++) {
            for (int j = i; j < newArr.length; j++) {
                if (newArr[i] < newArr[j]) {
                    int temp = newArr[i];
                    newArr[i] = newArr[j];
                    newArr[j] = temp;
                }
            }
        }
        //这里已经读取六个元素到新数组中，并完成了递减排序
        //读取后面的元素
        for (int i = k; i < arr.length; i++) {
            int newElement = arr[i];
            // 新元素比newArr中的最小元素大才能进一步比较
            if (newElement > newArr[k - 1]) {
                for (int j = 0; j < newArr.length - 1; j++) {
                    //如果新元素大小在两个元素之间  插入新元素，插入位置后的元素索引后移（元素位置后移1）
                    if (newArr[j] > newElement && newElement > newArr[j + 1]) {
                        if (k > j + 1) {
                            int m = j + 1;
                            //数组j+1索引后面的后一个元素为前一个元素的值
                            newArr[m] = newArr[m - 1];
                        }
                    }
                    //新元素插入
                    newArr[j + 1] = newElement;
                }
            }
        }
        //遍历观察算法完成时的数组
        System.out.print("算法完成时的新数组");
        for (int value : newArr) {
            System.out.print(value + " ");
        }
        System.out.println();

        return newArr[k - 1];
    }

    // 方法3 利用快速排序的思想，从数组S中随机找出一个元素X，把数组分为两部分Sa和Sb。Sa中的元素大于等于X，Sb中元素小于X

}
