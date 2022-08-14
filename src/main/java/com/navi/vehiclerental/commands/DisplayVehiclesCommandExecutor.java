package com.navi.vehiclerental.commands;

import com.navi.vehiclerental.controllers.BranchController;
import com.navi.vehiclerental.models.Command;
import com.navi.vehiclerental.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("displayVehiclesCommandExecutor")
public class DisplayVehiclesCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "DISPLAY_VEHICLES";

    @Autowired
    private BranchController branchController;

    @Override
    public Boolean validate(Command command) {
        return command.getParams().size() == 3;
    }

    @Override
    public void execute(Command command) {
        List<Vehicle> output = this.branchController.displayVehicles(command.getParams().get(0),
                Integer.valueOf(command.getParams().get(1)), Integer.parseInt(command.getParams().get(2)));
        System.out.println(output);
    }
}
