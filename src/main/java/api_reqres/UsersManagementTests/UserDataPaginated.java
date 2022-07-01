package api_reqres.UsersManagementTests;

import java.util.ArrayList;

import api_reqres.ResourceTests.ResourceSupport;

public class UserDataPaginated{
    public ArrayList<UserData> data;
    public ResourceSupport support;
    
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    
	public int getPage() {
		return page;
	}
	public int getPer_page() {
		return per_page;
	}
	public int getTotal() {
		return total;
	}
	public int getTotal_pages() {
		return total_pages;
	}
}
