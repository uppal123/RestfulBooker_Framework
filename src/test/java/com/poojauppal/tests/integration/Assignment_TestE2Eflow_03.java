package com.poojauppal.tests.integration;

import com.poojauppal.Pojos.AllResponseJson;
import com.poojauppal.Pojos.BookingResponse;
import com.poojauppal.base.BaseTestClass;
import com.poojauppal.endpoints.APIConstents;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Assignment_TestE2Eflow_03 extends BaseTestClass {
    //Get a Booking from Get All -> Try to Delete that booking

    @Test(groups ="reg", priority = 1)
    @Description("Tc01 Verify all the booking ids are fetched")
    public void test_GetAllBookingId(ITestContext iTestContext) {
        requestSpecification.basePath(APIConstents.Create_Booking_Update_URL);
        validatableResponse = RestAssured.given(requestSpecification)
                .when().get().then().log().all().statusCode(200);

        System.out.println("validatableResponse =" + validatableResponse);
        //  store in list

        AllResponseJson allResponseJson = payloadManager.getBookingidJson(response.asString());
        assertActions.verifyStringKeyNotNull(allResponseJson.getBookingid());
        iTestContext.setAttribute("bookingid", allResponseJson.getBookingid());
    }

}
