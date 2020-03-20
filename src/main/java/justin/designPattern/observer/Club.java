package justin.designPattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Club implements Observer {

	public String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		FootballPlayer fp = (FootballPlayer) arg;
		System.out.println("通知俱乐部："+ this.getName()+"所关注球员："+ fp.getName()+" 能力评级发生了变化："+ fp.getLevel()+"级");
	}

}
