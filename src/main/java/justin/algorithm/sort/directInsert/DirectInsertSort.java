package justin.algorithm.sort.directInsert;


import justin.util.PrintUtil;

/**
 * 直接插入排序

   算法思路是：
	待排序记录 R1，R2，… ，Rn–1, Rn
	第一步：将无序表的第一个元素作为一个有序表。
	第二步：将R2加入到有序表中，使有序表依旧有序
	.........
	第 n 步: (R1，R2，… ，Rn–1), Rn．
	以此类推。
	
	
    时间复杂度，最好的情况是待排序记录是有序的，O（N），最坏打情况是记录从大到小排列，O（N2）。
 */
public class DirectInsertSort {
	
	static void directSort(int[] arr){
		if(arr == null || arr.length == 0) {
            System.out.println("该数组为空！");
            return;
        }
        int n = arr.length;							//将数组的长度赋给n是为了防止每次for循环中判断时都调用length方法影响性能
        int j,temp;          							//放于for循环外面是为了防止重复创建变量
        for(int i = 1; i < n;i++){//排序的趟数
            temp = arr[i];							//赋给temp是为了防止索引i之前的元素向后移动覆盖了索引i的元素
            for(j = i-1; j>=0&&arr[j]>temp; --j) {	//将大于i位置元素的元素向后移
                arr[j+1] = arr[j];
            }
            arr[j+1]= temp;							//找到i应该在的位置，将值放置此处 
        }
	}
	
	public static void main(String[] args) {
		int[] arr = {9, 3, 8, 4,  7,  5,  2, 1, 6, 0};
		
		PrintUtil.printArr(arr);
		directSort(arr);
		PrintUtil.printArr(arr);
    }

}
