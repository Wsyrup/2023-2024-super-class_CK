package pkg;
import java.util.*;
import java.io.*;

public class User {
	//initialize variables
	String username;
	String password;


	// Creates a User with empty name and password.
	public User() {
		username = "";
		password = "";	
	}

	// Creates a User with given username and password.
	public User(String usr, String pwd) {
		this.username = usr;
		this.password = pwd;
	}

	// Returns the username
	public String getUsername(){
		return this.username;
	}

	// Returns true if the stored username/password matches the parameters. Otherwise returns false.
	// Note that, even with a User with empty name and password, this is actually a valid User object (it is the default User), 
	// This function must still return false if given an empty username string.  
	public boolean check(String usr, String psd){
		//returns false given empty string
		//returns true if the check is successful, and returns false for all other cases.
		if(usr.equals("") || usr.equals(null))
		{
			System.out.println("Empty user passed in.");
			return false;
		}
		else if(usr.equals(this.username) && psd.equals(this.password))
		{
			return usr.equals(this.username) && psd.equals(this.password);
		}
		else
		{
			System.out.println("Either the username or the password was incorrect. Try again.");
			return false;
		}
		
	}

	// Sets a new password.
	// This function should only set the password if the current (old) password is passed in.
	// Also, a default User cannot have its password changed. 
	// Return true if password changed, return false if not.

	//for some reason, this method does not work. "old password..." is always printed, even
	//if password change is successful. 
	public boolean setPassword(String oldPass, String newPass){
		//takes care of the default user issue.
		if(this.username.equals(""))
		{
			System.out.println("Default user cannot change password");
			return false;
		}
		//checks if the old password equals the stored password before changing
		//otherwise, prints message and returns false
		else if(oldPass.equals(this.password))
		{
			this.password = newPass;
			System.out.println("Password changed successfully");
			return true;
		}
		else
		{
			System.out.println("Old password does not match. Please try again.");
			return false;
		}
		
	}

	//tostring method for testing purposes.
	@Override
	public String toString()
	{
		return new String("Username: " + this.getUsername() + "Password: " + this.password);
	}
}
