package day2;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DifferentWaysToCreatePostRequestBody2 {
	
	// 2) Post request body creation using Org.json
	String studentId;
	@Test(priority = 1)
	void testPostusingJsonLibrary() {
		 JSONObject data = new JSONObject();
		  data.put("name", "Scott");
		    data.put("location", "France");
		    data.put("phone", "654321");
		    String[] courseArr = { "C", "C++" };
		    data.put("courses", courseArr);

		    Response res = given()
		        .contentType("application/json")
		        .body(data.toString())
		    .when()
		        .post("http://localhost:3000/students");

		    res.then()
		        .statusCode(201)
		        .body("name", equalTo("Scott")) // adjust if "name" is nested
		        .log().all();

		    studentId = res.jsonPath().getString("id");
		    System.out.println("Captured ID: " + studentId);

	}

	// deleting student record
	@Test(priority = 2)
	void testDeleteusingJsonLibrary() {
		
		given()

				.when()
				.delete("http://localhost:3000/students/" + studentId)

					.then()
					   .statusCode(200);

	}


}
