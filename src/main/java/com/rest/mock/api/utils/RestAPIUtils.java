package com.rest.mock.api.utils;

public class RestAPIUtils {
    static String env;

    //Get the environment details to run the tests
    public static String getEnvToRun() {
        String url = null;
        if (System.getProperty("environment") != null) {
            env = System.getProperty("environment");
        } else {
            env = ConfigHelper.returnPropVal("config", "environment");
        }
        switch (env.toLowerCase()) {
            case "qa":
                url = ConfigHelper.GetBaseUrl("qaUrl");
                break;
            case "dev":
                url = ConfigHelper.GetBaseUrl("devUrl");
                break;
            default:
                System.out.println("Invalid Environment Value");
        }
        return url;
    }
}