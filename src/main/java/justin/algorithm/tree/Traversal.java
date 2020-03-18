package justin.algorithm.tree;

import java.util.Arrays;
import java.util.LinkedList;


/**
 * 递归实现:
 * 创建二叉树
 * 前序遍历(根->左->右)
 * 中序遍历(左->根->右)
 * 后序遍历(左->右->根)
 *
 *  即  根节点 在前就是 前序,  在中间 就是 中序,  在后面 就是 后序
 */
public class Traversal {

    private static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data){
            this.data = data;
        }
    }


    public static void main(String[] args) {
        //存储用的链表
        LinkedList<Integer> inputList =new LinkedList<>(Arrays.asList(new Integer[]{ 1,2,3,4,5,6}));

        //创建二叉树
        TreeNode treeNode = createBinaryTree(inputList);

        System.out.println("前序遍历:");
        preOrderTraversal(treeNode);
        System.out.println("中序遍历:");
        midOrderTraversal(treeNode);
        System.out.println("后序遍历:");
        postOrderTraversal(treeNode);
        
    }


    private static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if(inputList== null || inputList.isEmpty()) return null;

        Integer data = inputList.removeFirst();
        if(data != null){
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    private static void preOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) return;
        System.out.println(treeNode.data);
        preOrderTraversal(treeNode.leftChild);
        preOrderTraversal(treeNode.rightChild);
    }

    private static void midOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) return;

        midOrderTraversal(treeNode.leftChild);
        System.out.println(treeNode.data);
        midOrderTraversal(treeNode.rightChild);
    }

    private static void postOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) return;

        postOrderTraversal(treeNode.leftChild);
        postOrderTraversal(treeNode.rightChild);
        System.out.println(treeNode.data);
    }
    
}
