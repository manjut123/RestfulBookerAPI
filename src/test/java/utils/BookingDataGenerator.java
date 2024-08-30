package utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import pojo.Booking;
import pojo.BookingDates;
import pojo.Crendentials;
import pojo.PartialUpdate;

public class BookingDataGenerator {
    private static final Faker faker = new Faker();
    
      public static Booking generateBooking() {
        Booking booking = new Booking();
        BookingDates dates=new BookingDates();

        dates.setCheckin(faker.date().past(10, TimeUnit.DAYS).toString());
        dates.setCheckout(faker.date().future(10, TimeUnit.DAYS).toString());

        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.number().numberBetween(100, 500));
        booking.setDepositpaid(faker.bool().bool());
        booking.setBookingdates(dates);
        booking.setAdditionalneeds(faker.lorem().sentence());

        return booking;
    }
      
    public static PartialUpdate updatePartial()
    {
    	PartialUpdate partial=new PartialUpdate();
    	partial.setFirstname(faker.name().firstName());
    	partial.setLastname(faker.name().lastName());
    	return partial;
    	
    }
    
    public static Crendentials readCredentials() throws IOException
    {
    	Crendentials cred=new Crendentials();
    	
    	String username=PropertyReader.readproperty("username");
    	String password=PropertyReader.readproperty("password");
    	
    	cred.setUsername(username);
    	cred.setPassword(password);
    	return cred;
    	
    }
}
