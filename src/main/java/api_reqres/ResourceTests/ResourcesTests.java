package api_reqres.ResourceTests;

import org.junit.Test;

import api_reqres.Resources;
import api_reqres.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import io.restassured.module.jsv.JsonSchemaValidator;

public class ResourcesTests {
	private final static String URL = "https://reqres.in";
	@Test
	public void ResourceMultipleEntries() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		List<ResourceData> resources = 
				  given()
				  .when()
				  .get("/api/unknown")
				  .then().extract().body().jsonPath().getList("data", ResourceData.class);
		 
		for (ResourceData element : resources) {
		  assertTrue(element.getColor().length()==7);
		  assertTrue(element.getYear()>=2000);
		  assertTrue(element.getPantone_value().matches("\\d{2}\\-\\d{4}"));
		  }
	}
	
	@Test
	public void ResourceListJsonValidation() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
				  given()
				  .when()
				  .get("/api/unknown")
				  .then()
				  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(Resources.JsonResoursesList));
	}
	
	@Test
	public void ResourceSingleJsonValidation() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
				  given()
				  .when()
				  .get("/api/unknown/2")
				  .then()
				  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(Resources.JsonResoursesSingle));
	}
	
	@Test
	public void ResourceSingleEntry() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		ResourceData resource =  given()
				  .when()
				  .get("/api/unknown/2")
				  .then().extract().body().as(ResourceDataRoot.class).getData();
		assertTrue(resource.getColor().length()==7);
		assertTrue(resource.getYear()>=2000);
		assertTrue(resource.getPantone_value().matches("\\d{2}\\-\\d{4}"));
	}

	@Test
	public void ResourceSingleEntryUnavailable() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(404));
					given()
					.when()
					.get("/api/unknown/23")
					.then();
	}
	
	@Test
	public void ListResourceData() {
		Specifications.installSpecification(Specifications.requestSpecs(URL), Specifications.responseSpecResponseStatus(200));
		List<BodyResponseListResource> data = 
				given()
				.when()
				.get("api/unknown")
				.then().extract().body().jsonPath().getList("Datum", BodyResponseListResource.class);
		Integer previos_year = 1900;
		 for (BodyResponseListResource element : data) {
			 assertTrue(element.getYear()>=previos_year);
			 previos_year = element.getYear();
		 }

	}
}
