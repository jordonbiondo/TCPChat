import java.util.*;
import java.io.*;
import java.net.*;

public class TestServer {
    
    /**
     * Socket
     */
    public ServerSocket socket;

    
    /**
     * Constructor
     */
    public TestServer(int port) throws Exception {
	socket = new ServerSocket(port);
    }
    

    /**
     *  Listen loop
     */
    public void listen() {
	while(true) {
	    try {
	    Socket client = socket.accept();

	    Thread t = new Thread(new TestHandler(socket, client));
	    t.start();
	    } catch (Exception e) {
		System.out.println("listen");
	    }
	}

    }
    

    /**
     * Main
     */
    public static void main(String[] args) {
	TestServer server;
	try {
	    server = new TestServer(8080);
	    server.listen();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}

class TestHandler implements Runnable {

    /** Server socket */

    ServerSocket socket;

    /** Client Socket */
    Socket client;


    /**
     * Constructor
     */
    public TestHandler(ServerSocket s, Socket client) {
	this.socket = s;
	this.client = client;
    }


    public void run() {
	BufferedReader input;
	PrintWriter output;
	try {
	    input = new BufferedReader(new InputStreamReader(client.getInputStream()));	
	    output = new PrintWriter(client.getOutputStream(), true);
	    while(true) {
		// infinite, read message, send ok
		String message = input.readLine();
		if (message != null) {
		    String[] pieces = message.split(":");
		    System.out.println(pieces[0] + " sent: " + pieces[1]);
		} else {
		    break;
		}
		output.println("you sent: " + message);
		
	    }
	    System.out.println("Client Disconnected");
	    client.close();
	} catch (Exception e ) {
	    e.printStackTrace();
	}
    }
}
