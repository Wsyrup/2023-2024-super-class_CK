package pkg;
import java.util.*;
import java.io.*;

public class Topic extends Message {

	// Default Constructor
	public Topic() {
		super.author = "";
		super.subject = "";
		super.body = "";
		super.id = -1;
	}

	// Parameterized constructor
	public Topic(String auth, String subj, String bod, int i) {
		super.author = auth;
		super.subject = subj;
		super.body = bod;
		super.id = i;
	}

	// Returns if it's a reply (false)
	public boolean isReply(){
		System.out.println("Topic, not reply");
		return false;
	}
}
