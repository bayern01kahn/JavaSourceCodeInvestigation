package justin.listener.custom;

public class customListenerTest {
	
	public static void main(String args[]){
		EventSource es = new EventSource();
		IEventListener el = new EventListenerImpl();
		
		//设置事件发生监听器
		es.setEventListener(el);
		
		//事件触发
		es.EventHappened();
	}
}
