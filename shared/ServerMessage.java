package shared;

public class ServerMessage extends ClientMessage {
    
    public ServerMessage(ServerAction action, String text) {
	this.action = action;
	this.text = text;
    }

    public String toString() {
	return "server:"+action.name()+":"+text;
    }
    
    
}
