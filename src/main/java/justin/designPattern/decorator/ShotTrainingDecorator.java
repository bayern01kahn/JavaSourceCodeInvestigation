 package justin.designPattern.decorator;

public class ShotTrainingDecorator extends AFootballTrainCourseDecorator {

	AFootballTrainingCourseComponent ftcComponent;
	
	public ShotTrainingDecorator(AFootballTrainingCourseComponent ftcComponent) {
		this.ftcComponent = ftcComponent;
	}

	@Override
	public String getContent() {
		return ftcComponent.getContent() + "+加练射门训练:0.5h";
	}
	
	@Override
	public double trainingTime() {
		return 0.5 + ftcComponent.trainingTime();
	}
}
