package api_reqres.UsersManagementTests;

public class BodyResponseRegistrationSuccessful {
	    public Integer id;
	    public String token;
	    
		public BodyResponseRegistrationSuccessful() {
			super();
		}

	    public BodyResponseRegistrationSuccessful(String token) {
			this.token = token;
		}
	    
	    public BodyResponseRegistrationSuccessful(Integer id, String token) {
			this.id = id;
			this.token = token;
		}

		public int getId() {
			return id;
		}

		public String getToken() {
			return token;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setToken(String token) {
			this.token = token;
		}
	    
}
