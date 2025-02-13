package com.historycode.api.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StreetcodeClient extends BaseClient {
    protected String resourceUrl = "/Streetcode";
    public StreetcodeClient(String baseUrl) {
        super(baseUrl);
    }

    public StreetcodeClient(String baseUrl, ContentType contentType) {
        super(baseUrl, contentType);
    }

    public StreetcodeClient(String baseUrl, String contentType) {
        super(baseUrl, contentType);
    }
    public Response getAll() {
        return preparedRequest()
                .when()
                .get(resourceUrl+"/GetAll");
    }
    public Response getAll(int page, int amount) {
        return preparedRequest()
                .when()
                .queryParam("page", page)
                .queryParam("amount", amount)
                .get(resourceUrl + "/GetAll");
    }

    public Response getAll(String filter) {
        return preparedRequest()
                .when()
                .queryParam("Filter", filter)
                .get(resourceUrl + "/GetAll");
    }
}
