package com.rest.mock.api.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//This class is for passing query parameters dynamically based on the request

@Getter
public class QueryParamsUtil {
    Map<String, List<String>> queryParamsList = new HashMap<String, List<String>>();
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    public QueryParamsUtil setQueryParams(String key, String value) {
        queryParams.put(key, value);
        return this;
    }

    public QueryParamsUtil setQueryParamsList(String key, List<String> value) {
        queryParamsList.put(key, value);
        return this;
    }

    public void clearQueryParams() {
        queryParams.clear();
        queryParamsList.clear();
    }
}