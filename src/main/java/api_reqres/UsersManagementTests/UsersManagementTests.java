package api_reqres.UsersManagementTests;
import api_reqres.Resources;

import org.junit.Test;

import api_reqres.Specifications;

import org.hamcrest.Matchers;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import io.qameta.allure.Description;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;



public class UsersManagementTests {
	private final static String URL = "https://reqres.in/";
	@Test
	public void UserEmailDomain() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		List<UserData> users = 
				given()
				.when()
				.get("api/users?page=2")
				.then().extract().body().jsonPath().getList("data", UserData.class);

		 for (UserData element : users) {
			 assertTrue(element.getAvatar().contains(element.getId().toString()));
			 assertTrue(element.getEmail().endsWith("@reqres.in"));
		 }

	}
	
	@Test
	public void UserListJsonSchemaValidation() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		given()
		.when()
		.get("api/users?page=2")
		.then()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(Resources.JsonUsersList));
	}
	
	@Test
	public void UserSingleJsonSchemaValidation() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		given()
		.when()
		.get("api/users/2")
		.then()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(Resources.JsonUsersSingle));
	}	
	
	@Test
	public void UserDoesNotExists() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(404));
		given()
		.when()
		.get("api/users/23")
		.then();
	}	
	
	@Test
	public void JsonValuesCheck() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		given()
		.when()
		.get("api/users?page=2")
		.then().body("data.id", Matchers.hasItems(7, 11));
	}
	
	@Test
	public void DeleteUser() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(204));
				given()
				.when()
				.delete("api/users/2")
				.then().log().body();
	}
	
	@Test
	public void RegisterUserSuccessful() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		Integer id = 4;
		String token = "QpwL5tke4Pnpja7X4";
		UserDataRegister user = new UserDataRegister("eve.holt@reqres.in", "pistol");
		BodyResponseRegistrationSuccessful registration = given()
				.body(user)
				.post("api/register")
				.then()
				.log().all()
				.extract().as(BodyResponseRegistrationSuccessful.class);
		assertEquals(id, registration.id);
		assertEquals(token, registration.token);
	}
	
	@Test
	public void LoginUserSuccessful() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		String token = "QpwL5tke4Pnpja7X4";
		UserDataRegister user = new UserDataRegister("eve.holt@reqres.in", "pistol");
		BodyResponseRegistrationSuccessful login = given()
				.body(user)
				.post("api/register")
				.then()
				.log().all()
				.extract().as(BodyResponseRegistrationSuccessful.class);
		assertEquals(token, login.token);
	}
	
	@Test
	public void LoginUserUnsuccessful() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(400));
		String error = "Missing password";
		UserData user = new UserData("eve.holt@reqres.in");
		BodyResponseMissedPassword failedLogin = given()
				.body(user)
				.post("api/register")
				.then()
				.log().body()
				.extract()
				.body().as(BodyResponseMissedPassword.class);
		System.out.println(failedLogin.getError());
		failedLogin.getError().equals(error);
	}
	
	@Test
	public void RegisterUserWithoutPassword() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(400));
		UserDataRegister user = new UserDataRegister("eve.holt@reqres.in", "");
		String output = given()
				.body(user)
				.post("api/register")
				.then()
				.log().all()
				.extract().body().asString();
		assertTrue(output.contains("Missing password"));
	}
	
	@Test
	public void UpdateUserPutDateTime() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		UserDataUpdate user = new UserDataUpdate("morpheus", "zion resident");
		Date curDate = new Date();
		UserDataUpdate userUpdate = given()
				.body(user)
				.put("api/users/2")
				.then()
				.log().all()
				.extract().as(UserDataUpdate.class);
		System.out.println(curDate);
		Date updateDate = userUpdate.getUpdatedAt();
		System.out.println(curDate);
		System.out.println(updateDate);
		assertTrue(curDate.before(updateDate));
	}
	
	@Test
	public void UpdateUserPatchDateTime() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		UserDataUpdate user = new UserDataUpdate("morpheus", "zion resident");
		Date curDate = new Date();
		UserDataUpdate userUpdate = given()
				.body(user)
				.put("api/users/2")
				.then()
				.log().all().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(Resources.JsonUsersUpdate))
				.extract().as(UserDataUpdate.class);
		System.out.println(curDate);
		Date updateDate = userUpdate.getUpdatedAt();
		System.out.println(curDate);
		System.out.println(updateDate);
		assertTrue(curDate.before(updateDate));
	}
	
	@Test
	@Description("Pagination 2 page/2 per per page")
	public void UsersListPagination() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		UserDataPaginated response = given()
				.get("api/users?page=2&per_page=2")
				.then()
				.log().all()
				.extract().as(UserDataPaginated.class);
		System.out.println(response);
		System.out.println(response.getPer_page());
		for (UserData item:response.data) {
			System.out.println(item.getId()); 			
		}
		System.out.println(response.data.get(0).getAvatar());
	}
	
}
