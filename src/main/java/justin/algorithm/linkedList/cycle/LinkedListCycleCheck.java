package justin.algorithm.linkedList.cycle;


import lombok.Data;
import org.junit.Test;

/**
 * 链表是否存在 回环的问题
 * 思路  利用 2个指针 都从head 出发,
 * p1  每次前进1步
 * p2  每次前进2步
 */
public class LinkedListCycleCheck {

    @Data
    public class Node{
        String value;
        Node next;

        Node(String value){
            this.value = value;
        }
    }

    @Test
    public void work(){
        Node n1 = new Node("a");
        Node n2 = new Node("b");
        Node n3 = new Node("c");
        Node n4 = new Node("d");
        Node n5 = new Node("e");
        Node n6 = new Node("f");
        Node n7 = new Node("g");
        Node n8 = new Node("h");
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n5;

        System.out.println("当前链表存在回环, 位置在: "+ isCycle(n1).getValue());

    }

    private Node isCycle(Node n1) {
        Node p1 = n1;
        Node p2 = n1;

        while (p1 != null && p2 != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                return p1;
            }
        }
        return null;
    }
}
