package day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class LoggingDemo {
	@Test(priority = 1)
	void testLogs() 
	{
		given()
		
		 .when()
		   .get("https://reqres.in/api/users?page=2")
		 
		  .then()
		   .log().all()
		   .log().body()
		   .log().cookies();
		   
	}

}
