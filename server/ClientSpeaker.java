package server;


import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

import shared.*;

class ClientSpeaker implements Runnable {

    
    /*
     * The owner
     */
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
	    case list:     sendClientList(message); break;
	    case whisper:  sendWhisper(message); break;
	    case say:      sendText(message); break;
	    }
	}
    }
    
    
    /*
     * Send list
     */
    public void sendClientList(ClientMessage message) {
	String clientList = "Users>.";
	HashMap<UUID, Client> clients = server.clients;
	
	for (UUID id : clients.keySet()) {
	    clientList += "  -"+clients.get(id).username + ".";
	}
	
	message.text = clientList;
	clients.get(message.from).sendMessage(message);
    }
    
    /*
     * Send a whisper
     */
    public void sendWhisper(ClientMessage message) {
	HashMap<UUID, Client> clients = server.clients;
	for (Client c : clients.values()) {
	    System.out.println(c.username+" - "+message.to);
	    if (c.username.trim().equals(message.to.trim())) {
		System.out.println("here");
		message.text = clients.get(message.from).username +" whispers> "+message.text;
		c.sendMessage(message);
		return;
	    }
	}

    }

    public void sendText(ClientMessage message) {
	HashMap<UUID, Client> clients = server.clients;
	message.text = "\t\t" + clients.get(message.from).username +"> "+message.text;
	for (UUID id : clients.keySet()) {
	    if (! id.equals(message.from))
		clients.get(id).sendMessage(message);
	}
	
    }
}

