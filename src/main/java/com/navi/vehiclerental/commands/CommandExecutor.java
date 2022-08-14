package com.navi.vehiclerental.commands;

import com.navi.vehiclerental.models.Command;
import org.springframework.stereotype.Component;

@Component("CommandExecutor")
public abstract class CommandExecutor {

    public abstract Boolean validate(Command command);
    public abstract void execute(Command command);
}
