package pkg;
import java.util.*;
import java.io.*;

public class Reply extends Message {

	// Default Constructor
	public Reply() {
		super.author = "";
		super.subject = "";
		super.body = "";
		super.id = -1;
	}

	// Parameterized Constructor
	public Reply(String auth, String subj, String bod, int i) {
		super.author = auth;
		super.subject = subj;
		super.body = bod;
		super.id = i;

		super.addChild(this);
	}

	// Returns if this is a reply (true)
	public boolean isReply(){
		System.out.println("Reply");
		return true;
	}
}
