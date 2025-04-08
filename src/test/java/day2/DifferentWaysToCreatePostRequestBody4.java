package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DifferentWaysToCreatePostRequestBody4 {

	// 4) Post request body using external json file data
	  String studentId;

	    @Test(priority = 1)
	    void testPostusingExternalJsonFile() throws FileNotFoundException {
	    File f= new File("C:\\Users\\hp\\OneDrive\\Desktop\\NEWECLIPSE\\RestAssuered\\RestAssuredTrainning\\BODY.json");
	    FileReader fr = new FileReader(f);
	    JSONTokener jt = new JSONTokener(fr);
	    JSONObject data = new JSONObject(jt);
	        Response res =
	            given()
	                .contentType("application/json")
	                .body(data.toString())
	            .when()
	                .post("http://localhost:3000/students"); // <-- Make sure this is the correct URL

	        // Store studentId for deletion
	        studentId = res.jsonPath().getString("id");

	        // Validations
	        res.then()
	            .statusCode(201)
	            .body("name", equalTo("Scott"))
	            .body("location", equalTo("France"))
	            .body("phone", equalTo("123456"))
	            .body("courses[0]", equalTo("C#"))
	            .body("courses[1]", equalTo("C++"))
	            .header("Content-Type", containsString("application/json"))
	            .log().all();
	    }

	    // deleting student record
	    @Test(priority = 2)
	    void testDelete() {
	        given()
	        .when()
	            .delete("http://localhost:3000/students/" + studentId)
	        .then()
	            .statusCode(200);
	    }

}
