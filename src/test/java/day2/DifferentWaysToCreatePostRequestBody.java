package day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * Different ways to create Post request body
 * 1) Post request body using Hashmap
 * 2) Post request body creation using  Org.json
 * 3) Post request body creation using POJO class
 * 4) Post request body using external json file data
 */
public class DifferentWaysToCreatePostRequestBody {
	// 1) Post request body using Hashmap

	@Test(priority=1)
	void testPostusingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		
		String courseArr[]= {"C","C++"};
		data.put("courses", courseArr);
		
		given()
		   .contentType("application/json")
		   .body(data)
		
		
		.when()
		  .post("http://localhost:3000/students")
		
		.then()
		 .statusCode(201)
		 .body("name", equalTo("Scott"))
		 .body("location", equalTo("France"))
		 .body("phone", equalTo("123456"))
		 .body("courses[0]", equalTo("C"))
		 .body("courses[1]", equalTo("C++"))
		 .header("Content-Type", containsString("application/json")) // Updated assertion
		 .log().all();
		 

	}
	
	//deleting student record
	@Test(priority=2)
	void testDelete()
	{
		given()
		
		.when()
		  .delete("http://localhost:3000/students/4")
		
		.then()
		 .statusCode(200);
		
	}
}
