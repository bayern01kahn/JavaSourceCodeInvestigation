 package justin.designPattern.decorator;

public class PowerTrainingDecorator extends AFootballTrainCourseDecorator {

	AFootballTrainingCourseComponent ftcComponent;
	
	public PowerTrainingDecorator(AFootballTrainingCourseComponent ftcComponent) {
		this.ftcComponent = ftcComponent;
	}

	@Override
	public String getContent() {
		return ftcComponent.getContent() + "+加练力量训练:1.5h";
	}
	
	@Override
	public double trainingTime() {
		return 1.5 + ftcComponent.trainingTime();
	}
}
