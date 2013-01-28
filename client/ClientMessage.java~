
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

}
