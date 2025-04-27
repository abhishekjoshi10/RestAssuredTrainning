package day5;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;


public class ValidateXMLBody {
	

	
	@Test
	void testNASA_JSONResponse() {
	    given()
	    .when()
	        .get("https://api.nasa.gov/neo/rest/v1/feed?start_date=2024-04-01&end_date=2024-04-08&api_key=DEMO_KEY")
	    .then()
	        .statusCode(200)
	        .contentType(ContentType.JSON);  // NOT XML
	}
	
	
	@Test
	void testMockXML()
	{
	    given()
	    .when()
	        .get("https://run.mocky.io/v3/38d9bbda-64cf-4f53-8b08-317a4cfb7d87")
	    .then()
	        .statusCode(200)
	        .contentType(ContentType.XML)
	        .body("TravelerinformationResponse.page", equalTo("1"))
	        .body("TravelerinformationResponse.traveleres.Travelerinformation[0].name", equalTo("Vijay Bharath Reddy"));
	}
	
	@Test
	void validateXmlResponse() {
	    given()
	        .when()
	            .get("http://localhost:8080/getEmployeeDetailsXML")
	        .then()
	            .statusCode(200)
	            .contentType(ContentType.XML)
	            .body("employee.name", equalTo("Abhishek Joshi"))
	            .body("employee.id", equalTo("12345"))
	            .body("employee.department", equalTo("Testing"))
	            .log().all();
	}





}
