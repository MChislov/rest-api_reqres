package api_reqres.ResourceTests;

public class BodyResponseListResource {
	    public Integer id;
	    public String name;
	    public Integer year;
	    public String color;
	    public String pantone_value;
	    
		public BodyResponseListResource() {
			super();
		}

		public BodyResponseListResource(Integer id, String name, Integer year, String color, String pantone_value) {
			this.id = id;
			this.name = name;
			this.year = year;
			this.color = color;
			this.pantone_value = pantone_value;
		}

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}
		
		
}
