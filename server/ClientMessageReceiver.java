package server;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

import shared.*;

class ClientMessageReceiver implements Runnable {

    /** Server socket */

    ChatServer server;

    /** Client Socket */
    Client client;


    /**
     * Constructor
     */
    public ClientMessageReceiver(ChatServer server, Client client) {
	this.server = server;
	this.client = client;
    }
    
    
    public void run() {
	try {
	    String hello = client.receiveMessage();
	    System.out.println(hello);
	    String[] parts = hello.split(":");
	    UUID newID = UUID.fromString(parts[0]);
	    String username = parts[1];
	    client.id = newID;
	    client.username = username;
	    server.clients.put(client.id, client);
	    while(true) {
		
		String msg = "";
		
		try {
		    msg = client.receiveMessage();
		} catch(SocketException se) {
		    // if socket closed, end listen loop
		    break;
		}
		
		if (msg != null) {
		    ClientMessage message = ClientMessage.fromString(msg);
		    server.queueMessage(message);
		    server.notifySpeaker();
		} else {
		    break;
		}
	    }
	    System.out.println(client.username+" Disconnected");
	    client.close();
	    server.clients.remove(client.id);
	    
	} catch (Exception e) {
e.printStackTrace();
        }


    }
}
