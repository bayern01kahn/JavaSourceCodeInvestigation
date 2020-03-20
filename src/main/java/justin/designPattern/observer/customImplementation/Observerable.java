package justin.designPattern.observer.customImplementation;

public interface Observerable {

	//添加观察者
	public void addObserver(Observer ob);
	
	//移除观察者
	public void removeObserver(Observer ob);
	
	//通知所有观察者 
	public void notifyObservers();
	
	//通知指定观察者 
	public void notifyObserver(Observer ob);
}
