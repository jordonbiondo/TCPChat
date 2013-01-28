package server;


import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

import shared.*;

class ClientSpeaker implements Runnable {
    
    ChatServer server;


    /*
     * Constructor
     */
    public ClientSpeaker(ChatServer server) {
	this.server = server;
    }


    /*
     * Run thread
     */
    public void run() {
	while(!server.messageQueue.isEmpty()) {
	    ClientMessage message = server.messageQueue.poll();
	    ServerAction action = message.action;

	    switch(action) {
		
	    case list:     sendClientList(message);
		
	    case whisper:  sendWhisper(message);
		
	    case say:      sendText(message);
		
	    }
	}
    }

    
    /*
     * Send list
     */
    public void sendClientList(ClientMessage message) {
	String clientList = "";
	HashMap<UUID, Client> clients = server.clients;
	for (UUID id : clients.keySet()) {
	    if (! id.equals(message.from))
		clientList += clients.get(id).username + "\n";
	}
	message.text = clientList;
	clients.get(message.from).sendMessage(message);
	
    }
    
    /*
     * Send a whisper
     */
    public void sendWhisper(ClientMessage message) {
	HashMap<UUID, Client> clients = server.clients;
	Client receiver = clients.get(message.to);
	if (receiver != null) {
	    receiver.sendMessage(message);
	}
    }

    public void sendText(ClientMessage message) {
	HashMap<UUID, Client> clients = server.clients;
	message.text = clients.get(message.from).username +"> "+message.text;
	for (UUID id : clients.keySet()) {
	    if (! id.equals(message.from))
		clients.get(id).sendMessage(message);
	}
	
    }
}

