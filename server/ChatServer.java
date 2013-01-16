import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class ChatServer {
    
    /** 
     *  Server Socket
     */
    public ServerSocket socket;
    

    /** 
     * Connected Clients
     */
    public ArrayList<Client> clients;


    public ConcurrentLinkedQueue<String> messageQueue;
    
    /**
     *  Chat Server Construtor
     */
    public ChatServer(int port) throws IOException {
	socket = new ServerSocket(port);
	clients = new ArrayList<Client>();
	messageQueue = new ConcurrentLinkedQueue<String>();
    }

    public boolean queueMessage(String message) {
	return messageQueue.add(message);
    }
    
    
    /**
     *  Listen loop
     */
    public void listen() {
	
	Thread speakThread = new Thread(new ClientSpeaker(this));
	speakThread.start();
	while(true) {
	    try {
		Client newClient = new Client(socket.accept());
		clients.add(newClient);
		
		Thread t = new Thread(new ClientThread(this, newClient));
	    t.start();

	    } catch (Exception e) {
		System.out.println("listen failed");
		e.printStackTrace();
	    }
	}

    }
    
    /**
     * Main
     */
    public static void main(String[] args) {
	ChatServer server;
	try {
	    server = new ChatServer(8080);
	    server.listen();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}


class ClientThread implements Runnable {

    /** Server socket */

    ChatServer server;

    /** Client Socket */
    Client client;


    /**
     * Constructor
     */
    public ClientThread(ChatServer server, Client client) {
	this.server = server;
	this.client = client;
    }


    public void run() {
	//BufferedReader input;
	//PrintWriter output;
	try {
	    //	    input = new BufferedReader(new InputStreamReader(client.getInputStream()));	
	    //	    output = new PrintWriter(client.getOutputStream(), true);
	    while(true) {
		// infinite, read message, send ok
		//String message = input.readLine();
		String message = client.receiveMessage();
		if (message != null) {

		    String[] pieces = message.split(":");
		    server.queueMessage(message);

		} else {
		    break;
		}
		
		//client.sendMessage("you sent: " + message);
		//output.println("you sent: " + message);
		
	    }
	    System.out.println("Client Disconnected");
	    client.close();
	} catch (Exception e ) {
	    e.printStackTrace();
	}
    }
}


class ClientSpeaker implements Runnable {
    
    ChatServer server;


    public ClientSpeaker(ChatServer server) {
	this.server = server;
    }


    public void run() {
	while(true) {
	    if (!server.messageQueue.isEmpty()) {
		String message = server.messageQueue.poll();
		ArrayList<Client> clients = server.clients;
		for (Client c : clients) {
		    c.sendMessage(message);
		}
	    }
	}
    }
}