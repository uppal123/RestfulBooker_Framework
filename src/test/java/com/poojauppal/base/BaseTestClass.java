package com.poojauppal.base;

import com.poojauppal.asserts.AssertActions;
import com.poojauppal.endpoints.APIConstents;
import com.poojauppal.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTestClass {
    //commonTOAll- URLs, content type- json
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public PayloadManager payloadManager;
    public AssertActions assertActions;
    public JsonPath jsonPath;

    @BeforeTest
    public void setUp() {
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
//        requestSpecification = RestAssured.given().baseUri(APIConstents.BaseUrl);
//        requestSpecification.body(payloadManager);
//        requestSpecification.contentType(ContentType.JSON).log().all();

        //both are same can use anyone.
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstents.BaseUrl)
                .addHeader("Content-type", "application/json")
                .build().log().all();
    }

    public String getToken() {
        requestSpecification = RestAssured.given()
                .baseUri(APIConstents.BaseUrl)
                .basePath(APIConstents.Auth_URL);
        //select the payload
        String payload = payloadManager.setauthPayload();
        //get the token
        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload).when().post();

        //String Extraction
        String token = payloadManager.getTokenFromJson(response.asString());
        return token;
    }
}
