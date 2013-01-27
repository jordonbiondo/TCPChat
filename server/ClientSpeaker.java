import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

class ClientSpeaker implements Runnable {
    
    ChatServer server;


    public ClientSpeaker(ChatServer server) {
	this.server = server;
    }


    public void run() {
	while(!server.messageQueue.isEmpty()) {

		String message = server.messageQueue.poll();
		UUID messageID = UUID.fromString(message.split(":")[0]);
		HashMap<UUID, Client> clients = server.clients;
		System.out.println(message);
		for (UUID id: clients.keySet()) {
		    if (! id.equals(messageID))
			clients.get(id).sendMessage(message);   
		}

		
	    }
    }
}