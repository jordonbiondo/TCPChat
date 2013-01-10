/**
 * TCPServer.java
 * Date: 	2012-01-09
 *
 * Demonstrates basic functionality of a TCP client.
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
public class TCPClient
{
	// Main entry point.
	public static void main(String args[]) throws Exception
	{		
		FileReader fr = new FileReader("settings.txt");
		BufferedReader textReader = new BufferedReader(fr);
		String ipAddress = textReader.readLine();
		
		// Our socket connection.
		Socket clientSocket = new Socket(ipAddress, 9876);
		
		// Our output stream where the output is sent to the server.
		DataOutputStream outToServer = new DataOutputStream(
			clientSocket.getOutputStream());
		
		// Our input stream from the server.
		BufferedReader inFromServer = new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream()));
		
		// Handles input from the user, simply through reading
		// console commands.
		BufferedReader inFromUser = new BufferedReader(
			new InputStreamReader(System.in));
		
		// Now we are grabbing user input and writing it to
		// the server.
		String message = "";		
			System.out.println("Enter your message, please: ");
			message = inFromUser.readLine();
			outToServer.writeBytes(message + '\n');
			
			// Handle the reply from the server.
			String reply = inFromServer.readLine();
			System.out.println("Server responded with: " + reply);
		
		clientSocket.close();		
	}
}