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
	    send("hi");
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

    public void requestList() {
    	send(MessageMaker.listReq(id, UUID.randomUUID()));
    }

    public void sendText(String text) {
    	send(MessageMaker.textMessage(id, UUID.randomUUID(), text));
    }

    public void nameChange(String text) {
    	send(MessageMaker.changeName(id, UUID.randomUUID(), text));
    }

    public void joinGroup(String text) {
    	send(MessageMaker.groupJoin(id, UUID.randomUUID()));
    }

    public void whisper(String text, String to) {
    	send(MessageMaker.whisper(id, to, UUID.randomUUID(), text));
    }

    public void block(String text) {
    	send(MessageMaker.blockMessage(id, UUID.randomUUID(), text);
    }


    /**
     *  receive
     */
    public String receive() throws IOException {
	return input.readLine();


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
		client.send(client.userInput(null));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    
	}
    }
}