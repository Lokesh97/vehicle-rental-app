package com.navi.vehiclerental.commands;

import com.navi.vehiclerental.controllers.BookingController;
import com.navi.vehiclerental.models.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("bookCommandExecutor")
public class BookCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "BOOK";

    @Autowired
    private BookingController bookingController;

    @Override
    public Boolean validate(Command command) {
        return command.getParams().size()==4;
    }

    @Override
    public void execute(Command command) {
        String output = this.bookingController.book(command.getParams().get(0), command.getParams().get(1),
                Integer.valueOf(command.getParams().get(2)), Integer.parseInt(command.getParams().get(3)));
        System.out.println(output);
    }
}
