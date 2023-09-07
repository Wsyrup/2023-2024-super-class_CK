package pkg;
import java.util.*;
import java.io.*;

public class Message {

	ArrayList<Message> messageList = new ArrayList<Message>();
	String author;
	String subject;
	String body;
	int id;

	// Default Constructor
	public Message() {
		author = "";
		subject = "";
		body = "";
		id = -1;
	}
	
	// Parameterized Constructor
	public Message(String auth, String subj, String bod, int i) {
		this.author = auth;
		this.subject = subj;
		this.body = bod;
		this.id = i;
	}

	// This function is responsbile for printing the Message
	// (whether Topic or Reply), and all of the Message's "subtree" recursively:

	// After printing the Message with indentation n and appropriate format (see output details),
	// it will invoke itself recursively on all of the Replies inside its childList, 
	// incrementing the indentation value at each new level.

	// Note: Each indentation increment represents 2 spaces. e.g. if indentation ==  1, the reply should be indented 2 spaces, 
	// if it's 2, indent by 4 spaces, etc. 
	//integer i used to parse through the arraylist;
	//int i = 0;
	public void print(int indentation){
		//go through the childlist
		//check if child is a reply
		//if yes, print with indentation and remove from list
		//if no, skip
		//continue until childlist is empty (?)
		//int originalSize = messageList.size();

		//i has to represent the size of the arraylist which has not yet been
		//displayed by the method. 

		//System.out.println("---------------------------------------------------" + "\n");


		System.out.println("\"" + this.getSubject() + "\"");
		System.out.println("From: " + this.author + " "+ "\"" + this.body + "\"\n");
		System.out.println("\n");

		//focus on this last
		if(!messageList.isEmpty())
		{
			for(int i = 0; i < messageList.size(); i++)
			{
				// System.out.println(correctIndents(indentation) + "Message #" + messageList.get(i).getId() + ": \"" + messageList.get(i).getSubject() + "\"");
				// System.out.println(correctIndents(indentation) + "From: " + messageList.get(i).author + " \"" + messageList.get(i).body + "\n");
				// System.out.print("\n");
				System.out.print(correctIndents(indentation) + "Message #" + this.messageList.get(i).getId() + ": ");
				this.messageList.get(i).print(indentation++);
			}
		}
			

		System.out.println("---------------------------------------------------");
		
		return;
		

		
	}

	//helper function used for the display method.
	public String correctIndents(int indentation)
	{
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < indentation; i++)
		{
			sb.append("  ");
		}
		return sb.toString();
	}
	

	// Default function for inheritance
	public boolean isReply(){
		//print used for debugging
		System.out.println("Message, not reply");
		return false;
	}

	// Returns the subject String
	public String getSubject(){
		//simple get function
		return this.subject;
	} 

	// Returns the ID
	public int getId(){
		//simple get function
		return this.id;
	}

	// Adds a child pointer to the parent's childList.
	public void addChild(Message child){
		this.messageList.add(child);
	}

}
