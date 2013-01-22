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


ChatServer.class : ChatServer.java Client.java ClientSpeaker.java MessageParser.java Message.java

#default: classes

#classes: $(CLASSES:.java=.class)



clean: $(RM) *.class
