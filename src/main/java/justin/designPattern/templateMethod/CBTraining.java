package justin.designPattern.templateMethod;

public class CBTraining extends AFootballPlayerTraining {

	@Override
	public void pass() {
		System.out.println("短传训练");
	}

	@Override
	public void shot() {
		System.out.println("头球训练");
	}

	@Override
	public void dribble() {
		// TODO Auto-generated method stub
		
	}

}
