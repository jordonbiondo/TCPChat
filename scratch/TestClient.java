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


    /**
     *  Constructor
     */
    public TestClient(final String host, final int port) {
	try {
	    socket = new Socket(host, port);
	    stdIn = new Scanner(System.in);
	    input =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    output = new PrintWriter(socket.getOutputStream(), true);
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
	output.println(message);
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
	TestClient client = new TestClient("127.0.0.1", 8080);
	
	while (client != null) { // infinite
	    try {
		// send and recieve
		client.send(client.userInput("Enter Message:"));
		System.out.println(client.receive());
	    } catch (Exception e) {
		e.printStackTrace();
		System.exit(-1);
	    }
	}

	try{
	    client.socket.close();
	} catch(Exception e) {
	    
	}
    }
}