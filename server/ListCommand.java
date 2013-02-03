package server;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class ListCommand extends ServerCommand {
    
    public ListCommand(ChatServer server) {
	super("/list",
	      "List the connected users", server);
    }
    
    @Override public boolean run() {
	HashMap<UUID, Client> clients = server.clients;
	if (clients == null) return false;
	for (UUID id : clients.keySet()) {
	    System.out.println(clients.get(id).username+": "+id.toString());
	}
	return true;
    }
       
}