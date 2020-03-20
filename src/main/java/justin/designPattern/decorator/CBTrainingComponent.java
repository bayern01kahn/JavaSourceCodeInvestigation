package justin.designPattern.decorator;

public class CBTrainingComponent extends AFootballTrainingCourseComponent {

	public CBTrainingComponent() {
		content = "中后卫训练";
	}

	@Override
	public double trainingTime() {
		return 3;
	}

}
