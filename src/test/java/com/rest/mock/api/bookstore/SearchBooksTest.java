package com.rest.mock.api.bookstore;

import com.aventstack.extentreports.Status;
import com.rest.mock.api.base.RestTestBase;
import com.rest.mock.api.responseParser.BookStoreBaseTest;
import com.rest.mock.api.utils.ConfigHelper;
import com.rest.mock.api.utils.QueryParamsUtil;
import com.rest.mock.api.utils.TestConstants;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

public class SearchBooksTest extends RestTestBase {
    BookStoreBaseTest bs = new BookStoreBaseTest();
    List<String[]> data = csv.readCsvData(TestConstants.BOOKSTORE_CSV);
    QueryParamsUtil queryParams = new QueryParamsUtil();
    Response response;

    @Test
    public void verifySearchBooks_Success() {
        extentLog.info(customReport(methodName + ": This test Performs Search Books with Query Params"));
        queryParams.setQueryParams(TestConstants.SEARCH,csv.getSpecificCSVData(data, 4, 1));
        response = cm.doGET(ConfigHelper.returnPropVal("config", "searchBooks"),queryParams.getQueryParams());
        extentLog.log(Status.INFO, customReport("Click here to view Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("HITS Endpoint to Search Books with Query Params-- click to see the Response ",
                response.asString()));
        //Validate status code is 200
        checkSuccessResponse(response, HttpStatus.SC_OK);

        //Validate the search books response
        bs.validateSearchBooksResponse(response,csv.getSpecificCSVData(data, 5, 1),csv.getSpecificCSVData(data, 6, 1),
                csv.getSpecificCSVData(data, 7, 1),Double.parseDouble(csv.getSpecificCSVData(data, 8, 1)));
    }

    @Test
    public void verifySearchBooks_InvalidMethod() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid http method"));
        queryParams.setQueryParams(TestConstants.SEARCH,csv.getSpecificCSVData(data, 4, 1));
        response = cm.doPUT(ConfigHelper.returnPropVal("config", "searchBooks"),queryParams.getQueryParams());
        extentLog.log(Status.INFO, customReport("Click here to view Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("HITS Endpoint to checks the error response with invalid http method-- click to see the Response ",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void verifySearchBooks_InvalidEndpoint() {
        extentLog.info(customReport(methodName + ": This test checks the error response with invalid endpoint Url"));
        queryParams.setQueryParams(TestConstants.SEARCH,csv.getSpecificCSVData(data, 4, 1));
        response = cm.doGET("/bookks?search=query");
        extentLog.log(Status.INFO, customReport("Click here to view Request", requestWriter.toString()));
        extentLog.log(Status.INFO, customReport("HITS Endpoint to checks the error response with invalid endpoint Url-- click to see the Response ",
                response.asString()));
        checkErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }
}