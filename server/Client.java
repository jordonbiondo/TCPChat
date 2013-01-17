import java.util.*;
import java.io.*;
import java.net.*;

public class Client {
    
    public Socket socket;

    public BufferedReader sender;
    
    public PrintWriter receiver;

    private UUID id;

    public Client(Socket socket) throws IOException {
	this.socket = socket;
	this.sender = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	this.receiver = new PrintWriter(socket.getOutputStream(), true);
	id = UUID.randomUUID();
    }


    public void close() throws IOException {
	socket.close();
    }

    
    public boolean isClosed() {
	return socket.isClosed();
    }


    public boolean isConnected() {
	return socket.isConnected();
    }


    public void sendMessage(String message) {
	receiver.println(message);
    }


    public String receiveMessage() throws IOException {
	return sender.readLine();
    }

    
    
    
}