package client;

import shared.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class ChatClient {

    /** client socket */
    Socket socket;

    /** cmd line input */
    Scanner stdIn;

    /** socket reader */
    BufferedReader input;

    /** socket writer */
    PrintWriter output;

    private UUID id;


    /**
     *  Constructor
     */
    public ChatClient(final String host, final int port) {
	try {
	    socket = new Socket(host, port);
	    stdIn = new Scanner(System.in);
	    input =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    output = new PrintWriter(socket.getOutputStream(), true);
	    id = UUID.randomUUID();
	    send(id.toString()+ ":hi");
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(-1);
	}
    }


    /**
     *  User Input
     */
    public String userInput(String prompt) {
	if (prompt != null) {
	    prompt.trim();
	    System.out.print(prompt + " ");
	}
	return stdIn.nextLine();
    }
    

    /**
     *  Send
     */
    public void send(String message) throws IOException{
	output.println(message);
    }

    public void send(ClientMessage message) throws IOException {
	send(message.toString());
    }


    /**
     *  receive
     */
    public String receive() throws IOException {
	return input.readLine();


    }

    public UUID getID() {
	return id;
    }

    /**
     *  Main
     */
    public static void main(String[] args) {	
	
	// new client
	System.out.println("Enter server ip: ");
	String ip = new Scanner(System.in).nextLine();
	ChatClient client = new ChatClient(ip, 8080);
	Thread listen = new Thread(new ClientListener(client));
	Thread speak = new Thread(new ClientSpeaker(client));
	listen.start();
	speak.start();
    }
}

class ClientListener implements Runnable {
    
    ChatClient client;
    
    public ClientListener(ChatClient client) {
	this.client = client;
    }
    
    public void run() {
	while (true) {
	    try {
		String message = client.receive();
		System.out.println(">>"+message.split(":")[1]);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    
	}
    }
}

class ClientSpeaker implements Runnable {
    
    ChatClient client;
    
    public ClientSpeaker(ChatClient client) {
	this.client = client;
    }
    
    public void run() {

	while (true) {
	    try {
		
		String input = client.userInput(null);
		ClientMessage message = InputParser.parse(client, input);
		if (message != null) {
		    client.send(message);
		} else {
		    System.out.println("Invalid message syntax you dumbass");
		}
		
		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    
	}
    }
}
