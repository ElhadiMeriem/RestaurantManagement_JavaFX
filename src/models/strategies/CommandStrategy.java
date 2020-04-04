package models.strategies;

import models.commands.CommandLivrable;
import models.users.Client;

public class CommandStrategy implements ClientStrategy {

    private CommandLivrable command;

    public CommandStrategy(CommandLivrable command) {
        this.command = command;
    }

    @Override
    public void action(Client client) {
        command.setClient(client);
    }

    public CommandLivrable getCommand() {
        return command;
    }

    public void setCommand(CommandLivrable command) {
        this.command = command;
    }

}
