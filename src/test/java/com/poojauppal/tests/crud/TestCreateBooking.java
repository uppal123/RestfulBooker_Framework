package com.poojauppal.tests.crud;

import com.poojauppal.Pojos.BookingResponse;
import com.poojauppal.base.BaseTestClass;
import com.poojauppal.endpoints.APIConstents;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTestClass {

    @Test(groups = "reg", priority = 1)
    @Description("Tc01 Verify Booking is created")
    public void createBooking(){
        //preparation of request
        requestSpecification.basePath((APIConstents.Create_Booking_Update_URL));

        //making of request
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.test_CreatePayloadBookingString()).post();

        //verification of request
        validatableResponse = response.then().log().all().statusCode(200);
        BookingResponse bookingResponse = payloadManager.bookingresponseJava(response.asString());

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Pooja");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
    }
}
