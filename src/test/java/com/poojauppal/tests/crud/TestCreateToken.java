package com.poojauppal.tests.crud;

import com.poojauppal.base.BaseTestClass;
import com.poojauppal.endpoints.APIConstents;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTestClass {

    @Test(groups = "req", priority = 2)
    @Description("TC02 Verify Token is generated")
    public void CreateToken_Get() {

        requestSpecification.basePath(APIConstents.Auth_URL);

        response =RestAssured.given(requestSpecification).when()
                .body(payloadManager.setauthPayload()).post();

        validatableResponse = response.then().log().all().statusCode(200);

        String token = payloadManager.getTokenFromJson(response.asString());
        assertActions.verifyStringKeyNotNull(token);
    }
}
