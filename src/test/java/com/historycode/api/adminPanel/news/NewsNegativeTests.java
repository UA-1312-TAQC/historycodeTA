package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;

public class NewsNegativePostTests extends ApiTestRunner {
    NewsClient client;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
    }

    @Issue("198")
    @Test
    @Description("Verify that the news cannot be created with character limit exceeded of 'title' field using POST method")
    public void testVerifyCreationWithExceededTitleLength() {
        String title = generateString(101);
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().toString();
        NewsRequestBody newsRequestBody = setUpTestData(title, text, imageId, url, creationDate);

        Response response = client.create(newsRequestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(response.body().asPrettyString().contains("'Title': 'Max Length is 100'"));

        softAssert.assertAll();
    }



}
