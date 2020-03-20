package justin.designPattern.command.active_object;

public class Test {

    static boolean stop = false;

    public static void main(String[] args) {


        ActiveObjectEngine engine = new ActiveObjectEngine();
        Command command = new Command() {
            @Override
            public void execute() {
                stop = true;
            }
        };

        long time = System.currentTimeMillis();
        SleepCommand sleepCommand = new SleepCommand(command,1000,engine);
        engine.addCommand(sleepCommand);
        engine.run();
        System.out.println(stop);
        System.out.println(System.currentTimeMillis() - time);

    }

}
