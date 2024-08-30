package specs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import groovy.transform.Synchronized;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tests.TestBase;

public class RequestResponseSpec extends TestBase{
	
   public static synchronized RequestSpecification getRquestspec()
   {
	   RequestSpecification reqspec;
	   RequestSpecBuilder requestBuilder=new RequestSpecBuilder();
	   
	   requestBuilder.addFilter(new AllureRestAssured());
	   
	   reqspec=requestBuilder.build();
	   
	   return reqspec;   
	   
   }
   
   public static synchronized ResponseSpecification getResponseSpec()
   {
	      ResponseSpecBuilder responseSpec;
		ResponseSpecification responseSpecification;
		
		responseSpec = new ResponseSpecBuilder();
		responseSpec.expectHeader("Content-Type","application/json; charset=utf-8");
		responseSpec.expectHeader("Server","Cowboy");
		//responseSpec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		
		responseSpecification = responseSpec.build();
		return responseSpecification;
	 
   }
}
