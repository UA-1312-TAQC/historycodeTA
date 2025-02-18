package com.historycode.api.clients;

import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.adminPanel.news.NewsUpdateRequestBody;
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
  
    public Response create(NewsRequestBody news) {
        return preparedRequest()
                .when()
                .body(news)
                .post(resourceUrl + "/Create");
    }

    public Response update(NewsUpdateRequestBody news) {
        return preparedRequest()
                .when()
                .body(news)
                .put(resourceUrl + "/Update");
    }

    public Response delete(int newsId) {
        return preparedRequest()
                .when()
                .delete(resourceUrl + "/Delete/" + newsId);
    }

    public Response updateNews(NewsRequestBody news) {
        return preparedRequest()
                .when()
                .body(news)
                .put(resourceUrl + "/Update");
    }
}
