package justin.algorithm.xor;

import java.util.Arrays;

/**
 * 题目
   给出一组整形（int）数组，在这个数组中只有一个数字是单独的，其它的数字都出现了2次，或者更多次。当然出现的次数全部统一，要么全部出现2次，要么全部出现多次。找出唯一存在的那个值
 *
 */
public class FindAppearsOnceNumberFromArray {

	 public static void main(String[] args) {
	        //除了唯一出现的其他出现2次
	        int[] a = {1,2,3,4,5,6,7,8,9,1,2,4,5,6,7,8,9};
	        //除了唯一出现的，其他出现了3次
	        int[] b = {1,2,3,4,5,6,7,8,9,1,2,4,5,6,7,8,9,1,2,4,5,6,7,8,9};
	        
	        //除了唯一出现的，其他出现了7次
	        int[] c = {1,2,3,4,5,6,7,8,9,1,2,4,5,6,7,8,9,1,2,4,5,6,7,8,9,1,2,4,5,6,7,8,9,1,2,4,5,6,7,8,9,1,2,4,5,6,7,8,9,1,2,4,5,6,7,8,9};
	        
	        System.out.println("快速查找 唯一存在的值为" + getSingleByQuickSort(a));
	        System.out.println("2次出现与或运算 唯一存在的值为" + getSingleByxorSort(a,2));
	        System.out.println("多次出现与或运算 唯一存在的值为" + getSingleByxorSort(b,3));
	        System.out.println("多次出现与或运算 唯一存在的值为" + getSingleByxorSort(c,7));
	 }
	 
	 /**
	  *  使用快速排序查找唯一存在值 
	  */
	 public static int getSingleByQuickSort(int[] a){
		//首先对数组进行排序
	        //排序不是本篇博文核心，我们直接调用Arrays库来解决排序，但是真正处理的时候用快速排序或者堆排序
	        Arrays.sort(a);
	        for (int i = 1; i < a.length - 1; i++) {//直接从1开始，可以两头判断
	            if(a[i] != a[i-1]){//把当前数字和它前面数字比较
	                //如果不相等，那么在与它后面的数字比较
	                if(a[i] != a[i+1]){
	                    //那么可以断定已经找到了那个唯一的数字
	                    return a[i];
	                }
	            }
	        }
	        return Integer.MIN_VALUE;//非法的时候抛出
	 }
	 
	/**
	  *  使用异或查找  多次出现 唯一存在值 
	  *  time: 其他数 重复出现次数
	  */
	 public static int getSingleByxorSort(int[] a, int time){
		 if(time == 2){
			//直接遍历数组
		        int result = a[0];
		        for (int i = 1; i < a.length; i++) {
		            result ^= a[i];
		        }
		        return result;
		 }
		 if(time >2){
			// 定义一个数组，用于存放每一个位上出现次数
		        int[] count = new int[32]; // int类型占4个字节32位
		        // 遍历数组，计算每个位置上出现的次数
		        for (int i = 0; i < a.length; i++) {
		            // 计算每一个位上出现的次数
		            for (int j = 0; j < 32; j++) {
		                count[j] += ((a[i] >> j) & 1);//计算一个a[i]中的所以位上的出现情况。&操作：1&1=1；1&0=0
		            }
		        }
		        int result=0;//定义一个返回值
		        //遍历出现次数数组，如果存在某个位上的值不能被次数整除，那么唯一值就在这个位上

		        for(int i = 0; i < 32; i++){
		            if(count[i] % time !=0){
		                result += (1<<i);//恢复这个唯一的数，需要还原它原本1所在的位置
		            }
		        }
		        return result;
		 }
		 return 0;
	 }
}
