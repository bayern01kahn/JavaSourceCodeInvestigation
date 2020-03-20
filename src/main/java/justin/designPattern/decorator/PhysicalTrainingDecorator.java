package justin.designPattern.decorator;

public class PhysicalTrainingDecorator extends AFootballTrainCourseDecorator {

	AFootballTrainingCourseComponent ftcComponent;
	
	public PhysicalTrainingDecorator(AFootballTrainingCourseComponent ftcComponent) {
		this.ftcComponent = ftcComponent;
	}

	@Override
	public String getContent() {
		return ftcComponent.getContent()+ "+加练体能训练:1h";
	}

	@Override
	public double trainingTime() {
		return 1.0 + ftcComponent.trainingTime();
	}
}
