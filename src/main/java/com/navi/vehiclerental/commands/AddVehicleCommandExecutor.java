package com.navi.vehiclerental.commands;

import com.navi.vehiclerental.controllers.VehicleController;
import com.navi.vehiclerental.models.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("addVehicleCommandExecutor")
public class AddVehicleCommandExecutor extends CommandExecutor {

    public static String COMMAND_NAME = "ADD_VEHICLE";

    @Autowired
    private VehicleController vehicleController;

    @Override
    public Boolean validate(Command command) {
        return command.getParams().size() == 4;
    }

    @Override
    public void execute(Command command) {
        String output = this.vehicleController.addVehicle(command.getParams().get(0), command.getParams().get(1),
                command.getParams().get(2), Integer.valueOf(command.getParams().get(3)));
        System.out.println(output);
    }
}
