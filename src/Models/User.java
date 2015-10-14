package Models;

public class User {
	private String username;
	private String fName;
	private String lName;

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
