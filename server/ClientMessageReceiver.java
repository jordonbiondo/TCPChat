import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

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
	    server.clients.put( UUID.fromString(hello.split(":")[0]), client);
		while(true) {
		    String message = client.receiveMessage();
		    if (message != null) {
			server.queueMessage(message);
			server.notifySpeaker();
		    } else {
			break;
		    }
		}
	    System.out.println("Client Disconnected");
	    client.close();
	    
	} catch (Exception e ) {
	    e.printStackTrace();
	}
    }
}
