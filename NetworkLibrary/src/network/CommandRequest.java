package network;

import java.io.Serializable;

public class CommandRequest implements Serializable {
    private String command;
    private Object commandObject;

    public CommandRequest(String command, Object commandObject) {
        this.command = command;
        this.commandObject = commandObject;
    }

    public String getCommand() {
        return command;
    }

    public Object getCommandObject() {
        return commandObject;
    }
}
