package api_reqres.UsersManagementTests;

public class UserData {
	public Integer id;
	public String email;
	public String first_name;
	public String last_name;
	public String name;
	public String avatar;
	public String job;
	
	public UserData() {
		super();
	}
	
	public UserData(String email) {
		this.email=email;
	}

	public UserData(Integer id, String email, String first_name, String last_name, String avatar) {
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.avatar = avatar;
	}
	

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getAvatar() {
		return avatar;
	}
}
