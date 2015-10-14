package Application;

import java.util.Scanner;

import Database.DatabaseHelper;
import Exceptions.FailedToRegisterException;
import Exceptions.InvalidCredentialsException;
import Exceptions.UserAccountBlockedException;
import Exceptions.UserNotFoundException;
import Models.User;

public class Application {
	private Scanner sc;
	private DatabaseHelper dbHelper;
	private User currentUser;
	private boolean running = true;
	
	public Application() {
		sc = new Scanner(System.in);
		dbHelper = new DatabaseHelper();
		
		if (hasAccount()) {
			currentUser = getLoginDetails();
			System.out.println("Welcome to the Darkside, " + currentUser.getUsername());
		} else {
			currentUser = getNewAccountDetails();
			System.out.println("Welcome to the Darkside, " + currentUser.getUsername());
		}
		
		while (running) {
			System.out.println("What do you want to do?");
			System.out.println("h for help");
			getInput();
		}
	}
	
	public boolean hasAccount() {
		System.out.println("Do you have an account? Y/N");
		String line = sc.nextLine();
		char option = line.toLowerCase().charAt(0);

		switch (option) {
		case 'y':
			return true;
		case 'n':
			return false;
		default:
			System.out.println("Try again");
			return hasAccount();
		}
	}

	public User getLoginDetails() {
		System.out.println("Enter Username");
		String username = sc.nextLine();
		System.out.println("Enter password");
		String password = sc.nextLine();
		try {
			User user = dbHelper.login(username, password);
			return user;
		} catch (InvalidCredentialsException e) {
			System.out.println("Invalid Username or Password");
			return getLoginDetails();
		} catch (UserAccountBlockedException e) {
			System.out.println("This user account has been blocked");
			return getLoginDetails();
		}
	}

	public User getNewAccountDetails() {
		System.out.println("Enter FirstName");
		String fName = sc.nextLine();
		System.out.println("Enter LastName");
		String lName = sc.nextLine();
		System.out.println("Enter Username");
		String username = sc.nextLine();
		System.out.println("Enter password");
		String password = sc.nextLine();
		try {
			return dbHelper.register(fName, lName, username, password);
		} catch (FailedToRegisterException e) {
			System.out.println("Failed to create account");
			return getNewAccountDetails();
		}
	}
	
	private void getInput(){
		String input = sc.nextLine();
		char option = input.toLowerCase().charAt(0);
		 switch (option) {
		 case 'b': blockUser(); break;
		 case 'h': printHelp(); break;
		 case 'l': doLogout(); break;
		 case 'u': unblockUser(); break;
		 case 'x': doExit(); break;
		 }
	}
	
	private void blockUser() {
		if(currentUser.isAdmin()) {
			System.out.println("Enter Username of user to block: ");
			String username = sc.nextLine();
			try {
				dbHelper.blockUser(username, 1);
			} catch (UserNotFoundException e) {
				System.out.println("User not found");
				blockUser();
			}
		} else {
			System.out.println("You do not have permissions to block users");
		}
	}

	private void unblockUser() {
		if(currentUser.isAdmin()) {
			System.out.println("Enter Username of user to unblock: ");
			String username = sc.nextLine();
			try {
				dbHelper.blockUser(username, 0);
			} catch (UserNotFoundException e) {
				System.out.println("User not found");
				unblockUser();
			}
		} else {
			System.out.println("You do not have permissions to block users");
		}
	}

	private void printHelp(){
		System.out.println("b = block user");
		System.out.println("l = logout");
		System.out.println("u = unblock user");
		System.out.println("x = exit");
	}
	
	private void doLogout(){
		if(currentUser != null) {
			System.out.println(currentUser.getUsername() + " has been logged out");
			currentUser = null;
			getLoginDetails();
		}
	}
	
	private void doExit() {
		System.out.println("Goodbye");
		System.exit(0);
		running = false; 
	}
}
