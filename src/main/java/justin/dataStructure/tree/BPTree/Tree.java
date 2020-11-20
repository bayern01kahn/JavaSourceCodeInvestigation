package justin.dataStructure.BPTree.example1.zhangtianlong;

/**
 * Tree
 * @author zhangtianlong
 */
public interface Tree {

    Tuple find(Tuple key);

    boolean remove(Tuple key);

    void insert(Tuple key);
}
