package justin.juc.queue;

/**
 * 2017/4/30
 */
public class LinkQueue<T> {

    // 定义一个内部类Node，Node实例代表链队列的节点
    private class Node {

        // 保存节点的数据
        private T data;
        // 指向下个节点的引用
        private Node next;

        // 无参构造器
        public Node() {
        }

        // 初始化全部属性的构造器
        public Node(T data, Node next) {

            this.data = data;
            this.next = next;

        }

    }

    // 保存该链队列的头节点
    private Node front;
    // 保存该链队列的尾节点
    private Node rear;
    // 保存该链队列中已包含的节点数
    private int size;

    // 创建空链队列
    public LinkQueue() {
        // 空链队列，front和rear的值都为null
        front = null;
        rear = null;
    }

    // 以指定数据元素来创建链队列，该链队列只有一个元素
    public LinkQueue(T element) {

        front = new Node(element, null);
        // 只有一个节点，front、rear都是指向该节点
        rear = front;
        size++;

    }

    // 返回链队列的长度
    public int length() {

        return size;

    }

    // 将新元素加入队列
    public void add(T element) {
        // 如果该链队列还是空链队列
        if (front == null) {
            front = new Node(element, null);
            // 只有一个节点，front、rear都是指向该节点
            rear = front;
        } else {
            // 创建新节点
            Node newNode = new Node(element, null);
            // 让尾节点的next指向新增的节点
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // 删除队列front端的元素
    public T remove() {

        Node oldfront = front;
        // 让front引用指向原队列顶元素的下一个元素
        front = front.next;
        // 释放原队列顶元素的next引用
        oldfront.next = null;
        size--;
        return oldfront.data;

    }

    // 访问队列顶元素，但不删除队列顶元素
    public T element() {

        return rear.data;

    }

    // 判断链队列是否为空队列
    public boolean empty() {

        return size == 0;

    }

    // 请空链队列
    public void clear() {
        // 将front、rear两个节点赋为null
        front = null;
        rear = null;
        size = 0;
    }

    public String toString() {

        // 链队列为空队列时
        if (empty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = front; current != null; current = current.next) {
                sb.append(current.data.toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }

    }
    
    public static void main(String[] args) {

        LinkQueue<String> queue = new LinkQueue<String>("aaaa");
        // 依次将4个元素加入到队列中
        queue.add("bbbb");
        queue.add("cccc");
        queue.add("dddd");
        System.out.println(queue);

        // 删除一个元素后
        queue.remove();
        System.out.println("删除一个元素后的队列：" + queue);

        // 再添加一个元素
        queue.add("eeee");
        System.out.println("再次添加元素后的队列：" + queue);

    }

}
