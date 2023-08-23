package com.petstore.utils;

import io.restassured.response.Response;

/**
 * Created by sekarayukarindra
 */
public class DataUtils {

    private static Response response;

    private DataUtils(){}

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        DataUtils.response = response;
    }
}
