package justin.designPattern.command.active_object;

public class SleepCommand implements Command {
    private boolean start = false;
    private long startTime;
    private Command executeCommand;
    private int delay;
    private ActiveObjectEngine engine;

    public SleepCommand(Command executeCommand, int delay, ActiveObjectEngine engine) {
        this.executeCommand = executeCommand;
        this.delay = delay;
        this.engine = engine;
    }

    @Override
    public void execute() {
        if (!start) {
            start = true;
            startTime = System.currentTimeMillis();
            engine.addCommand(this);
        } else if (System.currentTimeMillis() - startTime < delay) {
            engine.addCommand(this);
        } else {
            engine.addCommand(executeCommand);
        }
    }
}
