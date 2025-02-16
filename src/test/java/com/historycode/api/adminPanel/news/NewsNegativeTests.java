package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.adminPanel.news.NewsResponse;
import com.historycode.api.testRunners.ApiTestRunner;
import com.historycode.utils.StringDataCreator;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class NewsNegativeTests extends ApiTestRunner {
    NewsClient client;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
    }

    private NewsRequestBody setUpTestData(String title, String text, int imageId, String url, String creationDate) {
        NewsRequestBody requestBody = new NewsRequestBody();
        requestBody.setTitle(title);
        requestBody.setText(text);
        requestBody.setImageId(imageId);
        requestBody.setUrl(url);
        requestBody.setCreationDate(creationDate);
        return requestBody;
    }

    @Issue("198")
    @Test
    @Description("Verify that the news cannot be created with character limit exceeded of 'title' field using POST method")
    public void testVerifyCreationWithExceededTitleLength() {
        String title = StringDataCreator.generateRandomAlphanumericString(101);
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().toString();
        NewsRequestBody requestBody = setUpTestData(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(response.path("Title"), "Max Length is 100");
        softAssert.assertAll();
    }

    @Issue("200")
    @Test
    @Description("Verify that the news cannot be created with character limit exceeded of 'url' field using POST method")
    public void testVerifyCreationWithExceededUrlLength() {
        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = StringDataCreator.generateRandomAlphanumericString(201).toLowerCase();
        String creationDate = Instant.now().toString();
        NewsRequestBody requestBody = setUpTestData(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(response.path("URL"), "Max Length is 200");
        softAssert.assertAll();
    }

    @Issue("201")
    @Test
    @Description("Verify that the news cannot be created with capital letters in 'url' field using POST method")
    public void testVerifyCreationWithCapitalLettersInUrl() {
        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "News-item";
        String creationDate = Instant.now().toString();
        NewsRequestBody requestBody = setUpTestData(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(response.body().asPrettyString().contains("Url Is Invalid"));
        softAssert.assertAll();
    }

    @Issue("202")
    @Test
    @Description("Verify that the news cannot be created with cyrillic letters in 'url' field using POST method")
    public void testVerifyCreationWithCyrillicLettersInUrl() {
        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "новина";
        String creationDate = Instant.now().toString();
        NewsRequestBody requestBody = setUpTestData(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(response.body().asPrettyString().contains("Url Is Invalid"));
        softAssert.assertAll();
    }

    //TODO: DP
    @Issue("203")
    @Test
    @Description("Verify that the news cannot be created with special symbols ($,@,%,#) in 'url' field using POST method")
    public void testVerifyCreationWithSpecialSymbolsInUrl() {
        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "News-Item#";
        String creationDate = Instant.now().toString();
        NewsRequestBody requestBody = setUpTestData(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(response.body().asPrettyString().contains("Url Is Invalid"));
        softAssert.assertAll();
    }

    @Issue("205")
    @Test
    @Description("Verify that the news cannot be created with character limit exceeded of 'text' field using POST method")
    public void testVerifyCreationWithExceededLengthInText() {
        String title = "Test News Item";
        String text = StringDataCreator.generateRandomAlphanumericString(15001);
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().toString();
        NewsRequestBody requestBody = setUpTestData(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(response.path("Text"), "Max Length is 15000");
        softAssert.assertAll();
    }

    @Issue("207")
    @Test
    @Description("Verify that the news is not created with the past date chosen in the 'creationDate' field using POST method")
    public void testVerifyCreationWithPastDateInCreationDate() {
        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().minus(7, ChronoUnit.DAYS).toString();
        NewsRequestBody requestBody = setUpTestData(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(response.body().asPrettyString().contains("The news cannot be published with a past date"));
        softAssert.assertAll();
    }

    @Issue("209")
    @Test
    @Description("Verify that the news cannot be deleted without authorization by 'id' using DELETE method")
    public void testVerifyDeletionWithoutAuthorizationById() {
        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().toString();
        NewsRequestBody requestBody = setUpTestData(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);
        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        client.setToken(null);
        Response deleteResponse = client.delete(newsResponse.getId());

        Assert.assertEquals(deleteResponse.getStatusCode(), 401);
    }

}
