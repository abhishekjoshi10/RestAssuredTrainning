package day3;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OrganehrmHeaders {

	@Test(priority=1)
	void OrangeHeader()
	{		given()

		.when().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")

		.then()
		  // .log().all()
		   .header("Content-Type", "text/html; charset=UTF-8")
		   .and()
           .header("Transfer-Encoding", "chunked")
           .and()
           .header("Server", "nginx")
           .log().headers();
	}
	
	  @Test(priority =2)
	  void getOrangeHeaders()
	  {
		   Response rer =given()
				   
				   .when()
				    .get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		    String headersvalue =rer.getHeader("Transfer-Encoding");
		    
		    Headers myheads =rer.getHeaders();
		    for(Header heads:myheads )
		    {
		    	System.out.println(heads.getName()+"    "+heads.getValue());
		    }
		   	
	  }

	
}
