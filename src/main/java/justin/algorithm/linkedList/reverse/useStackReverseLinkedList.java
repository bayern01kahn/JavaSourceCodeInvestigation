package justin.algorithm.linkedList.reverse;

import lombok.Data;
import org.junit.Test;

import java.util.Stack;

/**
 * 链表翻转
 *
 * 利用栈 特性 直接完成 翻转
 */
public class useStackReverseLinkedList {

    @Data
    public class Node {
        String value;
        Node next;

        Node(String value){
            this.value = value;
        }
    }

    public static void reverseLinkedList(Node linkedList){
        if(linkedList == null) return;

        Stack<Node> stack = new Stack();

        //存入栈  利用先进后出
        while(linkedList!= null){
            stack.push(linkedList);
            linkedList= linkedList.next;
        }

        /**
         * 简版
         */
//        while(!stack.isEmpty()){
//            Node newNode = stack.pop();
//            System.out.println(newNode.getValue());
//        }

        if(!stack.isEmpty()){
            Node head = stack.pop();
            Node temp = head;
            while(!stack.isEmpty()){
                Node newNode = stack.pop();
                newNode.next = null;
                temp.next = newNode;
                temp = newNode;
            }
            //输出
            while(head!= null){
                System.out.println(head.getValue());
                head = head.next;
            }
        }






    }


    @Test
    public void work() {
        Node head=new Node("a");
        Node node1=new Node("b");
        Node node2=new Node("c");
        Node node3=new Node("d");
        //初始化链表

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        System.out.println("打印链表反转后：");

        reverseLinkedList(head);
        //设置head的下一个元素为null，注意：此时head已经成为链表尾部元素。
        head.next=null;

    }
}
