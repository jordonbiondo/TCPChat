package server;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class KickCommand extends ServerCommand {

    public KickCommand(ChatServer server) {
	super("/kick",
	      "Removes a user from the server\n\tUsage: /kick <username>",
	      server);
    }
    
    @Override public boolean run() {
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
}