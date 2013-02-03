package client;

import java.net.*;
import java.util.*;
import shared.*;

public class MessageMaker {


    /*
     * make Message
     */
    private static ClientMessage makeMessage(UUID from, String to, ServerAction action,
					     UUID messageID, String text) {
	return new ClientMessage(from, to, action, messageID, text);
    }
    

    /*
     * list Users
     */
    public static ClientMessage listMessage(UUID from, UUID messageID) {
	return makeMessage(from, "null", ServerAction.list, messageID, "null");
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
    
    
    
}

