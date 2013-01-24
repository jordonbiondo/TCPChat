package client;

import shared.*;
import java.net.*;
import java.util.*;

public class MessageMaker {

    /**
     * Constructor
     */
    public static String makeMessage(UUID from, String to, ServerAction action, UUID messageNum, String text) {
		return(from.toString() + ":" + to + ":" + action.name() + ":" + messageNum.toString() + ":" + text);
    }

    public static String blockMessage(UUID from, UUID messageNum, String text) {
    	return makeMessage(from, "", ServerAction.block, messageNum, text);
    }

    public static String whisper(UUID from, String to, UUID messageNum, String text)
    {
    	return makeMessage(from, to, "", messageNum, text);
    }

    public static String groupJoin(UUID from, UUID messageNum) {
    	return makeMessage(from, "", ServerAction.groupJoin, messageNum, "");
    }

    public static String changeName(UUID from, UUID messageNum, String text) {
    	return makeMessage(from, "", ServerAction.changeName, messageNum, text);
    }

    public static String textMessage(UUID from, UUID messageNum, String text) {
    	return makeMessage(from, "All", ServerAction.say, messageNum, text);
    }

    public static String listReq(UUID from, UUID messageNum) {
        return makeMessage(from, "", ServerAction.list, messageNum, "");
    }
}