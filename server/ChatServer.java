package server;


import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

import shared.*;

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
     * Thread that listens for new connections
     */
    private Thread connectionThread;

    /**
     * Server shell thread
     */
    private Thread commandThread;
    


    /**
     * Queue of messages
     */
    public ConcurrentLinkedQueue<ClientMessage> messageQueue;
    
    /**
     *  Chat Server Construtor
     */
    public ChatServer(int port) throws IOException {
	socket = new ServerSocket(port);
	clients = new HashMap<UUID, Client>();
	messageQueue = new ConcurrentLinkedQueue<ClientMessage>();
	speakThread = new Thread(new ClientSpeaker(this));
	connectionThread = new Thread(new ConnectionListener(this));
	commandThread = new Thread(new CommandShell(this));
    }

    
    /**
     * Add a message to the queue for sending
     */
    public boolean queueMessage(ClientMessage message) {
		try{
			return messageQueue.add(message);
		}
		catch(Exception e){
	        System.out.println("");
		}
		return false;
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
	connectionThread.start();
	commandThread.start();
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


