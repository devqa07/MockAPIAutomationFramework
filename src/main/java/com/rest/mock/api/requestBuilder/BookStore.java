package com.rest.mock.api.requestBuilder;

import com.rest.mock.api.base.RestTestBase;
import org.json.JSONObject;

public class BookStore extends RestTestBase {

    public JSONObject userRegistrationBody(String username, String email, String password) {
        JSONObject register = new JSONObject();
        register.put("username", username);
        register.put("email", email);
        register.put("password", password);
        return register;
    }

    public JSONObject userLoginBody(String email, String password) {
        JSONObject login = new JSONObject();
        login.put("email", email);
        login.put("password", password);
        return login;
    }

    public JSONObject addToCartBody(String bookId, String quantity) {
        JSONObject cart = new JSONObject();
        cart.put("bookId", bookId);
        cart.put("quantity", quantity);
        return cart;
    }
}