import java.util.*;
import java.io.*;
import java.net.*;

public class TestClient {

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
    public TestClient(final String host, final int port) {
	try {
	    socket = new Socket(host, port);
	    stdIn = new Scanner(System.in);
	    input =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    output = new PrintWriter(socket.getOutputStream(), true);
	    id = UUID.randomUUID();
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(-1);
	}
    }


    /**
     *  User Input
     */
    public String userInput(String prompt) {
	prompt.trim();
	System.out.print(prompt + " ");
	return stdIn.nextLine();
    }
    

    /**
     *  Send
     */
    public void send(String message) throws IOException{
	output.println(id.toString() + ":"+message);
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
	TestClient client = new TestClient(ip, 8080);
	Thread listen = new Thread(new ClientListener(client));
	Thread speak = new Thread(new ClientSpeaker(client));
	listen.start();
	speak.start();
    }
}

class ClientListener implements Runnable {
    
    TestClient client;
    
    public ClientListener(TestClient client) {
	this.client = client;
    }
    
    public void run() {
	while (true) {
	    try {
		String message = client.receive();
		System.out.println(message);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    
	}
    }
}

class ClientSpeaker implements Runnable {
    
    TestClient client;
    
    public ClientSpeaker(TestClient client) {
	this.client = client;
    }
    
    public void run() {
	while (true) {
	    try {
		client.send(client.userInput(""));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    
	}
    }
}