package com.poojauppal.tests.integration;

import com.poojauppal.Pojos.BookingResponse;
import com.poojauppal.base.BaseTestClass;
import com.poojauppal.endpoints.APIConstents;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Assignment_TestE2Eflow_02 extends BaseTestClass {
//Create Booking -> Delete it -> Verify

//Create Booking -> Update it -> Try to Delete
//Delete a Booking -> Try to Update it

    @Test(groups = "sanity", priority = 1)
    @Description("Tc01- Verify Booking is created")
    public void test_CreateBooking(ITestContext iTestContext) {
      requestSpecification.basePath(APIConstents.Create_Booking_Update_URL);
      response = RestAssured.given(requestSpecification)
              .when().body(payloadManager.test_CreatePayloadBookingString()).post();
      validatableResponse = response.then().log().all().statusCode(200);
        BookingResponse bookingResponse = payloadManager.bookingresponseJava(response.asString());

        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Pooja");
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "sanity", priority = 2)
    @Description ("Tc02 - Verify the booking get delete")
    public void test_DeleteBooking (ITestContext iTestContext) {
       Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");
       String token = getToken();
              iTestContext.getAttribute("token");
      String basePathDelete = APIConstents.Create_Booking_Update_URL + "/" +bookingId;
      requestSpecification.basePath(basePathDelete).cookie("token", token);
       validatableResponse = RestAssured.given().spec(requestSpecification)
               .when().delete().then().log().all().statusCode(201);
    }

    @Test(groups = "sanity", priority = 3)
    @Description("Tc03- Verify that deleted booking is not able to search")
    public void test_SearchBooking(ITestContext iTestContext) {
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");
       String basePathDelete = APIConstents.Create_Booking_Update_URL +"/" +bookingId;
        requestSpecification.basePath(basePathDelete);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().get().then().log().all().statusCode(404);
    }

}
