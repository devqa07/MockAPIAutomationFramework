package com.rest.mock.api.responseParser;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

public class BookStoreBaseTest {
    JSONObject json;

    // Validate Register User Response
    public void validateRegisterUserResponse(Response response, String message) {
        json = new JSONObject(response.asString());
        Assert.assertNotNull(json.getString("userId"));
        Assert.assertEquals(json.getString("message"), message);
    }

    // Validate User Login Response
    public void validateUserLoginResponse(Response response, String message) {
        json = new JSONObject(response.asString());
        Assert.assertNotNull(json.getString("token"));
        Assert.assertEquals(json.getString("message"), message);
    }

    // Validate Search Books Response
    public void validateSearchBooksResponse(Response response, String bookId, String title, String author, double price) {
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject json = jsonArray.getJSONObject(0);
        Assert.assertEquals(json.getString("bookId"), bookId);
        Assert.assertEquals(json.getString("title"), title);
        Assert.assertEquals(json.getString("author"), author);
        Assert.assertEquals(json.getDouble("price"), price);
    }

    // Validate Add To Cart Response
    public void validateAddToCartResponse(Response response, String message) {
        json = new JSONObject(response.asString());
        Assert.assertEquals(json.getString("message"), message);
    }

    // Validate Checkout Response
    public void validateCheckoutResponse(Response response, String message) {
        json = new JSONObject(response.asString());
        Assert.assertEquals(json.getString("message"), message);
        Assert.assertNotNull(json.getString("orderId"));

    }
}