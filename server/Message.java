

/*
<?xml version="1.0" encoding="UTF-8"?>
<MESSAGE>
  <FROM>username</FROM>
  <TO> username or  * or group name </TO>
  <ACTION> block or nameChange or none </ACTION>
  <TEXT>message text</TEXT>
  <ID>message uuid</ID>
</MESSAGE>
*/

/*
  from-uuid:username or not:action:message-uuid:text
*/



public class Message {

    public final UUID sender;
    public final String receiver;
    public final String text;
    public final ServerAction action;
    public final UUID messageID;

	public Message(UUID sender, String receiver, UUID messageID, 
		       ServerAction action, String text) {
	
    }
}