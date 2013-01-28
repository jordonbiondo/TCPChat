package shared;
import client.*;
import java.net.*;
import java.util.*;

public class ClientMessage {

    // To String helper
    private static StringBuilder builder = new StringBuilder();
    
    public UUID from;

    public String to;

    public ServerAction action;

    public UUID messageID;

    public String text;

    public ClientMessage() {

    }
    /*
     * Constructor
     */
    public ClientMessage(UUID from , String to, ServerAction action, 
			 UUID messageID, String text) {

	this.from = from;
	this.to = to;
	this.action = action;
	this.messageID = messageID;
	this.text = text;
    }

    /**
     * To String
     */
    public String toString() {
	builder.setLength(0);
	builder.append(from.toString());
	builder.append(":");
	builder.append(to);
	builder.append(":");
	builder.append(action.name());
	builder.append(":");
	builder.append(messageID.toString());
	builder.append(":");
	builder.append(text);
	return builder.toString();
    }

    public static ClientMessage fromString(String message) {
	String[] parts = message.split(":");
	
	if (parts.length < 5) {
	    if (parts[0].toLowerCase().trim() == "server") {
		return new ServerMessage(ServerAction.valueOf(parts[1]),
					 parts[2]);
	    }
	}

	String text = "";
	for (int i = 4; i < parts.length; i++) {
	    text += parts[i];
	}
	return new ClientMessage(UUID.fromString(parts[0]), parts[1],
				 ServerAction.valueOf(parts[2]),
				 UUID.fromString(parts[3]),
				 text);
				 
	
    }

}
