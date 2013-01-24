import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 * So this is *part* of the client GUI.  I'm having some issues with the tab
 * layout that I'll try to resolve going forward.  If you guys have any hints
 * or have done it before let me know.  But, this could work for just talking 
 * to one other person after we get the server done.  Still some work to be done.
 *
 */

public class GUI implements ActionListener {
    
    private JFrame frame = new JFrame();
    
    private JPanel panelSouth, panelCenter, centerBottom, panelWest;
    
    private JLabel usernameLabel;
    
    private JTabbedPane tabbedPane;
    
    private JButton sendTextButton = new JButton("Send");
    
    private ArrayList<Object> onlineUsers = new ArrayList<Object>();
    
    private JList list;
    
    private JTextArea textArea;
    private JScrollPane scrollPane, listScrollPane;
    
    private JTextField textField;
    public void run() {
	//	    	crappy method that doesn't really do anything real.  Won't be in the final
	//	    	 part of this
	initializeTestUsersOnline();
	
	frame.setLayout(new BorderLayout());
	panelSouth = new JPanel();
	panelSouth.setLayout(new BorderLayout());
	
	//	       Failed attempt at tabbedpanes
	//	       tabbedPane = new JTabbedPane();
	//	       
	//	       tabbedPane.addTab(onlineUsers.toArray()[0].toString(), textArea);
	
	//Setting up actual chat area
	textArea = new JTextArea(" ", 5, 20);
	textArea.setLineWrap(true);
	textArea.setEditable(false);
	scrollPane = new JScrollPane(textArea);
	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	panelCenter = new JPanel();
	panelCenter.add(scrollPane);
	
	//Holds the send and text box for typing the message.  For some reason
	// it's being really obnoxious and putting the send button on the left.
	centerBottom = new JPanel();
	centerBottom.setLayout(new GridLayout(1,2));
	textField = new JTextField(15);
	centerBottom.add(textField);
	centerBottom.add(sendTextButton);
	panelCenter.add(centerBottom);
	panelCenter.add(textField);
	
	//Just a crappy list I made up to use the list feature on the side
	// will eventually need double click stuff.
	list = new JList(onlineUsers.toArray());
	list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	list.setLayoutOrientation(JList.VERTICAL);
	listScrollPane = new JScrollPane(list);
	listScrollPane.setPreferredSize(new Dimension(75, 80));
	panelWest = new JPanel();
	panelWest.add(listScrollPane);
	
	sendTextButton.addActionListener(this);
	
	frame.add(panelWest, BorderLayout.WEST);
	frame.add(panelSouth, BorderLayout.SOUTH);
	frame.add(panelCenter, BorderLayout.CENTER);
	frame.setTitle("Chat Client Plus");
	frame.setSize(350, 200);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setResizable(false);
    }
    
    private void initializeTestUsersOnline() {
	onlineUsers.add("bob");
	onlineUsers.add("jim");
	onlineUsers.add("derp");
	onlineUsers.add("derp");
	onlineUsers.add("derp");
	onlineUsers.add("derp");
	onlineUsers.add("derp");
	onlineUsers.add("derp");
    }
    
    public void actionPerformed(ActionEvent e) {
	if(e.getSource() == sendTextButton && !textField.getText().isEmpty()){
	    textArea.append(textField.getText() + "\n");
	    textField.setText("");
	    textArea.setCaretPosition(textArea.getDocument().getLength());
	}
    }

    public static void main(String[] args) {
	GUI gui = new GUI();
	gui.run();
	
    }
}