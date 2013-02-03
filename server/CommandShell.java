package server;


import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

class CommandShell implements Runnable {

    /** Server socket */
    ChatServer server;

    /**
     * Command List
     */
    public HashMap<String, ServerCommand> commands;

    Scanner stdIn;
    

    /**
     * Constructor
     */
    public CommandShell(ChatServer server) {
	this.server = server;
	commands = new HashMap<String, ServerCommand>();
	stdIn = new Scanner(System.in);
	initCommands();
    }

    public void initCommands() {
	HelpCommand help = new HelpCommand(server, this);
	commands.put(help.title, help);
	ExitCommand exit = new ExitCommand(server);
	commands.put(exit.title, exit);
	ListCommand list = new ListCommand(server);
	commands.put(list.title, list);
	KickCommand kick = new KickCommand(server);
	commands.put(kick.title, kick);
	commands.put("/awp", kick);
    }
    
    
    /** 
     * Run 
     */
    public void run() {
	try {
	    while(true) {
		String input = stdIn.next().toLowerCase().trim();
		ServerCommand command = commands.get(input);
		if (command != null) {
		    command.run();
		} else {
		    System.out.println("\""+input+"\" is not a command type /help for information");
		}
	    }
	} catch (Exception e ) {
	    e.printStackTrace();
	}
    }
    
    
    
}

