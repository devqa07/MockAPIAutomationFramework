package com.rest.mock.api.bookstore;

import com.aventstack.extentreports.Status;
import com.rest.mock.api.base.RestTestBase;
import com.rest.mock.api.responseParser.BookStoreBaseTest;
import com.rest.mock.api.utils.ConfigHelper;
import com.rest.mock.api.utils.TestConstants;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutTest extends RestTestBase {
    BookStoreBaseTest bs = new BookStoreBaseTest();
    List<String[]> data = csv.readCsvData(TestConstants.BOOKSTORE_CSV);
    String checkoutMessage=csv.getSpecificCSVData(data, 12, 1), userId=csv.getSpecificCSVData(data, 11, 1);
    Response response;

    @Test
    public void verifyCheckout_Success() {
        extentLog.info(customReport(methodName + ": This test performs Checkout order successfully"));
       response = cm.doPOST(ConfigHelper.returnPropVal("config", "cartCheckout")+userId+"/checkout");
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkSuccessResponse(response, HttpStatus.SC_OK);
        bs.validateCheckoutResponse(response,checkoutMessage);
    }

    @Test
    public void verifyCheckout_InvalidMethod() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid http method"));
        response = cm.doGET(ConfigHelper.returnPropVal("config", "cartCheckout")+userId+"/checkout");
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void verifyCheckout_InvalidEndpoint() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid endpoint Url"));
        response = cm.doPOST("/userss/12345/checkout");
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }
}

