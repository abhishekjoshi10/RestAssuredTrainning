package day2;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DifferentWaysToCreatePostRequestBody3 {

    // 3) Post request body creation using POJO class
    String studentId;

    @Test(priority = 1)
    void testPostusingPOJO() {
        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setName("Scott");
        data.setLocation("France");
        data.setPhone("123654");
        String[] courseArr = { "C#", "C++" };
        data.setCourses(courseArr);

        Response res =
            given()
                .contentType("application/json")
                .body(data)
            .when()
                .post("http://localhost:3000/students"); // <-- Make sure this is the correct URL

        // Store studentId for deletion
        studentId = res.jsonPath().getString("id");

        // Validations
        res.then()
            .statusCode(201)
            .body("name", equalTo("Scott"))
            .body("location", equalTo("France"))
            .body("phone", equalTo("123654"))
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
