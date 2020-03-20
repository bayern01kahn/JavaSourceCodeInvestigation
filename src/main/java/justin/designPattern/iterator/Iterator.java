package justin.designPattern.iterator;

/**
 * 定义迭代器角色(Iterator)
 * @author justin
 *
 */
public interface Iterator {
	Object next();
	boolean hasNext();
	void remove();
}
