package server;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class ExitCommand extends ServerCommand {
    
    public ExitCommand(ChatServer server) {
	super("/exit", 
	      "Disconnects the clients and shutsdown the server", server);
    }
    
    @Override public boolean run() {
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
}