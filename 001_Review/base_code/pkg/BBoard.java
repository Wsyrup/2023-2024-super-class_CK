package pkg;
import java.util.*;
import java.io.*;

public class BBoard {		// This is your main file that connects all classes.
	// Think about what your global variables need to be.

	//Every method works parially. Begin testing and debugging every method. The change password method gives
	//confusing results, and the topic method gets an error.

	//List of users
	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<Message> msgList = new ArrayList<Message>();
	Scanner sc;
	String title;

	//currentUser object
	User currentUser;


	// Default constructor that creates a board with a defaulttitle, empty user and message lists,
	// and no current user
	public BBoard() {
		title = "";	
		currentUser = null;
		this.sc = new Scanner(System.in);
	}

	// Same as the default constructor except it sets the title of the board
	public BBoard(String ttl) {	
		title = ttl;
		currentUser = null;
		this.sc = new Scanner(System.in);
	}

	// Gets a filename of a file that stores the user info in a given format (users.txt)
	// Opens and reads the file of all authorized users and passwords
	// Constructs a User object from each name/password pair, and populates the userList ArrayList.
	public void loadUsers(String inputFile) throws FileNotFoundException {
		//inputfile has to have the file extension included.
		File data = new File(inputFile);
		Scanner parser = new Scanner(data);

		String currentLine = "";
		String username = "";
		String password = "";

		while(parser.hasNextLine())
		{
			currentLine = parser.nextLine();
			
			username = currentLine.substring(0, currentLine.indexOf(" "));
			password = currentLine.substring(currentLine.indexOf(" ") + 1,currentLine.length());

			//Line used for debugging
			System.out.println("Username:" + username);

			System.out.println("Password: " + password);
			userList.add(new User(username, password));
		}

		System.out.println(userList.size());

		// for(int i = 0; i < userList.size(); i++)
		// {
		// 	userList.get(i).toString();
		// }
		
	}

	// Asks for and validates a user/password. 
	// This function asks for a username and a password, then checks the userList ArrayList for a matching User.
	// If a match is found, it sets currentUser to the identified User from the list
	// If not, it will keep asking until a match is found or the user types 'q' or 'Q' as username to quit
	// When the users chooses to quit, sayu "Bye!" and return from the login function
	public void login(){
		String user;
		String pass;
		//Scanner sc = new Scanner(System.in);
		
		while(true){
			System.out.print("Please enter a username: ");
			user = this.sc.nextLine();
			if(user.equalsIgnoreCase("Q"))
			{
				System.out.println("Bye!");
				return;
			}
			System.out.print("\nPlease enter a password: ");
			pass = this.sc.nextLine();
			System.out.println("");
			if(pass.equalsIgnoreCase("Q"))
			{
				System.out.println("Bye!");
				return;
			}

			for(int i = 0; i < userList.size(); i++)
			{
				if(userList.get(i).check(user, pass))
				{
					System.out.println("User found. Welcome " + userList.get(i).getUsername());
					currentUser = userList.get(i);
					return;
				}
				else 
				{
					System.out.println("User not found. Please try again.");
					break;
				}
			}
		}
	}
	
	// Contains main loop of Bulletin Board
	// IF and ONLY IF there is a valid currentUser, enter main loop, displaying menu items
	// --- Display Messages ('D' or 'd')
	// --- Add New Topic ('N' or 'n')
	// --- Add Reply ('R' or 'r')
	// --- Change Password ('P' or 'p')
	// --- Quit ('Q' or 'q')
	// With any wrong input, user is asked to try again
	// Q/q should reset the currentUser to 0 and then end return
	// Note: if login() did not set a valid currentUser, function must immediately return without showing menu
	public void run(){
		
		String action = "";

		System.out.println("");

		login();
		
		if(currentUser == null)
		{
			return;
		}

		System.out.print("Menu\n" +
			"  - Display Messages (\'D\' or \'d\')\n" +
			"  - Add New Topic (\'N\' or \'n\')\n" +
			"  - Add New Reply to a Topic (\'R\' or \'r\')\n" + 
			"  - Change Password (\'P\' or \'p\')\n" +
			"  - Quit (\'Q\' or \'q\')\n" +
			"Choose an action: ");
			
		action = sc.nextLine();
	
		while(true /*action.equalsIgnoreCase("q")*/)
		{
			//action = sc.nextLine();
			if(action.equalsIgnoreCase("d"))
			{
				System.out.print("\n");
				display();
				//continue;
				//break;
			}
			else if(action.equalsIgnoreCase("n"))
			{
				System.out.print("\n");
				addTopic();
				//continue;
				//break;
			}
			else if(action.equalsIgnoreCase("r"))
			{
				System.out.print("\n");
				addReply();
				//continue;
				//break;
			}
			else if(action.equalsIgnoreCase("p"))
			{
				System.out.print("\n");
				setPassword();
				//continue;
				//break;
			}
			else if(action.equalsIgnoreCase("q"))
			{
				System.out.print("Bye");
				
				//continue;
				break;
			}
			else
			{
				System.out.println("Please enter a valid action");
				//continue;
			}

			System.out.print("Menu\n" +
			"  - Display Messages (\'D\' or \'d\')\n" +
			"  - Add New Topic (\'N\' or \'n\')\n" +
			"  - Add New Reply to a Topic (\'R\' or \'r\')\n" + 
			"  - Change Password (\'P\' or \'p\')\n" +
			"  - Quit (\'Q\' or \'q\')\n" +
			"Choose an action: ");
			
			action = sc.nextLine();
			System.out.print("\n");

		}

		//sc.close();

		return;
	}

	// Traverse the BBoard's message list, and invote the print function on Topic objects ONLY
	// It will then be the responsibility of the Topic object to invoke the print function recursively on its own replies
	// The BBoard display function will ignore all reply objects in its message list
	private void display(){

		if(msgList.size() == 0)
		{
			System.out.println("There is nothing to print. Please create a topic.");
			return;
		}
		else
		{
			System.out.print("Message: " + msgList.get(0).getId());
			System.out.println("\"" + msgList.get(0).getSubject() + "\"");
			System.out.println("From: " + msgList.get(0).author + ": "+ "\"" + msgList.get(0).body + "\"\n");
			System.out.println("\n");
			for(int i = 0; i < msgList.size() - 1; i++)
			{
				if(msgList.get(i) instanceof Topic)
				{
					msgList.get(i).print(1);
				}
			}
			return;
		}
	}


	// This function asks the user to create a new Topic (i.e. the first message of a new discussion "thread")
	// Every Topic includes a subject (single line), and body (single line)

	/* 
	Subject: "Thanks"
	Body: "I love this bulletin board that you made!"
	*/

	// Each Topic also stores the username of currentUser; and message ID, which is (index of its Message + 1)

	// For example, the first message on the board will be a Topic who's index will be stored at 0 in the messageList ArrayList,
	// so its message ID will be (0+1) = 1
	// Once the Topic has been constructed, add it to the messageList
	// This should invoke your inheritance of Topic to Message
	private void addTopic(){
		String subject = "";
		String body = "";

		System.out.println("To create a new topic, follow the steps below. To quit, press q after the following prompts.");

		//Scanner sc = new Scanner(System.in);

		System.out.println("Please enter the subject of the topic:");
		subject = this.sc.nextLine();
		System.out.println("Please enter the body of the topic:");
		body = this.sc.nextLine();
		//sc.close();

		if(subject.equalsIgnoreCase("q") || body.equalsIgnoreCase("q"))
		{
			return;
		}

		Message tpc = new Topic(currentUser.getUsername(), subject, body, msgList.size()+1);
		msgList.add(tpc);
		System.out.println("Topic created\n");
		return;
	}

	// This function asks the user to enter a reply to a given Message (which may be either a Topic or a Reply, so we can handle nested replies).
	//		The addReply function first asks the user for the ID of the Message to which they are replying;
	//		if the number provided is greater than the size of messageList, it should output and error message and loop back,
	// 		continuing to ask for a valid Message ID number until the user enters it or -1.
	// 		(-1 returns to menu, any other negative number asks again for a valid ID number)
	
	// If the ID is valid, then the function asks for the body of the new message, 
	// and constructs the Reply, pushing back the Reply on to the messageList.
	// The subject of the Reply is a copy of the parent Topic's subject with the "Re: " prefix.
	// e.g., suppose the subject of message #9 was "Thanks", the user is replying to that message:


	/*
			Enter Message ID (-1 for Menu): 9
			Body: It was a pleasure implementing this!
	*/

	// Note: As before, the body ends when the user enters an empty line.
	// The above dialog will generate a reply that has "Re: Thanks" as its subject
	// and "It was a pleasure implementing this!" as its body.

	// How will we know what Topic this is a reply to?
	// In addition to keeping a pointer to all the Message objects in BBoard's messageList ArrayList
	// Every Message (wheather Topic or Reply) will also store an ArrayList of pointers to all of its Replies.
	// So whenever we build a Reply, we must immediately store this Message in the parent Message's list. 
	// The Reply's constructor should set the Reply's subject to "Re: " + its parent's subject.
	// Call the addChild function on the parent Message to push back the new Message (to the new Reply) to the parent's childList ArrayList.
	// Finally, push back the Message created to the BBoard's messageList. 
	// Note: When the user chooses to return to the menu, do not call run() again - just return fro mthis addReply function. 
	private void addReply(){
		String rebody = "";
		int reId = -2;
		//Scanner sc = new Scanner(System.in);

		if(msgList.size() == 0)
		{
			System.out.println("There is nothing to reply to. Please create a topic");
			return;
		}

		while(true)
		{
			System.out.println("Please enter the ID of the message you would like to reply to, or -1 to return to menu:");
			//this does not work with values that are not numbers.
			reId = this.sc.nextInt();	
			this.sc.nextLine();
			if(reId == -1)
			{
				System.out.println("returning to menu.");
				return;
			}
			else if(reId == 0)
			{
				System.out.println("Invalid ID, try again.");
			}
			else if(reId < -1 || reId > msgList.size()+1)
			{
				System.out.println("Please enter a valid ID");
			}
			else
			{
				break;
			}
		}
		System.out.println("Please enter the body of your reply:");
		rebody = this.sc.nextLine();

		Message parent = msgList.get(reId - 1);
		Message temp = new Reply(currentUser.getUsername(), "Re: " + parent.subject, rebody, msgList.size()+1);
		msgList.add(temp);
		parent.addChild(temp);
		return;
		//unsure if this works, especially with the id being msgList.size() + 1;
	}

	// This function allows the user to change their current password.
	// The user is asked to provide the old password of the currentUser.
	// 		If the received password matches the currentUser password, then the user will be prompted to enter a new password.
	// 		If the received password doesn't match the currentUser password, then the user will be prompted to re-enter the password. 
	// 		The user is welcome to enter 'c' or 'C' to cancel the setting of a password and return to the menu.
	// Any password is allowed except 'c' or 'C' for allowing the user to quit out to the menu. 
	// Once entered, the user will be told "Password Accepted." and returned to the menu.
	private void setPassword(){
		//Scanner sc = new Scanner(System.in);
		String passReq = "";
		String oldPass = "";

		//question outside of loop so user is only prompted to reenter old password upon failure
		System.out.println("What would you like to change your password to?");
		passReq = this.sc.nextLine();

		while(true)
		{
			
			System.out.println("Please provide your old password (Enter \"c\" to quit)");
			oldPass = this.sc.nextLine();

			if(oldPass.equalsIgnoreCase("c"))
			{
				return;
			}
			
			if(currentUser.setPassword(oldPass, passReq))
			{
				System.out.println("Password Accepted");
				break;
			}
		}
	}

}
