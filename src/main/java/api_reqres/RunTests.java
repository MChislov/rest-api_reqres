package api_reqres;

import org.junit.runners.Suite;

import api_reqres.ResourceTests.ResourcesTests;
import api_reqres.UsersManagementTests.UsersManagementTests;


import org.junit.runner.RunWith;


@RunWith(Suite.class)
@Suite.SuiteClasses({UsersManagementTests.class, ResourcesTests.class})

public class RunTests {
	public static void main(String[] args) {

	}
}