import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

class ConnectionListener implements Runnable {

    /** Server socket */
    ChatServer server;


    /**
     * Constructor
     */
    public ConnectionListener(ChatServer server) {
	this.server = server;
    }

    
    /** 
     * Run dat shit you dirty mother fucker
     */
    public void run() {
	try {
	    while(true) {
		Client newClient = new Client(server.socket.accept());
		Thread t = new Thread(new ClientMessageReceiver(server, newClient));
		t.start();
	    }
	} catch (Exception e ) {
	    e.printStackTrace();
	}
    }
}
 