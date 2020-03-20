 package justin.designPattern.decorator;

public class SpeedTrainingDecorator extends AFootballTrainCourseDecorator {

	AFootballTrainingCourseComponent ftcComponent;
	
	public SpeedTrainingDecorator(AFootballTrainingCourseComponent ftcComponent) {
		this.ftcComponent = ftcComponent;
	}

	@Override
	public String getContent() {
		return ftcComponent.getContent() + "+加练速度训练:0.4h";
	}
	
	@Override
	public double trainingTime() {
		return .4 + ftcComponent.trainingTime();
	}
}
