package com.rest.mock.api.bookstore;

import com.aventstack.extentreports.Status;
import com.rest.mock.api.base.RestTestBase;
import com.rest.mock.api.requestBuilder.BookStore;
import com.rest.mock.api.responseParser.BookStoreBaseTest;
import com.rest.mock.api.utils.ConfigHelper;
import com.rest.mock.api.utils.TestConstants;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

public class AddToCartTest extends RestTestBase {
    BookStoreBaseTest bs = new BookStoreBaseTest();
    BookStore addToCart = new BookStore();
    List<String[]> data = csv.readCsvData(TestConstants.BOOKSTORE_CSV);
    String body, bookId = csv.getSpecificCSVData(data, 5, 1),
            quantity = csv.getSpecificCSVData(data, 9, 1);
    String userId=csv.getSpecificCSVData(data, 11, 1);
    Response response;

    @Test
    public void verifyAddToCart_Success() {
        extentLog.info(customReport(methodName + ": This test performs Add To Cart with userId"));
        body = addToCart.addToCartBody(bookId,quantity).toString();
        response = cm.doPOST(ConfigHelper.returnPropVal("config", "cartCheckout")+userId+"/cart", body);
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkSuccessResponse(response, HttpStatus.SC_OK);
        bs.validateAddToCartResponse(response,csv.getSpecificCSVData(data, 10, 1) );
    }

    @Test
    public void verifyAddToCart_InvalidMethod() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid http method"));
        body = addToCart.addToCartBody(bookId,quantity).toString();
        response = cm.doPUT(ConfigHelper.returnPropVal("config", "cartCheckout")+userId+"/cart", body);
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void verifyAddToCart_InvalidEndpoint() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid endpoint Url"));
        body = addToCart.addToCartBody(bookId,quantity).toString();
        response = cm.doPOST("/users/12345/cartt", body);
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }
}

