package justin.algorithm.search.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 折半查找，又叫二分查找

   要求顺序存储结构且要求元素按关键字有序排列
   
   过程描述：
   首先，用要查找的关键字k与中间位置的节点的关键字相比较，若相等，若比较结果相等则查找成功，返回中间节点序号；
   如果比较结果不成功：
    mid<k  left标记朝 mid 靠近 +1
    mid>k  right标记朝 mid 靠近 -1
    mid=k  则找到 返回下标
   这样递归下去。
   
   时间复杂度O(log2n)
 */
public class BinarySearch {
	
	static int binarySerach(int[] array, int key) {
	    int left = 0;
	    int right = array.length - 1;

	    while (left <= right) {
	        int mid = (left + right)  >>> 1;
	        int midvalue =array[mid];

	        if (midvalue < key) {
	            left = mid + 1;
	        }else if(midvalue > key){
	            right = mid - 1;
	        }else{
	            return mid;    //midvalue == key  则返回下标
	        }
	    }
	    return -1;
	}

	public static void main(String[] args) {
		
		int [] arr = {1,4,3,7,5,2,9,7,8,7,7,7,10};
		Arrays.sort(arr);// 二分搜索法(使用之前需要先排序)
		System.out.println("Array 二分查找结果（自己实现）： " + binarySerach(arr,7));
		System.out.println("Array 二分查找结果（JDK自带）： " + Arrays.binarySearch(arr,7));
		List iList = new ArrayList<>();
		for(int i : arr)
			iList.add(i);
		Collections.binarySearch(iList, 7);

		System.out.println("List 二分查找结果(JDK自带)： " + binarySerach(arr,7));
	}
}
