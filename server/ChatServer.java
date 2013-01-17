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
    public HashMap<UUID, Client> clients;
    


    /**
     * Thread that sends messages to clients
     */
    private Thread speakThread;


    /**
     * Queue of messages
     */
    public ConcurrentLinkedQueue<String> messageQueue;
    
    /**
     *  Chat Server Construtor
     */
    public ChatServer(int port) throws IOException {
	socket = new ServerSocket(port);
	clients = new HashMap<UUID, Client>();
	messageQueue = new ConcurrentLinkedQueue<String>();
	speakThread = new Thread(new ClientSpeaker(this));
    }

    
    /**
     * Add a message to the queue for sending
     */
    public boolean queueMessage(String message) {
	return messageQueue.add(message);
    }


    /**
     * If the speaker thread is not running, start a new one
     */
    public void notifySpeaker() {
	if (speakThread.getState() == Thread.State.TERMINATED) {
	    speakThread = new Thread(new ClientSpeaker(this));
	    speakThread.start();
	}
    }
    
    
    /**
     *  Listen loop
     */
    public void listen() {
	speakThread.start();
	while(true) {
	    try {
		Client newClient = new Client(socket.accept());
				
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


class ClientSpeaker implements Runnable {
    
    ChatServer server;


    public ClientSpeaker(ChatServer server) {
	this.server = server;
    }


    public void run() {
	while(!server.messageQueue.isEmpty()) {
		System.out.println("sending");
		String message = server.messageQueue.poll();
		HashMap<UUID, Client> clients = server.clients;
		for (UUID id: clients.keySet()) {
		    clients.get(id).sendMessage(message);
		}
	    }
    }
}