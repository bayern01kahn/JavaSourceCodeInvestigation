package justin.designPattern.command.concreteCommand;


import justin.designPattern.command.command.ICommand;
import justin.designPattern.command.receiver.AirConditioning;

public class ACMidWindSpeedCommand implements ICommand {

	AirConditioning ac;
	int prevSpeed;
	
	public ACMidWindSpeedCommand(AirConditioning ac){
		this.ac = ac;
	}
	
	@Override
	public void execute() {
		prevSpeed = ac.getWindSpeed();
		ac.mid();
	}

	@Override
	public void undo() {
		ac.low();
	}

}
