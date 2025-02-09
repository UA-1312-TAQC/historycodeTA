package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.adminPanel.NewsClient;
import com.historycode.api.models.adminPanel.news.GetAllNewsResponse;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class NewsTest extends ApiTestRunner {
    NewsClient client;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
    }

    @Test
    public void testGetAllNews() {
        Response response = client.getAll();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        GetAllNewsResponse getAllNewsResponse = response.body().as(GetAllNewsResponse.class);
        softAssert.assertFalse(getAllNewsResponse.getNews() == null || getAllNewsResponse.getNews().isEmpty(), "The news is not present in the response body");
        softAssert.assertAll();
    }

    @Test
    @Issue("193")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'text' is empty")
    public void testPostNews() {
        NewsRequestBody news = new NewsRequestBody();
        client.setToken(testValueProvider.getAccessToken());
        int randomLengthId = 1000;

        news.setTitle("TestNews" + System.currentTimeMillis());
        news.setText(null);
        news.setImageId(randomLengthId + new Random().nextInt(randomLengthId));
        news.setUrl(news.getTitle().toLowerCase());
        news.setCreationDate(
                DateTimeFormatter.ISO_INSTANT
                        .withZone(ZoneOffset.UTC)
                        .format(Instant.now())
        );

        //todo: I need to provide a unique and existing id for the image, where can I get one?
        Response response = client.post(news);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

}
