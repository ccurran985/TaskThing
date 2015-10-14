package Application;

import java.util.Scanner;

import Database.DatabaseHelper;

public class Application {
	private Scanner sc;
	private DatabaseHelper dbHelper;
	
	public Application() {
		sc = new Scanner(System.in);
		dbHelper = new DatabaseHelper();
	}
	
	public boolean loginOrRegister() {
		System.out.println("Do you have an account?");
		String line = sc.nextLine();
		char option = line.toLowerCase().charAt(0);
		
		switch (option) {
		case 'y': return true;
		case 'n': return false;
		}
	}
}
