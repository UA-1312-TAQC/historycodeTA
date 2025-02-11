package com.historycode.api.clients;

import io.restassured.response.Response;

public class NewsClient extends BaseClient {

    public NewsClient(String baseUrl) {
        super(baseUrl);
    }

    public Response getAllNews() {
        return preparedRequest()
                .when()
                .get("/News/GetAll")
                .then()
                .extract()
                .response();
    }
}


