package justin.designPattern.command.concreteCommand;

import justin.designPattern.command.command.ICommand;
import justin.designPattern.command.receiver.AirConditioning;

public class ACLowWindSpeedCommand implements ICommand {

	AirConditioning ac;
	int prevSpeed;
	
	public ACLowWindSpeedCommand(AirConditioning ac){
		this.ac = ac;
	}
	
	@Override
	public void execute() {
		prevSpeed = ac.getWindSpeed();
		ac.low();
	}

	@Override
	public void undo() {
		ac.off();
	}

}
