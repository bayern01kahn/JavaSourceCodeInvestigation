package justin.designPattern.observer;

import java.util.Observable;

public class FootballPlayer extends Observable {
	private String name;
	private Integer level;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

	//当球员评级发生变化时调用该方法
    public void modifyPrice(FootballPlayer fp) {
        //调用父类的方法，改变被观察者的状态
        setChanged();
        //通知客户该书已降价
        notifyObservers(fp); //拉模式
        //notifyObservers(); //推模式
    }
}
