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
     * block user
     */
    public static ClientMessage blockMessage(UUID from, String to, UUID messageID) {
	return makeMessage(from, to, ServerAction.block, messageID, "null");
    }

<<<<<<< HEAD
    public static String whisper(UUID from, String to, UUID messageNum, String text)
    {
    	return makeMessage(from, to, "", messageNum, text);
=======
    /*
     * list Users
     */
    public static ClientMessage listMessage(UUID from, UUID messageID) {
	return makeMessage(from, "null", ServerAction.list, messageID, "null");
>>>>>>> CLI
    }


    /*
     * Say
     */
    public static ClientMessage sayMessage(UUID from, UUID messageID, String text) {
	return makeMessage(from, "all", ServerAction.say, messageID, text);
    }

<<<<<<< HEAD
    public static String textMessage(UUID from, UUID messageNum, String text) {
    	return makeMessage(from, "All", ServerAction.say, messageNum, text);
    }

    public static String listReq(UUID from, UUID messageNum) {
        return makeMessage(from, "", ServerAction.list, messageNum, "");
=======
    /*
     * Whisper
     */
    public static ClientMessage whisperMessage(UUID from, String to, UUID messageID,
					       String text) {
	return makeMessage(from, to, ServerAction.whisper, messageID, text);
>>>>>>> CLI
    }

}
