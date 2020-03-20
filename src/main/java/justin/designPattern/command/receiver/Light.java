package justin.designPattern.command.receiver;

public class Light {

	public static final int ON = 1;
	public static final int OFF =0;
	public int status;
	
	public void on(){
		status = ON;
		System.out.println("遥控开灯");
	}
	
	public void off(){
		status = OFF;
		System.out.println("遥控关灯");
	}
	
	public int getStatus(){
		return status;
	}
}
