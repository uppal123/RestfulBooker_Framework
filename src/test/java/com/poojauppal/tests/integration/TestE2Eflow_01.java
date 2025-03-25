package com.poojauppal.tests.integration;

import com.poojauppal.Pojos.Booking;
import com.poojauppal.Pojos.BookingResponse;
import com.poojauppal.base.BaseTestClass;
import com.poojauppal.endpoints.APIConstents;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestE2Eflow_01 extends BaseTestClass {
//  Test E2E Scenario 1
    //  1. Create a Booking -> bookingID
    // 2. Create Token -> token
    // 3. Verify that the Create Booking is working - GET Request to bookingID
    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request
    // 5. Delete the Booking - Need to get the token, bookingID from above request

    @Description("TC01 - Step 1 Verify that Booking is created")
    @Test(groups = "qa", priority = 1)
    public void test_CreateBooking(ITestContext iTestContext) {
        requestSpecification.basePath((APIConstents.Create_Booking_Update_URL));

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.test_CreatePayloadBookingString()).post();

        validatableResponse = response.then().log().all().statusCode(200);
        BookingResponse bookingResponse = payloadManager.bookingresponseJava(response.asString());

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Pooja");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());

    }

    @Description("Tc02- Step2 Verify that Booking can be Searched")
    @Test(groups = "qa", priority = 2)
    public void test_SearchBooking(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        // GET Request - to verify that the firstname after creation is James
        String basePathGet = APIConstents.Create_Booking_Update_URL+"/"+bookingid;
        System.out.println(basePathGet);
        requestSpecification.basePath(basePathGet);
        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse = response.then().log().all().statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("Pooja");
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();

    }

    @Description("Tc03- Step 3 Verify Booking is updating")
    @Test(groups = "qa", priority = 3)
    public void test_UpdateBooking(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
      String token = getToken();
      iTestContext.setAttribute("token", token);

      String basepathPutPatch = APIConstents.Create_Booking_Update_URL + "/" +bookingid;
        System.out.println(basepathPutPatch);

        requestSpecification.basePath(basepathPutPatch);
        response = RestAssured.given(requestSpecification)
                .cookie("token", token).when().body(payloadManager.fullUpdatePayloadAsString())
                .put();
        validatableResponse = response.then().log().all().statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isEqualTo("Pinky");
        assertThat(booking.getFirstname()).isNotBlank().isNotNull();
        assertThat(booking.getLastname()).isEqualTo("Uppal");
    }

    @Description("Tc04- Step4 Verify that Booking can be deleted")
    @Test(groups = "qa", priority = 4)
    public void test_DeleteBooking(ITestContext iTestContext) {
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDelete = APIConstents.Create_Booking_Update_URL +"/" +bookingid;

        requestSpecification.basePath(basePathDelete).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }
}
