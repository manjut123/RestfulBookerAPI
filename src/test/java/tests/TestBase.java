package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Crendentials;
import utils.BookingDataGenerator;
import utils.PropertyReader;

import java.io.IOException;

import org.testng.annotations.BeforeTest;

public class TestBase {

	public static String token;

	@BeforeTest
	public void setup() throws IOException {

		String baseurl = PropertyReader.readproperty("url");
		RestAssured.baseURI = baseurl;

		Crendentials creadentials=BookingDataGenerator.readCredentials();
		
		Response res = RestAssured.given().header("Content-Type", "application/json").body(creadentials).post("/auth");
		token = res.path("token");
		System.out.println("new token " + token);

	}
}
