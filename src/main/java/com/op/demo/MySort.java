package com.op.demo;

/**
 * @Author: NZY
 * @Date: 2020/7/6 8:03 下午
 */
public class MySort {

	public static void main(String[] args) {
		int[] a = {5, 1, 12, 3, 125, 50, 66};
		for (int v : a) {
			System.out.print(v + " ");
		}
		System.out.println();
		fastSort(a);
		for (int v : a) {
			System.out.print(v + " ");
		}
		System.out.println();
	}

	/**
	 * 简单选择排序
	 */
	public static void selectSort(int[] a) {
		int len = a.length;
		for (int i = 0; i < len; i++) {
			//初始化min索引为当前索引
			int min = i;
			//与后面的比较
			for (int j = i + 1; j < len; j++) {
				//如果后面下标对应的数值小于min下标对应的数值
				if (a[j] < a[min]) {
					//后面下标值赋给min
					min = j;
				}
			}
			//经过比较之后，交换当前的与最小的位置
			exchange(a, i, min);
			System.out.println((i + 1) + "趟：");
			for (int v : a) {
				System.out.print(v + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 插入排序
	 */
	public static void insertSort(int[] a) {
		int len = a.length;
		for (int i = 1; i < len; i++) {
			//与前面的数比较
			for (int j = i; j > 0; j--) {
				//如果当前小于前面的
				if (a[j] < a[j - 1]) {
					//交换位置
					exchange(a, j, j - 1);
				}
			}
			System.out.println((i) + "趟：");
			for (int v : a) {
				System.out.print(v + " ");
			}
			System.out.println();
		}

	}

	/**
	 * 冒泡排序
	 */
	public static void bubbleSort(int[] a) {
		int len = a.length;
		//进行len-1次比较
		for (int i = 0; i < len - 1; i++) {
			//每一次中进行len-1-i次比较
			for (int j = 0; j < len - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					exchange(a, j, j + 1);
				}
			}
			System.out.println((i + 1) + "趟：");
			for (int v : a) {
				System.out.print(v + " ");
			}
			System.out.println();
		}

		// 降序
		// int len = a.length;
		// for(int i = 0; i < len - 1; i++){
		//     for(int j = 0; j < len - i - 1; j++){
		//         if(a[j] > a[j + 1]){
		//             exchange(a, j, j + 1);
		//         }
		//     }
		// }

	}

	/**
	 * 希尔排序：数组越大，优势越大(不是特别懂)
	 */
	public static void hashSort(int[] a) {
		int len = a.length;
		int h = 1;
		while (h < len / 3) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < len; i++) {
				for (int j = i; j >= h; j -= h) {
					if (a[j] < a[j - h]) {
						exchange(a, j, j - h);
					}
				}
			}
			h = h / 3;
		}
	}

	/**
	 * 递归方法，归并排序
	 */
	public static void mergeSort(int[] a, int low, int high) {
		if (low < high) {
			//找到中间下标
			int mid = (low + high) / 2;
			//对前半段再分割
			mergeSort(a, low, mid);
			//对后半段再分割
			mergeSort(a, mid + 1, high);
			//排序&合并当前的两段序列
			merge(a, low, mid, high);
		}
	}

	private static void merge(int[] array, int low, int mid, int high) {
		int i, j, k, n1, n2;
		//子序列1的大小
		n1 = mid - low + 1;
		//子序列2的大小
		n2 = high - mid;
		//子序列1
		int[] l = new int[n1];
		//子序列2
		int[] r = new int[n2];

		//把数组前半段复制到子序列1:（k为开始复制的下标）
		for (i = 0, k = low; i < n1; i++, k++) {
			l[i] = array[k];
		}
		//把数组后半段复制到子序列2
		for (i = 0, k = mid + 1; i < n2; i++, k++) {
			r[i] = array[k];
		}
		//分别扫描子序列1和子序列2，比较谁更小，谁就添加进数组
		for (k = low, i = 0, j = 0; i < n1 && j < n2; k++) {
			// < 升序 ; > 降序
			if (l[i] < r[j]) {
				array[k] = l[i];
				i++;
			} else {
				array[k] = r[j];
				j++;
			}
		}
		//如果子序列1没有扫描完，将其剩下的元素依次添加进数组
		if (i < n1) {
			for (j = i; j < n1; j++, k++) {
				array[k] = l[j];
			}
		}
		//子序列2也是如此
		if (j < n2) {
			for (i = j; i < n2; i++, k++) {
				array[k] = r[i];
			}
		}
	}

	/**
	 * 快速排序
	 */
	public static void fastSort(int[] a) {
		int len = a.length;
		quickSort(a, 0, len - 1);
	}

	private static void quickSort(int[] array, int low, int high) {
		int i = low, j = high;
		//这个变量存储基准数
		int index;
		if (i >= j) {
			return;
		}
		index = array[i];
		//直到i = j，循环结束
		while (i < j) {
			// 从后向前找第一个小于index的数
			while (i < j && array[j] >= index) {
				// 只要当前的数大于等于index就向前移动下标
				//向前移动
				j--;
			}
			if (i < j) {
				//把找到的小于index的数赋值给i所在的位置，然后i向后移动一位
				array[i++] = array[j];
			}

			//前面代码是把整个数组中小于index的数移动到数组前面
			//后面代码是把整个数组中大于等于index的数移动到数组后面

			// 从前向后找第一个大于等于index的数
			while (i < j && array[i] < index) {
				//只要当前的数小于index就向后移动下标
				i++;
			}
			if (i < j) {
				//把找到的大于等于index的数赋给j所在的位置，然后j向前移动一位
				array[j--] = array[i];
			}
		}
		//最后把index赋给i的位置，也可以写为j，因为此时i = j
		array[i] = index;
		//分治思想：这个递归负责数组位置i前面部分的排序
		quickSort(array, low, i - 1);
		//分治思想：这个递归负责数组位置i后面部分的排序
		quickSort(array, i + 1, high);
	}

	private static void exchange(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
