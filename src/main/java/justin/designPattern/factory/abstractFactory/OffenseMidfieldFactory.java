package justin.designPattern.factory.abstractFactory;

public class OffenseMidfieldFactory implements IMidFieldFootballPlayer {

	public void training(){
		System.out.println("进攻球员培训计划：");
		coreTraining();
		assistTraining();
	}

	@Override
	public void coreTraining() {
		// TODO Auto-generated method stub
		System.out.println("加强进攻意识练习");
	}

	@Override
	public void assistTraining() {
		// TODO Auto-generated method stub
		System.out.println("加强射门练习");
	}


}
