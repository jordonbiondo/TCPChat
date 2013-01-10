/**
 * TCPServer.java
 * 
 * Author: 	Jason Tierney
 * Date: 	2012-01-09
 *
 * Demonstrates basic functionality of a TCP server.
 */

import java.io.*;
import java.net.*;

/**
 * TCP Client
 * Technically two types:
 * TCP 
 * - Reliable in order delivery
 * - OS Control / not much user control.
 * UDP
 * - Delivery
 * - More (user) control
 */
public class TCPServer
{
	// Main entry point.
	public static void main(String args[]) throws Exception
	{		
		ServerSocket listenSocket = new ServerSocket(9876);
		
		while(true)
		{
			Socket connectionSocket = listenSocket.accept();
			ClientHandler client = new ClientHandler(connectionSocket);
			Thread thread = new Thread(client);
			thread.start();
		}
	}
}

class ClientHandler implements Runnable
{
	Socket connectionSocket;

	public ClientHandler(Socket socket)
	{
		connectionSocket = socket;
	}
	
	public void run()
	{
		try
		{
			BufferedReader inFromClient = new BufferedReader(
				new InputStreamReader(connectionSocket.getInputStream()));
			
			DataOutputStream outToClient = new DataOutputStream(
				connectionSocket.getOutputStream());
			
			// Retrieve message from client, modify it,
			// then send it back to the client.
			String clientMessage = inFromClient.readLine();
			String modifiedMessage = clientMessage.toUpperCase();
			outToClient.writeBytes(modifiedMessage + '\n');	
		}
		catch(Exception e)
		{
			// Handles Exception;
		}
	}
}