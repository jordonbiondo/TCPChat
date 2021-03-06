package server;

import java.util.*;
import java.io.*;
import java.net.*;
import shared.*;

public class Client {
    
    public Socket socket;

    public BufferedReader sender;
    
    public PrintWriter receiver;

    public UUID id;

    public String username;

    public Client(Socket socket) throws IOException {
	this.socket = socket;
	this.sender = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	this.receiver = new PrintWriter(socket.getOutputStream(), true);
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


    public void sendMessage(ClientMessage message) {
	receiver.println(message.toString());
    }


    public String receiveMessage() throws IOException {
	return sender.readLine();
    }

    
    
    
}
