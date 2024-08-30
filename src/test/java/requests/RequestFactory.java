package requests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.Crendentials;
import pojo.PartialUpdate;
import tests.TestBase;
import utils.BookingDataGenerator;

public class RequestFactory extends TestBase {
RestClient restClient = new RestClient();
	
	public Response getBookingbyid(int id) {
		
		Response response = restClient.doGetRequest("/booking/"+id);
		return response;
		 
	}
	
	
	public Response createNewStudent() {
		
		Booking booking=BookingDataGenerator.generateBooking();
		Response res=restClient.doPostRequest("/booking", booking);
		return res;
		
	}
	
	public Response updateBooking(String token,int id) {
		
		String updateUrl="/booking/"+id;
		Booking booking=BookingDataGenerator.generateBooking();
		Map<String,Object>setheader=setHeaders(token);
	Response res=restClient.doPutRequest(updateUrl, booking, setheader, token);
	return res;
	}
	
	public Response updatePartial(String token,int id)
	{
		String updateUrl="/booking/"+id;
		PartialUpdate booking=BookingDataGenerator.updatePartial();
		Map<String,Object>setheader=setHeaders(token);
		Response res=restClient.doPatchRequest(updateUrl,booking,setheader,token);
		return res;
	}
	
	public Map<String,Object> setHeaders(String token)
	{
		 HashMap<String,Object> setHeaders=new HashMap<>();
		 setHeaders.put("Content-Type","application/json");
		 setHeaders.put("Accept","application/json");
		 setHeaders.put("Cookie","token="+token);
		 return setHeaders;
	}
	
	
	
	//@Step("Getting booking information from the database")
	public Response deleteBookingbyid(int id,String token) {
		
		String pathdelete="/booking/"+id;
		Map<String,Object> headers=setHeaders(token);
		headers.remove("Accept");
		Response response = restClient.doDeleteRequest(pathdelete,headers);
		return response;
		 
	}
	
	
}

