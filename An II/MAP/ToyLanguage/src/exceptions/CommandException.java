package exceptions;

import view.commands.Command;

public class CommandException extends RuntimeException{
    public CommandException(String message){
        super(message);
    }
}
