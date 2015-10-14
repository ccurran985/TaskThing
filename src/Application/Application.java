package Application;

import java.util.Scanner;

import Database.DatabaseHelper;
import Exceptions.FailedToRegisterException;
import Exceptions.InvalidCredentialsException;
import Models.User;

public class Application {
	private Scanner sc;
	private DatabaseHelper dbHelper;
	private User currentUser;
	public Application() {
		sc = new Scanner(System.in);
		dbHelper = new DatabaseHelper();
		
		if(hasAccount()) {
			currentUser = getLoginDetails();
			System.out.println("Welcome to the Darkside, " + currentUser.getUsername());
		} else {
			currentUser = getNewAccountDetails();
			System.out.println("Welcome to the Darkside, " + currentUser.getUsername());
		}
	}
	
	public boolean hasAccount() {
		System.out.println("Do you have an account?");
		String line = sc.nextLine();
		char option = line.toLowerCase().charAt(0);
		
		switch (option) {
		case 'y': return true;
		case 'n': return false;
		default: System.out.println("Try again");
			return hasAccount();
		}
	}
	
	public User getLoginDetails(){
		System.out.println("Enter Username");
		String username = sc.nextLine();
		System.out.println("Enter password");
		String password = sc.nextLine();
		try{
			User user = dbHelper.login(username, password);
			return user;
		} catch (InvalidCredentialsException e) {
			System.out.println("Invalid Username or Password");
			return getLoginDetails();
		}
	}
	
	public User getNewAccountDetails(){
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
}
