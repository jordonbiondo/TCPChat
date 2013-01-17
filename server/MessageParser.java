

public class MessageParser 
{    
    /**
	 * Parses a string message and returns it as an object of type Message.
	 */
    public static Message parseMessage(String aMessage) 
	{
		String[] msgPieces = aMessage.split(':');
		
		if(msgPieces.length() < 5)
		{
		    return null;
		}
		
		Message lMessage = new Message();
		lMessage.username = msgPieces[0];
		lMessage.receiver = msgPieces[1];
		lMessage.action = msgPieces[2];

		for(int i = 3; i < msgPieces.length(); i++)
		{
			lMessage.message += msgPieces[i];
		}
		
		return lMessage;
    }
}