package justin.designPattern.templateMethod;

public class STTraining extends AFootballPlayerTraining {
	
	@Override
	public void pass() {
		System.out.println("直塞球训练");
	}

	@Override
	public void shot() {
		//super.isST = true
		System.out.println("低射训练");
	}


	@Override
	protected boolean doDribbleTrainingOrNot() {
		return true;
	}
	
	@Override
	public void dribble() {
		System.out.println("加练过人训练");
	}


}
