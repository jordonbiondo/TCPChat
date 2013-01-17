/*
  from-uuid:username or not:action:message-uuid:text
*/



public class Message {

    public UUID sender;
    public String receiver;
    public String text;
    public ServerAction action;
    public UUID messageID;

	public Message()
	{
	
    }
}