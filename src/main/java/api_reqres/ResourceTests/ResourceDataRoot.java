package api_reqres.ResourceTests;

public class ResourceDataRoot {
	public ResourceData data;
	public ResourceSupport support;
	
	public ResourceDataRoot() {
		super();
	}

	public ResourceDataRoot(ResourceData data, ResourceSupport support) {
		this.data = data;
		this.support = support;
	}

	public ResourceData getData() {
		return data;
	}

	public void setData(ResourceData data) {
		this.data = data;
	}

	public ResourceSupport getSupport() {
		return support;
	}

	public void setSupport(ResourceSupport support) {
		this.support = support;
	}



}
