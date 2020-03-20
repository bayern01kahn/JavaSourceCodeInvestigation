package justin.designPattern.factory.abstractFactory;

public class DefenseMidfieldFactory implements IMidFieldFootballPlayer {

	public void training(){
		System.out.println("防守球员培训计划：");
		coreTraining();
		assistTraining();
	}

	@Override
	public void coreTraining() {
		// TODO Auto-generated method stub
		System.out.println("加强防守意识练习");
	}

	@Override
	public void assistTraining() {
		// TODO Auto-generated method stub
		System.out.println("加强传球练习");
	}

}
