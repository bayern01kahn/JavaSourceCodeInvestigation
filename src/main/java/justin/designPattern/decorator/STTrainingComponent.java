package justin.designPattern.decorator;

public class STTrainingComponent extends AFootballTrainingCourseComponent {

	
	
	public STTrainingComponent() {
		content = "前锋训练";
	}

	@Override
	public double trainingTime() {
		return 2;
	}

}
