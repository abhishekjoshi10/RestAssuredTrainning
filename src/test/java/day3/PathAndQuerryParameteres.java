package day3;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class PathAndQuerryParameteres {

	@Test
	void etstquerryAndPathParameters()
	{
		//https://reqres.in/api/users?page=2&id=5
		given()
		 .pathParam("mypath","users")
		 .queryParam("page", 2)
		 .queryParam("id", 5) //querryparameters
		
		.when()
		 .get("https://reqres.in/api/users{mypath}")
		
		.then()
		 .statusCode(200)
		 .log().all();
		 
	}
}
