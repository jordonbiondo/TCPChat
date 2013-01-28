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
	//System.out.println("sending list");
	//System.out.println(message);
	String clientList = "Users> ";
	HashMap<UUID, Client> clients = server.clients;
	for (UUID id : clients.keySet()) {
	    clientList += clients.get(id).username + "...";
	}
	message.text = clientList;

	//System.out.println("sending: "+message.toStr);
	clients.get(message.from).sendMessage(message);
	
    }
    
    /*
     * Send a whisper
     */
    public void sendWhisper(ClientMessage message) {
	System.out.println("whisper----\n"+message.toString());
	HashMap<UUID, Client> clients = server.clients;
	for (Client c : clients.values()) {
	    System.out.println("looking at: "+c.username);
	    if (c.username == message.to)
		System.out.println(c.username+ "-"+message.to);
		c.sendMessage(message);
		return;
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

