package justin.listener.custom;

public class EventSource {

	private IEventListener eventListener;
	
	// 注册监听器  
    public void setEventListener(IEventListener arg) {  
    	eventListener = arg;  
    } 
	
	public void EventHappened(){
		System.out.println("触发事件");
		eventListener.handleEvent();
	}
}
