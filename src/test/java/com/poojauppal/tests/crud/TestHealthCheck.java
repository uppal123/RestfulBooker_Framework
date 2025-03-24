package com.poojauppal.tests.crud;

import com.poojauppal.base.BaseTestClass;
import com.poojauppal.endpoints.APIConstents;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTestClass {

    @Test(groups = "reg", priority = 3)
    @Description("TC03 Verify the Health check of Booking")
    public void healthCheck_Get() {
        requestSpecification.basePath(APIConstents.Ping_URL);
        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse = response.then().log().all().statusCode(201);

    }
}
