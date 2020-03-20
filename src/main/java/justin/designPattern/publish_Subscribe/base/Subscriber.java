package justin.designPattern.publish_Subscribe.base;

//订阅者
public interface Subscriber<E> {
	public void getPublication(E arg);
}
