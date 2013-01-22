/**
 * from-uuid:username or not:action:message-uuid:text
*/
public class Message 
{
    public String sender;
    
    public String receiver;
    
    public String message;
    
    public String action;
    
    
    // Do we need this?
    //public UUID messageID;
    
    public Message() {
	
    }
    
    public Message(String sender, String receiver,
		   String message, String action) {
	this.sender = sender;
	this.receiver = receiver;
	this.message = message;
	this.action = action;
    }
}