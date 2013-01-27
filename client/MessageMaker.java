import java.net.*;
import java.util.*;

public class MessageMaker {


    /*
     * make Message
     */
    private static ClientMessage makeMessage(UUID from, String to, ServerAction action,
					     UUID messageID, String text) {
	return new ClientMessage(from, to, action, messageID, text);
    }
    
    /*
     * block user
     */
    public static ClientMessage blockMessage(UUID from, String to, UUID messageID) {
	return makeMessage(from, to, ServerAction.block, messageID, "");
    }

    /*
     * list Users
     */
    public static ClientMessage listMessage(UUID from, UUID messageID) {
	return makeMessage(from, "", ServerAction.list, messageID, "");
    }

    /*
     * Say
     */
    public static ClientMessage sayMessage(UUID from, UUID messageID, String text) {
	return makeMessage(from, "all", ServerAction.say, messageID, text);
    }

    /*
     * Whisper
     */
    public static ClientMessage whisperMessage(UUID from, String to, UUID messageID,
					       String text) {
	return makeMessage(from, to, ServerAction.whisper, messageID, text);
    }





    // public static String makeMessage(UUID from, String to, ServerAction action, UUID messageNum, String text) {
    // 	return(from.toString() + ":" + to + ":" + action.name() + ":" + messageNum.toString() + ":" + text);
    // }
    
    // public static String blockMessage(UUID from, UUID messageNum, String text) {
    // 	return makeMessage(from, "", ServerAction.block, messageNum, text);
    // }
    
    // public static String whisper(UUID from, UUID to, UUID messageNum, String text)
    // {
    // 	return makeMessage(from, to, "", messageNum, text);
    // }
    
    // public static String groupJoin(UUID from, UUID messageNum) {
    // 	return makeMessage(from, "", ServerAction.groupJoin, messageNum, "");
    // }
    
    // public static String changeName(UUID from, UUID messageNum, String text) {
    // 	return makeMessage(from, "", ServerAction.changeName, messageNum, text);
    // }
    
    // public static String textMessage(UUID from, String to, UUID messageNum, String text) {
    // 	return makeMessage(from, to, "", messageNum, text);
    // }
}