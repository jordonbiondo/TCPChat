package server;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class ServerCommand {
    
    public final String title;
    
    public final String helpText;
    
    public ChatServer server;

    public Scanner stdIn;
    
    
    public ServerCommand(String title, String helpText, ChatServer server) {
	this.title = title;
	this.helpText = helpText;
	this.server = server;
	stdIn= new Scanner(System.in);
    }

    public boolean run() {
	return true;
    }
}