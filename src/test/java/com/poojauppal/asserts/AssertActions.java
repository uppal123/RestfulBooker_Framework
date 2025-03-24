package com.poojauppal.asserts;

import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AssertActions {
    public void verifyResponseBody(String actual, String expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyResponseBody(int actual, int expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyStatusCode(Response response, Integer expected){
        assertEquals(response.statusCode(), expected);
    }

    public void verifyStringKey(String keyExpect, String keyActual){
        assertThat(keyExpect).isEqualTo(keyActual);
        assertThat(keyExpect).isNotNull().isNotBlank();
    }

    public void verifyStringKeyNotNull(Integer keyExpect) {
        assertThat(keyExpect).isNotNull();
    }

    public void verifyStringKeyNotNull(String keyExpect) {
        assertThat(keyExpect).isNotNull();
    }
}
