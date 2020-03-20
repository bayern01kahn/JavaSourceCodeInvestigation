package justin.designPattern.observer.customImplementation;

import java.util.ArrayList;
import java.util.List;

public class WechatOfficialAccount implements Observerable {

	public List<Observer> observerList = new ArrayList<Observer>();
	public String message;
	
	@Override
	public void addObserver(Observer ob) {
		observerList.add(ob);
	}

	@Override
	public void removeObserver(Observer ob) {
		observerList.remove(ob);
	}

	@Override
	public void notifyObservers() {
		for(Observer ob: observerList){
			notifyObserver(ob);
		}
	}

	@Override
	public void notifyObserver(Observer ob) {
		ob.update(message);
	}

	
	public void sendAllMessage(String message){
		this.message = message;
		notifyObservers();
	}
	
	public void sendDirectMessage(Observer ob,String message){
		this.message = message;
		notifyObserver(ob);
	}
}
