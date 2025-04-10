package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
	@Test(priority = 1)
	void testHeaders() {
		given()

				.when().get("https://www.google.co.in/")

				.then()
				  // .log().all()
				   .header("Content-Type", "text/html; charset=ISO-8859-1")
				   .and()
		           .header("Content-Encoding", "gzip")
		           .and()
		           .header("Server", "gws")
		           .log().headers();
		

	}
	
	
	@Test(priority = 2)
	void getHeaders() {
		
		Response res=given()

				.when().get("https://www.google.co.in/");
		
		 // get single header info
		String headervalue=res.getHeader("Content-Type");
		System.out.println("The value of Content-Type header is : "+headervalue);
		
		//get all headers info
		Headers myheaders=res.getHeaders();
		for(Header heads:myheaders)
		{
			System.out.println(heads.getName()+"    "+heads.getValue());
		}

				  

	}


}
