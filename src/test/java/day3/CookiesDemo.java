package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

public class CookiesDemo {
	
	@Test(priority=1)
	void testCookies()
	{
		given()
		
		 .when()
		  .get("https://www.google.co.in/")
		 
		 .then()
		  .cookie("AEC","AVcja2eic6h3Fm5cwLyDHsz295tMkI63kpFxQcBNSw3LgESix3g2VR8vyQ")
		 .log().all();
	}
	
	
	@Test(priority=2)
	void getCookiesInfo()
	{
		Response res=given()
		
		 .when()
		  .get("https://www.google.co.in/");
		  

		  
		
		//get single cookie info
		String cookie_valuer=res.getCookie("AEC");
		System.out.println(" The value of cokkies is ===> "+cookie_valuer );
		
		
		//get all cookies info
		Map<String,String>cookie_values =res.getCookies();
		System.out.println(cookie_values.keySet()); 
		for(String key:cookie_values.keySet())
		{
			String cookie_values1=res.getCookie(key);
			System.out.println(key+"   "+cookie_values1);

		}
	}


}
