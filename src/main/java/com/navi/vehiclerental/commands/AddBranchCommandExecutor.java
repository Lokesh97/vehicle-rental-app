package com.navi.vehiclerental.commands;

import com.navi.vehiclerental.controllers.BranchController;
import com.navi.vehiclerental.models.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("addBranchCommandExecutor")
public class AddBranchCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "ADD_BRANCH";

    @Autowired
    private BranchController branchController;

    @Override
    public Boolean validate(Command command) {
        return command.getParams().size()==2;
    }

    @Override
    public void execute(Command command) {
        String output = this.branchController.addBranch(command.getParams().get(0), command.getParams().get(1));
        System.out.println(output);
    }
}
