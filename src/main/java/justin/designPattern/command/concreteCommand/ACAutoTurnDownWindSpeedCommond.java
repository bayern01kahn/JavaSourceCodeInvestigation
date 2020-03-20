package justin.designPattern.command.concreteCommand;


import justin.designPattern.command.command.ICommand;
import justin.designPattern.command.receiver.AirConditioning;

public class ACAutoTurnDownWindSpeedCommond implements ICommand {

	AirConditioning ac;
	int prevSpeed;
	
	public ACAutoTurnDownWindSpeedCommond(AirConditioning ac){
		this.ac = ac;
	}
	
	@Override
	public void execute() {
		prevSpeed = ac.getWindSpeed();
		if(prevSpeed == ac.HIGH){
			System.out.println("高速运行1小时候后，自动调节到中速");
			ac.mid();
			System.out.println("中速运行2小时候后，自动调节到低速");
			ac.low();
			System.out.println("低速运行3小时候后，自动关闭");
			ac.off();
		}
		if(prevSpeed == ac.MID){
			System.out.println("中速运行2小时候后，自动调节到低速");
			ac.low();
			System.out.println("低速运行3小时候后，自动关闭");
			ac.off();
		}
		if(prevSpeed == ac.LOW){
			System.out.println("低速运行3小时候后，自动关闭");
			ac.off();
		}
	}

	@Override
	public void undo() {
		prevSpeed = ac.getWindSpeed();
		if(prevSpeed == ac.OFF){
			System.out.println("之前操作为低速=>关闭，回退到低速");
			ac.low();
		}
		if(prevSpeed == ac.LOW){
			System.out.println("之前操作为中速=>低速，回退到中速");
			ac.mid();
		}
		if(prevSpeed == ac.MID){
			System.out.println("之前操作为高速=>中速，回退到高速");
			ac.high();
		}
		
	}

}
