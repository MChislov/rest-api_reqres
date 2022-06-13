package api_reqres.UsersManagementTests;

public class UserDataRegister extends UserData{
	private String password;
	
	public UserDataRegister(String email, String password) {
		this.email = email;
		this.password = password;
	}
		
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
