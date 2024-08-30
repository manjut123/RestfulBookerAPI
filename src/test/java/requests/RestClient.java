package requests;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import specs.RequestResponseSpec;
import tests.TestBase;

public class RestClient extends TestBase{
	
	public Response doGetRequest(String getpathurl)
	{
		return RestAssured.given().spec(RequestResponseSpec.getRquestspec()).get(getpathurl);
	}

	public Response doPutRequest(String putPathurl,Object body,Map<String,Object> setHeaders,String token)
	{
		return RestAssured.given().spec(RequestResponseSpec.getRquestspec())
				.headers(setHeaders)
				.when().body(body).put(putPathurl);
	}
	
	public Response doPostRequest(String postPathurl,Object body)
	{
		return RestAssured.given().spec(RequestResponseSpec.getRquestspec())
				 .contentType("application/json")
				.when().body(body).post(postPathurl);
	}
	
	public Response doPatchRequest(String patchPathurl,Object body,Map<String,Object> setHeaders,String token)
	{
		return RestAssured.given().spec(RequestResponseSpec.getRquestspec())
				.headers(setHeaders)
				.when().body(body).patch(patchPathurl);
	}
	
	public Response doDeleteRequest(String deleteUrl,Map<String,Object> setHeaders)
	{	
		return RestAssured.given()
				.headers(setHeaders)
				.when().delete(deleteUrl);
	}
	
	
}
