package api_reqres.UsersManagementTests;

import java.util.Date;

public class UserDataCreate extends UserData {
	    public Date createdAt;
	    
	    public UserDataCreate() {
			super();
	    }
	    
	    public UserDataCreate(String name, String job, Integer id, Date createdAt) {
			super();
			this.name = name;
			this.job = job;
			this.id = id;
			this.createdAt = createdAt;
		}

		public Date getcreatedAt() {
			return createdAt;
		}
	    
	    
}
