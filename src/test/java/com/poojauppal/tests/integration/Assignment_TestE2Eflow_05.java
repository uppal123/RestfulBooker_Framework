package com.poojauppal.tests.integration;

import com.poojauppal.Pojos.Booking;
import com.poojauppal.Pojos.BookingResponse;
import com.poojauppal.base.BaseTestClass;
import com.poojauppal.endpoints.APIConstents;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Assignment_TestE2Eflow_05 extends BaseTestClass {
    //Delete a Booking -> Try to Update it
    @Test(priority = 1)
    @Description("Tc01 - Verify Booking is created ")
    public void test_createBooking(ITestContext iTestContext) {
    requestSpecification.basePath(APIConstents.Create_Booking_Update_URL);
    response = RestAssured.given(requestSpecification).when()
            .body(payloadManager.test_CreatePayloadBookingString()).post();
    validatableResponse= response.then().log().all().statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingresponseJava(response.asString());

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Pooja");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(priority = 2)
    @Description("Tc02 - Verify Booking can be deleted ")
    public void test_deleteBooking(ITestContext iTestContext) {

    Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
    String token = getToken();
    iTestContext.setAttribute("token", token);

    requestSpecification.basePath(APIConstents.Create_Booking_Update_URL+"/"+bookingid);
    response = RestAssured.given(requestSpecification).cookie("token", token).when().delete();
    validatableResponse = response.then().log().all().statusCode(201);

    }

    @Test(priority = 3)
    @Description("Tc03 - Verify Booking can be not be searched for update after delete ")
    public void test_UpdateBooking(ITestContext iTestContext) {

    Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
    String token = (String) iTestContext.getAttribute("token");
    iTestContext.setAttribute("token", token);

    requestSpecification.basePath(APIConstents.Create_Booking_Update_URL+"/"+bookingid);
    response =RestAssured.given(requestSpecification).cookie("token", token).when()
            .body(payloadManager.fullUpdatePayloadAsString()).put();
    validatableResponse = response.then().log().all().statusCode(405);


    }
}
