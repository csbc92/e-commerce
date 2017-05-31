package network;

import java.io.Serializable;

public class CommandRequest implements Serializable {
    private String command;
    private Object commandObject;

    /**
     * Instantiate a CommandRequest containing a command and command object
     * @param command
     * @param commandObject
     */
    public CommandRequest(String command, Object commandObject) {
        this.command = command;
        this.commandObject = commandObject;
    }

    /**
     *
     * @return The command of the request
     */
    public String getCommand() {
        return command;
    }

    /**
     * @return The command object of the request.
     */
    public Object getCommandObject() {
        return commandObject;
    }
}
