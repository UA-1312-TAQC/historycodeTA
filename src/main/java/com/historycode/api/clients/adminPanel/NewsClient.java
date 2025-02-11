package com.historycode.api.clients.adminPanel;

import com.historycode.api.clients.BaseClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class NewsClient extends BaseClient {

    protected String resourceUrl = "/News";

    public NewsClient(String baseUrl) {
        super(baseUrl);
    }

    public NewsClient(String baseUrl, ContentType contentType) {
        super(baseUrl, contentType);
    }

    public NewsClient(String baseUrl, String contentType) {
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

    public Response post(NewsRequestBody news) {
        return preparedRequest()
                .when()
                .body(news)
                .post(resourceUrl + "/Create");
    }
}
