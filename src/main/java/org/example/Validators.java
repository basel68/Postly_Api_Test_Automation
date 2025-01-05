package org.example;

import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class Validators {
    public static SoftAssert softAssert;

    public static void validateStatusCode(int expectedStatusCode, Response response) {
        var actualStatusCode = response.getStatusCode();
        softAssert.assertEquals(actualStatusCode, expectedStatusCode,
                "Expected status code: " + expectedStatusCode + " but found: " + actualStatusCode);
    }

    public static void validateResponseTime(int expectedResponseTime, Response response) {
        var actualResponseTime = response.getTime();
        softAssert.assertTrue(expectedResponseTime >= actualResponseTime,
                "Expected response time: " + expectedResponseTime + " but found: " + actualResponseTime);
    }

    public static void validateEqual(Object expectedValue, Object actualValue, String message) {
        softAssert.assertEquals(actualValue, expectedValue, message);
    }

    public static void validateNotEqual(Object expectedValue, Object actualValue, String message) {
        softAssert.assertNotEquals(actualValue, expectedValue, message);
    }

    public static void validateNotNull(Object expectedValue, String message) {
        softAssert.assertNotNull(expectedValue, message);
    }


    public static void assertAll() {
        softAssert.assertAll();
    }
}
