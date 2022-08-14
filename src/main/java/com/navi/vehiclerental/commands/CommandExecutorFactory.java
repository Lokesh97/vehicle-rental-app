package com.navi.vehiclerental.commands;

import com.navi.vehiclerental.exceptions.InvalidCommandException;
import com.navi.vehiclerental.models.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("commandExecutorFactory")
public class CommandExecutorFactory {
    private Map<String, CommandExecutor> commands;

    @Autowired
    @Qualifier("addBranchCommandExecutor")
    private CommandExecutor addBranchCommandExecutor;

    @Autowired
    @Qualifier("addVehicleCommandExecutor")
    private CommandExecutor addVehicleCommandExecutor;

    @Autowired
    @Qualifier("bookCommandExecutor")
    private CommandExecutor bookCommandExecutor;

    @Autowired
    @Qualifier("displayVehiclesCommandExecutor")
    private CommandExecutor displayVehiclesCommandExecutor;

    public CommandExecutorFactory() {
        this.commands = new HashMap<>();
    }

    public void init(){
        commands.put(AddBranchCommandExecutor.COMMAND_NAME, this.addBranchCommandExecutor);
        commands.put(AddVehicleCommandExecutor.COMMAND_NAME, this.addVehicleCommandExecutor);
        commands.put(BookCommandExecutor.COMMAND_NAME, this.bookCommandExecutor);
        commands.put(DisplayVehiclesCommandExecutor.COMMAND_NAME, this.displayVehiclesCommandExecutor);
    }
    public CommandExecutor getCommandExecutor(Command command) {
        final CommandExecutor commandExecutor = commands.get(command.getName());
        if (commandExecutor == null) {
            throw new InvalidCommandException();
        }
        return commandExecutor;
    }
}
