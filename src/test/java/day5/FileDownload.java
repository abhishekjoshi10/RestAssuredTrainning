package day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class FileDownload {
	
	@Test
	void validateFileDownload() throws IOException {
	    Response response = given()
	        .when()
	            .get("http://localhost:8080/downloadFile/info.txt")
	        .then()
	            .statusCode(200)
	            .header("Content-Disposition", containsString("info.txt"))
	            .extract()
	            .response();

	    // Save file locally (optional)
	    byte[] fileData = response.getBody().asByteArray();
	    File downloadedFile = new File("C://Users//hp//Downloads//downloaded_info.txt");
	    FileOutputStream fos = new FileOutputStream(downloadedFile);
	    fos.write(fileData);
	    fos.close();
	    
	    // Optional: Validate content (if required)
	    // Example: Check if file size > 0
	    assert downloadedFile.length() > 0 : "Downloaded file is empty!";
	}


}
