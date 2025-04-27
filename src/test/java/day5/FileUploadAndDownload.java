package day5;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {
	
	@Test
	void singleFileUpload() {
		 File myfile= new File("C://Users//hp//OneDrive//Desktop//resume//AXA gbs//info.txt");

		given()
		.multiPart("file",myfile).contentType("multipart/form-data")
		
		.when()
		 .post("http://localhost:8080/uploadFile")
		 
		
		.then()
		.statusCode(200)
		.body("fileName", equalTo("info.txt")).log().all();
		
	}
	
	@Test
	void multipleFileupload()
	{

	    File myfile1 = new File("C://Users//hp//OneDrive//Desktop//resume//AXA gbs//info.txt");
	    File myfile2 = new File("C://Users//hp//OneDrive//Desktop//resume//AXA gbs//Abhishek Joshi reume.docx");

	    given()
	        .multiPart("files", myfile1)
	        .multiPart("files", myfile2)
	        .contentType("multipart/form-data")
	    .when()
	        .post("http://localhost:8080/uploadMultipleFiles")
	    .then()
	        .statusCode(200)
	        .body("[0].fileName", equalTo("info.txt"))
	        .body("[1].fileName", equalTo("Abhishek Joshi reume.docx"))
	        .log().all();
			 	
			

	}
	
	@Test
	void fileDownlaod() {
		
		given()
		
		.when()
		 .get("http://localhost:8080/downloadFile/info.txt")
		 
		.then()
		 .statusCode(200)
		 .log().body();
		 
	}
	

}
