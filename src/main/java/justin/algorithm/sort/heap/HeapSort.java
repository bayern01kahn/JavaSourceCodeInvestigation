package justin.algorithm.sort.heap;


import justin.util.PrintUtil;

/**
 * 堆排序：Java
 *
 * 堆排序(Heap Sort)是指利用堆这种数据结构所设计的一种排序算法。

	我们知道，堆分为"最大堆"和"最小堆"。最大堆通常被用来进行"升序"排序，而最小堆通常被用来进行"降序"排序。
	鉴于最大堆和最小堆是对称关系，理解其中一种即可。本文将对最大堆实现的升序排序进行详细说明。

	最大堆进行升序排序的基本思想：
	① 初始化堆：将数列a[1...n]构造成最大堆。
	② 交换数据：将a[1]和a[n]交换，使a[n]是a[1...n]中的最大值；然后将a[1...n-1]重新调整为最大堆。 接着，将a[1]和a[n-1]交换，使a[n-1]是a[1...n-1]中的最大值；然后将a[1...n-2]重新调整为最大值。 依次类推，直到整个数列都是有序的。
	
	下面，通过图文来解析堆排序的实现过程。注意实现中用到了"数组实现的二叉堆的性质"。
	在第一个元素的索引为 0 的情形中：
	性质一：索引为i的左孩子的索引是 (2*i+1);
	性质二：索引为i的左孩子的索引是 (2*i+2);
	性质三：索引为i的父结点的索引是 floor((i-1)/2);
 *
 * @author skywang
 * @date 2014/03/11
 */

public class HeapSort {

	/*
     * 堆排序(从小到大)
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     n -- 数组的长度
     */
    public static void heapSortAsc(int[] a) {
        int i,tmp,n=a.length;

        // 从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆。
        for (i = n / 2 - 1; i >= 0; i--)
            maxHeapDown(a, i, n-1);

        
        System.out.println("1.初始化堆完成");
        PrintUtil.printArr(a);
        // 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        for (i = n - 1; i > 0; i--) {
            // 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的。
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            // 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
            // 即，保证a[i-1]是a[0...i-1]中的最大值。
            maxHeapDown(a, 0, i-1);
        }
    }
	
    /* 
     * (最大)堆的向下调整算法
     *
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     *     其中，N为数组下标索引值，如数组中第1个数对应的N为0。
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     start -- 被下调节点的起始位置(一般为0，表示从第1个开始)
     *     end   -- 截至范围(一般为数组中最后一个元素的索引)
     */
    public static void maxHeapDown(int[] a, int start, int end) {
        int c = start;            // 当前(current)节点的位置
        int l = 2*c + 1;        // 左(left)孩子的位置
        int tmp = a[c];            // 当前(current)节点的大小

        for (; l <= end; c=l,l=2*l+1) {
            // "l"是左孩子，"l+1"是右孩子
            if ( l < end && a[l] < a[l+1])
                l++;        // 左右两孩子中选择较大者，即m_heap[l+1]
            if (tmp >= a[l])
                break;        // 调整结束
            else {            // 交换值
                a[c] = a[l];
                a[l]= tmp;
            }
        }
    }

    

    /* 
     * (最小)堆的向下调整算法
     *
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     *     其中，N为数组下标索引值，如数组中第1个数对应的N为0。
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     start -- 被下调节点的起始位置(一般为0，表示从第1个开始)
     *     end   -- 截至范围(一般为数组中最后一个元素的索引)
     */
    public static void minHeapDown(int[] a, int start, int end) {
        int c = start;            // 当前(current)节点的位置
        int l = 2*c + 1;        // 左(left)孩子的位置
        int tmp = a[c];            // 当前(current)节点的大小

        for (; l <= end; c=l,l=2*l+1) {
            // "l"是左孩子，"l+1"是右孩子
            if ( l < end && a[l] > a[l+1])
                l++;        // 左右两孩子中选择较小者
            if (tmp <= a[l])
                break;        // 调整结束
            else {            // 交换值
                a[c] = a[l];
                a[l]= tmp;
            }
        }
    }

    /*
     * 堆排序(从大到小)
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     n -- 数组的长度
     */
    public static void heapSortDesc(int[] a) {
        int i,tmp,n=a.length;

        // 从(n/2-1) --> 0逐次遍历每。遍历之后，得到的数组实际上是一个最小堆。
        for (i = n / 2 - 1; i >= 0; i--)
            minHeapDown(a, i, n-1);
        
        
        System.out.println("1.初始化堆完成");
        PrintUtil.printArr(a);
        // 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        for (i = n - 1; i > 0; i--) {
            // 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最小的。
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            // 调整a[0...i-1]，使得a[0...i-1]仍然是一个最小堆。
            // 即，保证a[i-1]是a[0...i-1]中的最小值。
            minHeapDown(a, 0, i-1);
        }
    }

    public static void main(String[] args) {
        int arr[] = {20,30,90,40,70,110,60,10,100,50,80};

        PrintUtil.printArr(arr);
        heapSortAsc(arr);            // 升序排列
        System.out.println("2.数据交换完成排序");
        PrintUtil.printArr(arr);
        
//        heapSortDesc(arr);        // 降序排列
//        PrintUtil.printArr(arr);
    }
}