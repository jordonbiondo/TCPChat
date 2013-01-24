#JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

#CLASSES = \
	Client.java \
	ClientMessageReceiver.java \
	ClientSpeaker.java \
	Message.java \
	MessageParser.java


server/ChatServer.class : server/ChatServer.java server/Client.java server/ClientSpeaker.java server/MessageParser.java server/Message.java server/CommandShell.java server/ConnectionListener.java

#default: classes

#classes: $(CLASSES:.java=.class)



clean: $(RM) *.class
