package day5;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

/*
 * Topics Covered
How to validate XML Body response
How to validate File Upload API
How to validate File Download API
 */
public class ParsingXMLResponse {

	@Test
	void testXMLResponse1()
	{
		//Approach 1
		given()
		
		.when()
		 .get("http://restapi.adequateshop.com/api/Traveler?page=1")
		 
		.then()
		  .statusCode(200)
		  .header("Content-Type", "application/xml; charset=utf-8")
		  .body("TravelerinformationResponse.page", equalTo("1"))
		  .body("TravelerinformationResponse.traveleres.Travelerinformation[0].name", equalTo("Vijay Bharath Reddy"));
		 
	}
	
	
	@Test
	void testXMLResponse2()
	{
		//Approach 2
		
		Response Res=
				given()
				 // .contentType(ContentType.JSON)
		
		.when()
		 .get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(Res.getStatusCode(),200);	
		Assert.assertEquals(Res.header("Content-Type"),"application/xml; charset=utf-8");
		String pageNo=Res.xmlPath().get("TravelerinformationResponse").toString();
		Assert.assertEquals(pageNo,"1");
		String travelName=Res.xmlPath().get("TravelerinformationResponse.traveleres.Travelerinformation[0].name").toString();
		Assert.assertEquals(travelName,"Vijay Bharath Reddy");
//		.then()
//		  .statusCode(200)
//		  .header("Content-Type", "application/xml; charset=utf-8")
//		  .body("TravelerinformationResponse.page", equalTo("1"))
//		  .body("TravelerinformationResponse.traveleres.Travelerinformation[0].name", equalTo("Vijay Bharath Reddy"));
//		 
	}
	
	
	
	@Test
	void testXMLResponseBody()
	{
		//Approach 2
		
		Response Res=
				given()
				
				.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlobj=new XmlPath(Res.asString());
		
		//Verify total number of travellers
		List<String>travellers=xmlobj.getList("TravelerinformationResponse.traveleres.Travelerinformation");		
		Assert.assertEquals(travellers.size(),10);	
		
		
		
		//verify  traveller name is present in response
		List<String>travellers_names=xmlobj.getList("TravelerinformationResponse.traveleres.Travelerinformation.name");
		boolean status=false;
        for(String travellersnames:travellers_names)
        {
        	if (travellersnames.equals("Vijay Bharath Reddy"))
        	{
        		status=true;
        		break;
        	}
        }
        
        Assert.assertEquals(status,true);
	}
	
	
	
	
	
	
	
}
