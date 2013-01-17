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
		HashMap<UUID, Client> clients = server.clients;
		for (UUID id: clients.keySet()) {
		    clients.get(id).sendMessage(message);
		}
	    }
    }
}