

/*
<?xml version="1.0" encoding="UTF-8"?>
<MESSAGE>
  <FROM>user uuid</FROM>
  <TYPE> text or message </TYPE>
  <TO> user or all or group </TO>
  <ACTION> block nameChange </ACTION>
  <TEXT>message text</TEXT>
  <ID>message uuid</ID>
</MESSAGE>
*/



public class Message {

    public final UUID sender;
    public final String receiver;
    public final String text;
    public final ServerAction action;
}