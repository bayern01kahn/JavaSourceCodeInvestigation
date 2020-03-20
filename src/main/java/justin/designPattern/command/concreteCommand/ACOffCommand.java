package justin.designPattern.command.concreteCommand;


import justin.designPattern.command.command.ICommand;
import justin.designPattern.command.receiver.AirConditioning;

public class ACOffCommand implements ICommand {

	AirConditioning ac;
	int prevSpeed;
	
	public ACOffCommand(AirConditioning ac){
		this.ac = ac;
	}
	
	@Override
	public void execute() {
		prevSpeed = ac.getWindSpeed();
		ac.off();
	}

	@Override
	public void undo() {
		ac.low();
	}

}
