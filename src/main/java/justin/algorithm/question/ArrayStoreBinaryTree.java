package justin.algorithm.question;

import lombok.Data;
import org.junit.Test;

import java.util.Arrays;

/**
 * 将 二叉树 存入到 一维数组
 *
 *  核心算法:  节点对应 数组序号i
 *  根节点 i
 *  左子节点 2i+1
 *  右子节点 2i+2
 */
public class ArrayStoreBinaryTree {

    @Data
    public class BinaryTree{
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value){
            this.value=value;
        }
    }

    @Test
    public void work(){
        BinaryTree tree = initTree();
        int[] arr = new int[9];
        saveBinaryTreeIntoArray(tree, arr,0);
        System.out.println(Arrays.toString(arr));
    }

    private int saveBinaryTreeIntoArray(BinaryTree tree, int[] arr, int index) {
        if(tree!=null){
            arr[index]= tree.getValue();
        }
        if(tree.left != null){
            arr[2*index+1] = saveBinaryTreeIntoArray(tree.left, arr, 2*index+1);
        }
        if(tree.right != null){
            arr[2*index+2] = saveBinaryTreeIntoArray(tree.right, arr, 2*index+2);
        }
        return tree.getValue();
    }

    private BinaryTree initTree() {
        BinaryTree bt1 = new BinaryTree(1);
        BinaryTree bt2 = new BinaryTree(3);
        BinaryTree bt3 = new BinaryTree(2);
        BinaryTree bt4 = new BinaryTree(6);
        BinaryTree bt5 = new BinaryTree(5);
        BinaryTree bt6 = new BinaryTree(7);
        BinaryTree bt7 = new BinaryTree(8);
        BinaryTree bt8 = new BinaryTree(9);
        BinaryTree bt9 = new BinaryTree(10);

        bt1.left=bt2;
        bt1.right=bt3;
        bt2.left =bt4;
        bt2.right=bt5;
        bt3.left=bt6;
        bt3.right=bt7;
        bt4.left=bt8;
        bt4.right=bt9;

        frontOrder(bt1);
        //midOrder(bt1);
        //lastOrder(bt1);
        return bt1;
    }

    private void frontOrder(BinaryTree bt) {
        if(bt ==null) return ;
        System.out.println(bt.value);
        frontOrder(bt.left);
        frontOrder(bt.right);
    }

    private void midOrder(BinaryTree bt) {
        if(bt ==null) return ;
        midOrder(bt.left);
        System.out.println(bt.value);
        midOrder(bt.right);
    }

    private void lastOrder(BinaryTree bt) {
        if(bt ==null) return ;
        lastOrder(bt.left);
        lastOrder(bt.right);
        System.out.println(bt.value);
    }
}
