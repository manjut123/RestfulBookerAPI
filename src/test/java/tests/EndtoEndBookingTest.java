package tests;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.Crendentials;
import pojo.PartialUpdate;
import requests.RequestFactory;
import specs.RequestResponseSpec;
import utils.BookingDataGenerator;

@Epic("Booking API")
@Feature("Verify End to End flow of Booking service ")
public class EndtoEndBookingTest extends TestBase {

	public static int bookingId;
	public String firstname;
	RequestFactory reqFactory = new RequestFactory();

	@Story("Create new Booking")
	@Description("Validate API for create booking")
	@Test(priority = 1)
	public void createNewbooking() throws JsonProcessingException {

		Response response = reqFactory.createNewStudent().then().spec(RequestResponseSpec.getResponseSpec())
				.statusCode(200).extract().response();

		bookingId = response.jsonPath().get("bookingid");
		firstname = response.jsonPath().getString("booking.firstname");
		System.out.println("created first name " + firstname + " id " + bookingId);
		Allure.addAttachment("Response Body", response.asString());
	}

	@Description("Validate new booking details by using ID search")
	@Test(priority = 2)
	public void verifynewBookingByid() {
		Response res = reqFactory.getBookingbyid(bookingId).then().spec(RequestResponseSpec.getResponseSpec())
				.statusCode(200).extract().response();
       
		String getname=res.jsonPath().getString("firstname");
		 Assert.assertEquals(firstname,getname);

	}

	@Description("Validate Update booking results ")
	@Test(priority = 3)
	public void updateBooking() throws JsonProcessingException {

		Response res = reqFactory.updateBooking(token, bookingId).then().spec(RequestResponseSpec.getResponseSpec())
				.statusCode(200).extract().response();

		firstname = res.jsonPath().getString("firstname");		
		String getname = reqFactory.getBookingbyid(bookingId).then().extract().response().jsonPath().getString("firstname");
		Assert.assertEquals(getname,getname);
	}

	@Description("Validate partial update of booking ")
	@Test(priority = 4)
	public void partialUpdate() throws JsonProcessingException {
		Response res = reqFactory.updatePartial(token, bookingId).then().spec(RequestResponseSpec.getResponseSpec())
				.statusCode(200).extract().response();
		String putname = res.jsonPath().getString("firstname");
		String getname = reqFactory.getBookingbyid(bookingId).then().extract().response().jsonPath()
				.getString("firstname");
		Assert.assertEquals(getname, putname);
	}

	@Description("Validate that booking deleted")
	@Test(priority = 5)
	public void deleteBooking() {

		Response deleteResponse = reqFactory.deleteBookingbyid(bookingId, token);
		Assert.assertEquals(deleteResponse.statusCode(), 201);
		Response getResponse = reqFactory.getBookingbyid(bookingId);
		Assert.assertEquals(getResponse.statusCode(), 404);

	}
}