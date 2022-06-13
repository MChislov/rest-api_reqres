package api_reqres.UsersManagementTests;

import java.util.Date;

public class UserDataUpdate extends UserData {
	    public Date updatedAt;
	    
	    public UserDataUpdate() {
			super();
	    }
	    
	    public UserDataUpdate(String name, String job, Integer id, Date updatedAt) {
			super();
			this.name = name;
			this.job = job;
			this.id = id;
			this.updatedAt = updatedAt;
		}
	    
	    public UserDataUpdate(String name, String job) {
			super();
			this.name = name;
			this.job = job;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}
	    
	    
}
