package justin.threadlocal;


/**
 *
 * 2654435769为32位无符号整数的黄金分割值，而-1640531527就是32位带符号整数的黄金分割值。
 * 而ThreadLocal中的哈希魔数正是1640531527(十六进制为0x61c88647)。为什么要使用0x61c88647作为哈希魔数？
 *
 * 这里提前说一下ThreadLocal在ThreadLocalMap(ThreadLocal在ThreadLocalMap以Key的形式存在)中的哈希求Key下标的规则：
 *
 * 哈希算法：keyIndex = ((i + 1) * HASH_INCREMENT) & (length - 1)
 *
 * 其中，i为ThreadLocal实例的个数，这里的HASH_INCREMENT就是哈希魔数0x61c88647，length为ThreadLocalMap中可容纳的Entry(K-V结构)的个数(或者称为容量)。
 * 在ThreadLocal中的内部类ThreadLocalMap的初始化容量为16，扩容后总是2的幂次方，因此我们可以写个Demo模拟整个哈希的过程：
 *
 */
public class hashDemo {
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) throws Exception {
        hashCode(4);
        hashCode(16);
        hashCode(32);
    }

    private static void hashCode(int capacity) throws Exception {
        int keyIndex;
        for (int i = 0; i < capacity; i++) {
            keyIndex = ((i + 1) * HASH_INCREMENT) & (capacity - 1);
            System.out.print(keyIndex);
            System.out.print(" ");
        }
        System.out.println();
    }
}

/**
 * 我们分别模拟了ThreadLocalMap容量为4,16,32的情况下，不触发扩容，并且分别”放入”4,16,32个元素到容器中，输出结果如下：
 *
 * 3 2 1 0
 * 7 14 5 12 3 10 1 8 15 6 13 4 11 2 9 0
 * 7 14 21 28 3 10 17 24 31 6 13 20 27 2 9 16 23 30 5 12 19 26 1 8 15 22 29 4 11 18 25 0
 * 每组的元素经过散列算法后恰好填充满了整个容器，也就是实现了完美散列。实际上，这个并不是偶然，其实整个哈希算法可以转换为多项式证明：
 * 证明(x - y) * HASH_INCREMENT != 2^n * (n m)，在x != y，n != m，HASH_INCREMENT为奇数的情况下恒成立，具体证明可以自行完成。
 *
 * HASH_INCREMENT赋值为0x61c88647的API文档注释如下：
 *
 * 连续生成的哈希码之间的差异(增量值)，将隐式顺序线程本地id转换为几乎最佳分布的乘法哈希值，这些不同的哈希值最终生成一个2的幂次方的哈希表。
 */
