package justin.designPattern.decorator;

public abstract class AFootballTrainingCourseComponent {

	protected String content ="基础训练";

	public String getContent() {
		return content;
	}
	
	public abstract double trainingTime();
}
