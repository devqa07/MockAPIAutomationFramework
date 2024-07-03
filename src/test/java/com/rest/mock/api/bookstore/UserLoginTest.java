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

public class UserLoginTest extends RestTestBase {
    BookStoreBaseTest bs = new BookStoreBaseTest();
    BookStore userLoginReq = new BookStore();
    List<String[]> data = csv.readCsvData(TestConstants.BOOKSTORE_CSV);
    String body, email = csv.getSpecificCSVData(data, 2, 1),
            password = csv.getSpecificCSVData(data, 0, 1);
    Response response;

    @Test
    public void verifyUserLogin_Success() {
        extentLog.info(customReport(methodName + ": This test performs user login with valid credentials"));
        body = userLoginReq.userLoginBody(email,password).toString();
        response = cm.doPOST(ConfigHelper.returnPropVal("config", "loginUser"), body);
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkSuccessResponse(response, HttpStatus.SC_OK);
        bs.validateUserLoginResponse(response,csv.getSpecificCSVData(data, 3, 1) );
    }

    @Test
    public void verifyUserLogin_InvalidMethod() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid http method"));
        body = userLoginReq.userLoginBody(email,password).toString();
        response = cm.doPUT(ConfigHelper.returnPropVal("config", "loginUser"), body);
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void verifyUserLogin_InvalidEndpoint() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid endpoint Url"));
        body = userLoginReq.userLoginBody(email,password).toString();
        response = cm.doPOST("/userrs/login", body);
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }
}