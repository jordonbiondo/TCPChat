package server;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class HelpCommand extends ServerCommand {
    
    private CommandShell shell;
    
    public HelpCommand(ChatServer server, CommandShell shell) {
	super("/help",
	      "Prints this message",
	      server);
	this.shell = shell;
    }

    @Override public boolean run() {
	System.out.println("\nAvailable Commands:\n----------------------\n");
	for (ServerCommand command : shell.commands.values()) {
	    System.out.println(command.title+":\n\t"+command.helpText);
	}
	return true;
    }
}

