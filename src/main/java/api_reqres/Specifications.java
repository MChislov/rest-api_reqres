package api_reqres;

import io.restassured.RestAssured;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
	public static RequestSpecification requestSpecs(String url) {
		return new RequestSpecBuilder()
				.setBaseUri(url)
				.setContentType(ContentType.JSON)
				.build();
	}

	public static ResponseSpecification responseSpecRegisterSuccess() {
		return new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
	}
	
	public static ResponseSpecification responseSpecResponseStatus(Integer expectedStatus) {
		return new ResponseSpecBuilder()
				.expectStatusCode(expectedStatus)
				.build();
	}

	public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
		RestAssured.requestSpecification = request;
		RestAssured.responseSpecification = response;
	}
}
