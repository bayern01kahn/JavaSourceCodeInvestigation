package justin.jvm.memoryLeak;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 数据加入集合后，修改会影响集合数据识别hashcode的数据参数，导致 数据查找失败 引起内存泄露
 * 解决该问题的办法是不要在执行期间修改与hashCode值有关的对象信息，如果非要修改，则必须先从集合中删除，更新信息后再加入集合中。
 * @author justin
 *
 */
public class CollectionTest {
	public static void main(String[] args) {  
        Collection set = new HashSet();  
        Point p1 = new Point(1, 1);  
        Point p2 = new Point(1, 2);  
        
        //假设p1的hashCode为1，p2的hashCode为2，在存储时p1被分配在1号桶中，p2被分配在2号筒中。
        set.add(p1);  
        set.add(p2);  
        
        
        //这时修改了p2中与计算hashCode有关的信息（x和y）,当调用remove(Object obj)时，首先会查找该hashCode值得对象是否在集合中。
        
        p2.setX(10);  
        p2.setY(10);  
        
      //假设修改后的hashCode值为10（仍存在2号桶中）,这时查找结果空，jdk认为该对象不在集合中，所以不会进行删除操作。
        set.remove(p2);  
        //然而用户以为该对象已经被删除，导致该对象长时间不能被释放，造成内存泄露。
  
        Iterator iterator = set.iterator();  
        while (iterator.hasNext()) {  
            Object object = iterator.next();  
            System.out.println(object);  
        }  
    }
}
