package Models;

public class User {

	private String username;
	private String fName;
	private String lName;
	private boolean isAdmin, isBlocked;
	
	public User() {
		isAdmin = false;
	}

	public User(String username) {
		this.username = username;
		isAdmin = false;
	}
	
	public User(String fName, String lName, String username) {
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		isAdmin = false;
		isBlocked = false;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
}
