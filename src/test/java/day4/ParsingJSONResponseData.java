package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.json.JSONObject;


public class ParsingJSONResponseData {

	@Test(priority=1)
	void testJsonResponse1()
	{
		//Approach 1
		
		
		given()
		  .contentType("contentType.JSON")
		
		.when()
		  .get("http://localhost:3000/store")
		
		.then()
		 .statusCode(200)
		// .header("content-type","application/json; charset=utf-8")
         .header("Content-Type", containsString("application/json"))
		 .body("book[3].title", equalTo("The Lord of the Rings"));
	}
	
	
	
	@Test(priority=2)
	void testJsonResponse2()
	{
		//Approach 2
		
		Response Res=
		given()
		  .contentType(ContentType.JSON)
		
		.when()
		  .get("http://localhost:3000/store");
		
//		.then()
//		 .statusCode(200)
//		// .header("content-type","application/json; charset=utf-8")
//         .header("Content-Type", containsString("application/json"))
//		 .body("book[3].title", equalTo("The Lord of the Rings"));
		Assert.assertEquals(Res.getStatusCode(),200);
		Assert.assertEquals(Res.header("Content-Type"),"application/json");
        String bookname=Res.jsonPath().get("book[3].title").toString();
        System.out.println("String : "+ bookname);
        Assert.assertEquals(bookname,"The Lord of the Rings");
        
        JSONObject jo= new JSONObject(Res.asString());
        
        for(int i=0;i<jo.getJSONArray("book").length();i++)
        {
        	String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
        	System.out.println("String title : "+ booktitle);
        }
        
        
        // Extracting all titles from the 'book' array
        List<String> titles = Res.jsonPath().getList("book.title");

        System.out.println("Book Titles:");
        for (String title : titles) {
            System.out.println("- " + title);
        }

        // Optional: Validate that one of the titles matches expected
        Assert.assertTrue(titles.contains("The Lord of the Rings"));
        
        boolean status=false;
        for(int y=0;y<jo.getJSONArray("book").length();y++)
        {
        	String booktitles=jo.getJSONArray("book").getJSONObject(y).get("title").toString();
        	System.out.println("String title : "+ booktitles);
        	
        	if(booktitles.equals("The Lord of the Rings"))
        	{
        		status=true;
        		break;
        	}
        	
        	
        }
        
        Assert.assertEquals(status,true);
        
        double totalprice=0;
        for(int i=0;i<jo.getJSONArray("book").length();i++)
        {
        	String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
        	totalprice=totalprice+Double.parseDouble(price);
        }
        
        System.out.println("total price of book"+totalprice);
        Assert.assertEquals(totalprice,526);
     
        
        
       
	}
	

}
