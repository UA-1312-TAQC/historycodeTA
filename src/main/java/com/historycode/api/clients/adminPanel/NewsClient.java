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
                .get(resourceUrl + "/GetAll");
    }

    public Response getAll(int page, int amount) {
        return preparedRequest()
                .when()
                .queryParam("page", page)
                .queryParam("amount", amount)
                .get(resourceUrl + "/GetAll");
    }

    public Response getById(int id) {
        return preparedRequest()
                .when()
                .get(resourceUrl + "/GetById/" + id);
    }

    public Response getByUrl(String url) {
        return preparedRequest()
                .when()
                .get(resourceUrl + "/GetByUrl/" + java.net.URLEncoder.encode(url, java.nio.charset.StandardCharsets.UTF_8));
    }
  
    public Response post(NewsRequestBody news) {
        return preparedRequest()
                .when()
                .body(news)
                .post(resourceUrl + "/Create");
    }

    public Response delete(int newsId) {
        return preparedRequest()
                .when()
                .delete(resourceUrl + "/Delete/" + newsId);
    }
}
