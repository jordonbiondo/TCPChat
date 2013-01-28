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

	/*
	 * list
	 */
	/*
	commands.put("/list", new ServerCommand() {
		public boolean run(ChatServer server) {
		    HashMap<UUID, Client> clients = server.clients;
		    if (clients == null) return false;
		    for (UUID id : clients.keySet()) {
			System.out.println(clients.get(id).username+": "+id.toString());
		    }
		    return true;
		}
	    });
	*/


	
	/*
	 * Kick
	 */
	/*
	commands.put("/kick", new ServerCommand() {
		public boolean run(ChatServer server) {
		    String user = stdIn.next();
		    HashMap<UUID, Client> clients = server.clients;
		    for (Client c : clients.values()) {
			if (c.username.equals(user)) {
			    try{
				c.close();
				server.clients.remove(c.id);
				System.out.println(c.username+ " has been kicked");
			    }
			    catch (Exception e){
				System.out.println("Weird things are ahappenin'");
			    }
			    return true;
			}
		    }
		    return false;
		}
	    });
	

	
	/*
	 * Exit
	 */
	/*
	commands.put("/exit", new ServerCommand() {
		public boolean run(ChatServer server){
		    System.out.println("start exit-------\n");
		    HashMap<UUID, Client> clients = server.clients;
		    try{
			for(Client c : clients.values()){		
			    c.close();
			}
			System.exit(0);			
		    } catch(Exception e){
			System.out.println("Weird things are ahappenin'");
		    }
		    return true;
		}
	    });
	*/
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

