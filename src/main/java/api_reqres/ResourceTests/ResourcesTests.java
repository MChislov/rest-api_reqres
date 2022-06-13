package api_reqres.ResourceTests;

import org.junit.Test;

import api_reqres.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

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
}
