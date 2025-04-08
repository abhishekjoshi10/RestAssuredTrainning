package day2;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PojoPractise {
	String studentId;
	 @Test(priority = 1)
	void creatinguser() {
		 Pojo_PostRequest pojodata=new Pojo_PostRequest();
		 pojodata.setName("Mrunali");
		 pojodata.setLocation("Meherun");
		 pojodata.setPhone("9511221839");
		 String course[]={"java","python"};
		 pojodata.setCourses(course);
		 
		 Response res =
		 given()
		  .contentType("application/json")
		  .body(pojodata)
		  
		  .when()
             .post("http://localhost:3000/students");
		 
		 studentId = res.jsonPath().getString("id");
		 
		 res.then()
		 .statusCode(201)
         .body("name", equalTo("Mrunali"))
         .body("location", equalTo("Meherun"))
         .body("phone", equalTo("9511221839"))
         .body("courses[0]", equalTo("java"))
         .body("courses[1]", equalTo("python"))
         .header("Content-Type", containsString("application/json"))
         .log().all();
 }
	  @Test(priority = 2)
	void DeletePojouser() {

		 given()
	        .when()
	            .delete("http://localhost:3000/students/" + studentId)
	        .then()
	            .statusCode(200);
	}
	
}



