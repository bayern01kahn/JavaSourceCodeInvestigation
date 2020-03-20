package justin.designPattern.factory.factoryMethod;


public abstract class AFootballPlayer {

	/** 工厂方法模式 
	* 【创建一个产品的等级结构，一个抽象产品类】
	* 每种产品由一种工厂来创建，一个工厂保存一个new
	* 完全遵循 “不改代码”的原则
	*/
	public abstract void productPlayer();
}
