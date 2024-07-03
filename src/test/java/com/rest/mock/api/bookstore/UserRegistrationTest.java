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

public class UserRegistrationTest extends RestTestBase {
    BookStoreBaseTest bs = new BookStoreBaseTest();
    BookStore registerUserReq = new BookStore();
    List<String[]> data = csv.readCsvData(TestConstants.BOOKSTORE_CSV);
    String body, username = FAKER.name().username(), email = FAKER.internet().emailAddress(),
            password = csv.getSpecificCSVData(data, 0, 1);
    Response response;

    @Test
    public void verifyRegisterUser_Success() {
        extentLog.info(customReport(methodName + ": This test performs new user registration"));
        body = registerUserReq.userRegistrationBody(username,email,password).toString();
        response = cm.doPOST(ConfigHelper.returnPropVal("config", "registerUser"), body);
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkSuccessResponse(response, HttpStatus.SC_OK);
        bs.validateRegisterUserResponse(response,csv.getSpecificCSVData(data, 1, 1) );
    }

    @Test
    public void verifyRegisterUser_InvalidMethod() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid http method"));
        body = registerUserReq.userRegistrationBody(username,email,password).toString();
        response = cm.doPUT(ConfigHelper.returnPropVal("config", "registerUser"), body);
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void verifyRegisterUser_InvalidEndpoint() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid endpoint Url"));
        body = registerUserReq.userRegistrationBody(username,email,password).toString();
        response = cm.doPOST("/users/registerr",body);
        extentLog.log(Status.INFO, customReport("Click here to the view the Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("Click here to the view the Response",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }
}

