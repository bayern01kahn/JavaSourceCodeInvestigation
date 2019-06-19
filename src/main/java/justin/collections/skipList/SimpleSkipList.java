package justin.collections.skipList;

import java.util.Random;

public class SimpleSkipList {

    private final static byte HEAD_NODE = (byte) -1;
    private final static byte DATA_NODE = (byte) 0;
    private final static byte TAIL_NODE = (byte) 1;

    private static class Node {
        private Integer value;
        private Node up, down, left, right;
        private byte bit;

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value) {
            this(value, DATA_NODE);
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private int height;
    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null, HEAD_NODE);
        this.tail = new Node(null, TAIL_NODE);
        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        SimpleSkipList simpleSkipList = new SimpleSkipList();
        //simpleSkipList.add(10);
        //simpleSkipList.dumpSkipList();
        //simpleSkipList.add(1);
        //simpleSkipList.dumpSkipList();

        Random random = new Random();
        for(int i =0; i<100;i++){
            simpleSkipList.add(random.nextInt(1000));
        }
        simpleSkipList.dumpSkipList();
    }

    public void add(Integer element){

        //完成最底层添加
        Node nearNode = this.find(element);
        Node newNode = new Node(element);
        newNode.left = nearNode;
        newNode.right = nearNode.right;
        nearNode.right.left = newNode;
        nearNode.right = newNode;

        //其他层数添加
        int currentLevel = 0;
        while(random.nextDouble() < 0.5d){

            if(currentLevel >= height){
                height++;
                Node topHead = new Node(null, HEAD_NODE);
                Node topTail = new Node(null, TAIL_NODE);

                topHead.right = topTail;
                topHead.down = head;
                head.up = topHead;

                topTail.left = topHead;
                topTail.down = tail;
                tail.up = topTail;

                head = topHead;
                tail = topTail;
            }

            while((nearNode != null)&&  nearNode.up== null){
                nearNode = nearNode.left;
            }
            nearNode = nearNode.up;
            Node upNode = new Node(element);
            upNode.left = nearNode;
            upNode.right = nearNode.right;
            upNode.down = newNode;

            nearNode.right.left = upNode;
            nearNode.right = upNode;

            newNode.up = upNode;
            newNode = upNode;
            currentLevel++;
        }
        size++;
    }

    public void dumpSkipList(){
        Node temp = head;
        int i = height+1;
        while (temp != null){
            System.out.printf("Total [%d] height [%d]", height+1, i--);
            Node node =temp.right;
            while(node.bit == DATA_NODE){
                System.out.printf("-> [%d] ", node.value);
                node = node.right;
            }
            System.out.println();
            temp= temp.down;
        }
        System.out.println("=============================");
    }

    private Node find(Integer element){
        Node current = head;
        for(;;){
            while(current.right.bit != TAIL_NODE && current.right.value <= element){
                current = current.right;
            }
            if(current.down != null){
                current = current.down;
            }else {
                break;
            }
        }
        return current;  // the current <= element < current.right (if exist)
    }

    public boolean contains(Integer element){
        Node node = this.find(element);
        return (node.value == element);
    }

    public Integer get(Integer element){
        Node node = this.find(element);
        return (node.value == element) ? node.value : null;
    }


    public boolean isEmpty(){
        return (size()== 0);
    }

    public int size(){
        return this.size;
    }
}
