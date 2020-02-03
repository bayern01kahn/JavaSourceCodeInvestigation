package justin.algorithm.sort.bubble;

import justin.util.PrintUtil;

/**
 * 冒泡排序
 * 它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 *
	时间复杂度
	其外层循环执行 N - 1次。内层循环最多的时候执行N次，最少的时候执行1次，平均执行 (N+1)/2次。
    所以循环体内的比较交换约执行 (N - 1)(N + 1) / 2 = (N^2 - 1)/2（其中N^2是仿照Latex中的记法，表示N的平方）。
    按照计算复杂度的原则，去掉常数，去掉最高项系数，其复杂度为O(N^2)。N的平方
 */
public class BubbleSort {

	static void bubbleSort(int [] a) {
		int j , k;
	    int flag = a.length ;//flag来记录最后交换的位置，也就是排序的尾边界

	    while (flag > 0){//排序未结束标志
	        k = flag; //k 来记录遍历的尾边界
	        flag = 0;

	        for(j=1; j<k; j++){
	            if(a[j-1] > a[j]){//前面的数字大于后面的数字就交换
	                //交换 a[j-1]和a[j]  使用异或 可以不用临时变量完成值交换
	            	a[j-1] = a[j-1] ^ a[j];
	            	a[j] = a[j] ^ a[j-1];
	            	a[j-1] = a[j-1] ^ a[j];
	            	
	                //表示交换过数据;
	                flag = j;//记录最新的尾边界.
	            }
	        }
	    }
	}

	public static void main(String[] args) {
    	int[] arr = { 10, 7, 8, 9, 1, 5, 6, 8, 3, 2, 4};
    	PrintUtil.printArr(arr);
    	System.out.println("冒泡排序后：");
    	bubbleSort(arr);
    	PrintUtil.printArr(arr);
	}
}
