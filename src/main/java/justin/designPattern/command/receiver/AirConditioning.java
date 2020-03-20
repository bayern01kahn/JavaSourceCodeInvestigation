package justin.designPattern.command.receiver;

public class AirConditioning {

	public static final int HIGH =3;
	public static final int MID =2;
	public static final int LOW =1;
	public static final int OFF =0;
	
	public String location;
	public int windSpeed;
	
	public AirConditioning(String location){
		this.location = location;
	}
	
	public void high(){
		windSpeed = HIGH;
		System.out.println("遥控空调-高风速运行\n");
	}
	
	public void mid(){
		windSpeed = MID;
		System.out.println("遥控空调-中风速运行\n");
	}
	public void low(){
		windSpeed = LOW;
		System.out.println("遥控空调-低风速运行\n");
	}
	
	public void off(){
		windSpeed = OFF;
		System.out.println("遥控空调-关闭");
	}
	
	public int getWindSpeed(){
		return windSpeed;
	}
}
