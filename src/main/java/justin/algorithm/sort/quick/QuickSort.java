package justin.algorithm.sort.quick;


import justin.util.PrintUtil;

/**
 * 快速排序，顾名思义，是一种速度快，效率高的排序算法。

	快排原理：
	
	在要排的数（比如数组A）中选择一个中心值key（比如A[mid]），通过一趟排序将数组A分成两部分，
	依次从两头向内开始遍历，发现左边的比中心值小，就记录左边位置，否则遍历下一个，发现右边比中心值大，就记录右边值，否则就继续遍历。
	判断结束就将记录的左右2个值进行交换。  （将比中心值小的移到其左边，比中心值大的移到其右边）
	然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
	
	时间复杂度  O(nlogn)
	
	而在最坏情况下，即数组已经有序或大致有序的情况下，每次划分只能减少一个元素，快速排序将不幸退化为冒泡排序，
    所以快速排序时间复杂度下界为O(nlogn)，最坏情况为O(n^2)。
	在实际应用中，快速排序的平均时间复杂度为O(nlogn)。
 */
public class QuickSort {

	static void quickSort(int arr[], int left, int right) {
		if (arr == null || arr.length == 0 || left >= right)
			return;
 
		int middle = left + (right - left) / 2;
		int pivot = arr[middle];

		int i = left, j = right;  	
		while (i <= j) {
			while (arr[i] < pivot) 
				i++;
			while (arr[j] > pivot) 
				j--;
			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
		//printArr(arr);
		if (left < j)
			quickSort(arr, left, j);
		if (right > i)
			quickSort(arr, i, right);
	}
	
	public static void main(String args[]) {
		int arr[] = { 10, 7, 8, 9, 1, 5, 6, 8, 3, 2, 4};
		System.out.println("original array");
		PrintUtil.printArr(arr);
		quickSort(arr, 0, arr.length-1);
		System.out.println("\nsorted array");
		PrintUtil.printArr(arr);
	}
}
