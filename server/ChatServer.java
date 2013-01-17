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
				
		Thread t = new Thread(new ClientMessageReceiver(this, newClient));
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


