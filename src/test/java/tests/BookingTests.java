package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import pojo.Booking;
import specs.RequestResponseSpec;
import utils.BookingDataGenerator;


import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Epic("Booking API")
@Feature("Verify Create Booking with multiple data")
public class BookingTests extends TestBase {
    private Booking booking;
    private String bookingId;
   
    public BookingTests(Booking booking) {
        this.booking = booking;
    }

    @Test(priority=0)
    @Story("Create Booking")
      public void createBooking() throws JsonProcessingException {
    	ObjectMapper obj=new ObjectMapper();
        String requestBody = obj.writeValueAsString(booking);

        Response response = RestAssured.given()
            .contentType("application/json")
            .spec(RequestResponseSpec.getRquestspec())
            .body(requestBody)
        .when()
            .post("/booking")
        .then()
          .spec(RequestResponseSpec.getResponseSpec())
            .statusCode(200)          
            .extract().response();

        bookingId = response.jsonPath().getString("bookingid");
        Allure.addAttachment("Response Body", response.asString());
    } 

    @Test(dependsOnMethods={"createBooking"})
    @Description("Verify the details of created booking using Id")
    public void verifyBookingByid() {
    	RestAssured.given()
    	.spec(RequestResponseSpec.getRquestspec())
            .basePath("/booking/{id}")
            .pathParam("id",bookingId)
        .when()
            .get()
        .then()
            .spec(RequestResponseSpec.getResponseSpec())
            .statusCode(200)
            .body("firstname", equalTo(booking.getFirstname()))
            .body("lastname", equalTo(booking.getLastname()))
            .body("totalprice", equalTo(booking.getTotalprice()))
            .body("depositpaid", equalTo(booking.isDepositpaid()));
    }



	@Factory
    public static Object[] factoryMethod() {
        return new Object[] {
            new BookingTests(BookingDataGenerator.generateBooking()),
            new BookingTests(BookingDataGenerator.generateBooking()),
            new BookingTests(BookingDataGenerator.generateBooking())
            
        };
    }
}


