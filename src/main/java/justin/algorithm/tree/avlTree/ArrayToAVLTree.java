package justin.algorithm.tree.avlTree;

import org.junit.Test;

import java.util.Arrays;


/**
 * 有序数组转换成平衡二叉树
 *
 * 使用 二分查找 来递归插入
 */
public class ArrayToAVLTree {


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    @Test
    public void work(){

        //int[] arr={3,1,2,4,5,6};
        int[] arr={-10,-3,0,5,9};
        Arrays.sort(arr);  //使用二分查找需要先将数组排序
        midOrderTraversal(sortedArrayToBST(arr, 0, arr.length-1));

        midOrderTraversal(sortedArrayToBST(arr));
    }

    /**
     * way 1
     */
    public TreeNode sortedArrayToBST(int[] arr, int low, int high) {
        if(low>high) return null;

        int mid = low + ((high-low+1)>>1);
        TreeNode root=new TreeNode(arr[mid]);
        root.left = sortedArrayToBST(arr, low, mid-1);
        root.right = sortedArrayToBST(arr, mid+1, high);
        return root;
    }

    /**
     * way 2
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        int index = nums.length / 2;
        TreeNode node = new TreeNode(nums[index]);
        int[] leftArray = new int[index];
        for(int i = 0;i<leftArray.length;i++) {
            leftArray[i] = nums[i];
        }
        node.left = sortedArrayToBST(leftArray);
        int[] rightArray = new int[nums.length - index - 1];
        for(int i = 0;i<rightArray.length;i++) {
            rightArray[i] = nums[index + 1 + i];
        }
        node.right = sortedArrayToBST(rightArray);
        return node;
    }

    private void midOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) return;
        midOrderTraversal(treeNode.left);
        System.out.println(treeNode.val);
        midOrderTraversal(treeNode.right);
    }
}
