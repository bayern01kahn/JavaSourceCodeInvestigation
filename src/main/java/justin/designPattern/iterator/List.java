package justin.designPattern.iterator;

/**
 * 定义容器角色(Aggregate)
 * @author justin
 *
 */
public interface List {

	public void add(Object obj);  
	public Object get(int index);
	public Iterator iterator();  
	public int getSize();
}
