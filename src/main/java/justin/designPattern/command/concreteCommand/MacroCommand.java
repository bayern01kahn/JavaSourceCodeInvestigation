package justin.designPattern.command.concreteCommand;


import justin.designPattern.command.command.ICommand;

public class MacroCommand implements ICommand {
	ICommand[] commands;

	public MacroCommand(ICommand[] commands){
		this.commands = commands;
	}
	
	@Override
	public void execute() {
		System.out.println("空调进入睡眠模式");
		
		for(int i=0;i < commands.length; i++){
			commands[i].execute();
		}
	}

	@Override
	public void undo() {
		System.out.println("睡眠模式回退到正常模式");
		commands[1].execute();
	}
}
