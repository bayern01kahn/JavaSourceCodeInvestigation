package justin.designPattern.command.active_object;

public class DelayedTyper implements Command{

    private String name;
    private int delay;
    private static boolean stop = false;
    private static ActiveObjectEngine engine = new ActiveObjectEngine();

    @Override
    public void execute() {
        System.out.print(name);
        if (!stop) {
            repeatTask();
        }
    }

    public DelayedTyper(String name, int delay) {
        this.name = name;
        this.delay = delay;
    }


    public void repeatTask() {
        engine.addCommand(new SleepCommand(this,delay,engine));
    }

    public static ActiveObjectEngine getEngine() {
        return engine;
    }

    public static void main(String[] args) {

        engine.addCommand(new DelayedTyper("A",100));
        engine.addCommand(new DelayedTyper("B",300));
        engine.addCommand(new DelayedTyper("C",500));
        engine.addCommand(new DelayedTyper("D",700));

        engine.addCommand(new SleepCommand(() -> stop = true,5000,engine));
        engine.run();
    }
}