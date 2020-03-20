package justin.designPattern.command.active_object;

import java.util.LinkedList;

public class ActiveObjectEngine {

    private LinkedList<Command> taskList = new LinkedList<>();

    public void addCommand(Command command) {
        taskList.add(command);
    }

    public void run() {
        while (!taskList.isEmpty()) {
            Command command = taskList.removeFirst();
            command.execute();
        }
    }
}
