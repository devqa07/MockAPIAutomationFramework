package com.rest.mock.api.utils;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommonMethods {
    String endPoint;
    Response response;

    public Response doPOST(String endpoint, String body) {
        endPoint = RestAPIUtils.getEnvToRun() + endpoint;
        response = RestAssured.given().with().contentType("application/json")
                .filter(new RequestLoggingFilter(TestConstants.REQUEST_CAPTURE)).when().log().all().body(body)
                .post(endPoint);
        System.out.println(
                "=================================================RESPONSE=======================================================================");
        response.prettyPrint();

        System.out.println(
                "================================================================================================================================");
        return response;
    }

    public Response doGET(String endpoint) {
        endPoint = RestAPIUtils.getEnvToRun() + endpoint;
        response = given().header("Content-Type", "application/json")
                .filter(new RequestLoggingFilter(TestConstants.REQUEST_CAPTURE)).when().log().all().get(endPoint);
        System.out.println(
                "================================================RESPONSE=======================================================================");
        response.prettyPrint();
        System.out.println(
                "===============================================================================================================================");
        return response;
    }

    public Response doPUT(String endpoint, String body) {
        endPoint = RestAPIUtils.getEnvToRun() + endpoint;
        response = RestAssured.given().with().contentType("application/json")
                .filter(new RequestLoggingFilter(TestConstants.REQUEST_CAPTURE)).when().log().all().body(body)
                .put(endPoint);
        System.out.println(
                "=================================================RESPONSE=======================================================================");
        response.prettyPrint();

        System.out.println(
                "================================================================================================================================");
        return response;
    }

    public Response doGET(String endpoint, Map<String, String> queryParams) {
        endPoint = RestAPIUtils.getEnvToRun() + endpoint;
        response = RestAssured.given().with().contentType("application/json").queryParams(queryParams)
                .filter(new RequestLoggingFilter(TestConstants.REQUEST_CAPTURE)).when().log().all().get(endPoint);
        System.out.println(
                "=================================================RESPONSE=======================================================================");
        response.prettyPrint();

        System.out.println(
                "================================================================================================================================");
        return response;
    }

    public Response doPOST(String endpoint) {
        endPoint = RestAPIUtils.getEnvToRun() + endpoint;
        response = RestAssured.given().with().contentType("application/json")
                .filter(new RequestLoggingFilter(TestConstants.REQUEST_CAPTURE)).when().log().all().post(endPoint);
        System.out.println(
                "=================================================RESPONSE=======================================================================");
        response.prettyPrint();

        System.out.println(
                "================================================================================================================================");
        return response;
    }

    public Response doPUT(String endpoint, Map<String, String> queryParams) {
        endPoint = RestAPIUtils.getEnvToRun() + endpoint;
        response = RestAssured.given().with().contentType("application/json").queryParams(queryParams)
                .filter(new RequestLoggingFilter(TestConstants.REQUEST_CAPTURE)).when().log().all().put(endPoint);
        System.out.println(
                "=================================================RESPONSE=======================================================================");
        response.prettyPrint();

        System.out.println(
                "================================================================================================================================");
        return response;
    }
}