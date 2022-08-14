package com.navi.vehiclerental;

import com.navi.vehiclerental.commands.CommandExecutorFactory;
import com.navi.vehiclerental.controllers.BookingController;
import com.navi.vehiclerental.controllers.BranchController;
import com.navi.vehiclerental.controllers.VehicleController;
import com.navi.vehiclerental.models.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component("vehicleRentalApplicationTest")
public class VehicleRentalApplicationTest {
    private final String inputFile = "input.txt";
    @Autowired
    private CommandExecutorFactory commandExecutorFactory;
    public void start() throws IOException {
        final File file = new File(this.inputFile);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String input = reader.readLine();
        while (input != null) {
            final Command command = new Command(input);
            processCommand(command);
            input = reader.readLine();
        }
    }

    public void processCommand(Command command){
        if(!commandExecutorFactory.getCommandExecutor(command).validate(command)){
            System.out.println("Invalid Command");
            return;
        }

        commandExecutorFactory.getCommandExecutor(command).execute(command);
    }
}
