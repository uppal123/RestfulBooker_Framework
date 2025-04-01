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

public class Assignment_TestE2Eflow_04 extends BaseTestClass {
    //Create Booking -> Update it -> Try to Delete
    @Test(priority = 1)
    @Description ("Tc01- verify Booking can be created")
    public void test_CreateBooking(ITestContext iTestContext) {
        requestSpecification.basePath(APIConstents.Create_Booking_Update_URL);
        response = RestAssured.given(requestSpecification).when()
                .body(payloadManager.test_CreatePayloadBookingString()).post();
        validatableResponse = response.then().log().all().statusCode(200);
        BookingResponse bookingResponse = payloadManager.bookingresponseJava(response.asString());

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Pooja");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(priority = 2)
    @Description("Tc02- Verify that created booking can be updated")
    public void test_FullUpdateBooking(ITestContext iTestContext) {
        Integer bookingid =(Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token", token);

        requestSpecification.basePath(APIConstents.Create_Booking_Update_URL+ "/" +bookingid);
        response = RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

        validatableResponse = response.then().log().all().statusCode(200);
        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isEqualTo("Pinky");
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();

    }

    @Test(priority = 3)
    @Description("Tc03- Verify booking can be deleted")
    public void test_DeleteBooking(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token", token);

        requestSpecification.basePath(APIConstents.Create_Booking_Update_URL + "/" + bookingid);
        response = RestAssured.given(requestSpecification).cookie("token", token).when().delete();
        validatableResponse = response.then().log().all().statusCode(201);


    }
}
