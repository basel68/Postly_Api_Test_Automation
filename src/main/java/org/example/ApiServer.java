package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiServer {
    public static Response Get(
            Map<String, Object> headers,
            Map<String, Object> pathParams,
            Map<String, Object> queryParams,
            String url
    ) {
        return RestAssured.
                given().
                headers(headers).
                pathParams(pathParams).
                queryParams(queryParams).
                get(url);
    }

    public static Response Post(
            Map<String, Object> headers,
            Map<String, Object> pathParams,
            Map<String, Object> queryParams,
            String url,
            Object body
    ) {
        return RestAssured.
                given().
                headers(headers).
                pathParams(pathParams).
                queryParams(queryParams).
                body(body).
                log().all().
                post(url);
    }

    public static Response Put(
            Map<String, Object> headers,
            Map<String, Object> pathParams,
            Map<String, Object> queryParams,
            String url,
            Object body
    ) {
        return RestAssured.
                given().
                headers(headers).
                pathParams(pathParams).
                queryParams(queryParams).
                body(body).
                put(url);
    }

    public static Response Delete(
            Map<String, Object> headers,
            Map<String, Object> pathParams,
            Map<String, Object> queryParams,
            String url
    ) {
        return RestAssured.
                given().
                headers(headers).
                pathParams(pathParams).
                queryParams(queryParams).
                delete(url);
    }
}
