package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.adminPanel.NewsClient;
import com.historycode.api.models.adminPanel.news.NewsErrorResponse;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.adminPanel.news.NewsResponse;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class NewsTests extends ApiTestRunner {
    NewsClient client;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
    }

    @Issue("197")
    @Test
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'title' field using POST method")
    public void testVerifyCreationWithMaxTitleLength() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = generateString(100);
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        softAssert.assertEquals(newsResponse.getTitle(), title);
        softAssert.assertEquals(newsResponse.getText(), text);
        softAssert.assertEquals(newsResponse.getImageId(), imageId);
        softAssert.assertEquals(newsResponse.getUrl(), url);
        softAssert.assertEquals(newsResponse.getCreationDate(), creationDate);

        softAssert.assertAll();
    }

    @Issue("198")
    @Test
    @Description("Verify that the news cannot be created with character limit exceeded of 'title' field using POST method")
    public void testVerifyCreationWithExceededTitleLength() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = generateString(101);
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);

        NewsErrorResponse newsResponse = response.body().as(NewsErrorResponse.class);
        softAssert.assertEquals(newsResponse.getMessage(), "'Title': 'Max Length is 100'");

        softAssert.assertAll();
    }

    @Issue("199")
    @Test
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'url' field using POST method")
    public void testVerifyCreationWithMaxUrlLength() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = generateString(200).toLowerCase();
        String creationDate = Instant.now().toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        softAssert.assertEquals(newsResponse.getTitle(), title);
        softAssert.assertEquals(newsResponse.getText(), text);
        softAssert.assertEquals(newsResponse.getImageId(), imageId);
        softAssert.assertEquals(newsResponse.getUrl(), url);
        softAssert.assertEquals(newsResponse.getCreationDate(), creationDate);

        softAssert.assertAll();
    }

    @Issue("200")
    @Test
    @Description("Verify that the news cannot be created with character limit exceeded of 'url' field using POST method")
    public void testVerifyCreationWithExceededUrlLength() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = generateString(201).toLowerCase();;
        String creationDate = Instant.now().toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);

        NewsErrorResponse newsResponse = response.body().as(NewsErrorResponse.class);
        softAssert.assertEquals(newsResponse.getMessage(), "'URL ': 'Max Length is 200'");

        softAssert.assertAll();
    }

    @Issue("201")
    @Test
    @Description("Verify that the news cannot be created with capital letters in 'url' field using POST method")
    public void testVerifyCreationWithCapitalLettersInUrl() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "News-item";
        String creationDate = Instant.now().toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);

        NewsErrorResponse newsResponse = response.body().as(NewsErrorResponse.class);
        softAssert.assertEquals(newsResponse.getMessage(), "Url Is Invalid");

        softAssert.assertAll();
    }

    @Issue("202")
    @Test
    @Description("Verify that the news cannot be created with cyrillic letters in 'url' field using POST method")
    public void testVerifyCreationWithCyrillicLettersInUrl() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "новина";
        String creationDate = Instant.now().toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);

        NewsErrorResponse newsResponse = response.body().as(NewsErrorResponse.class);
        softAssert.assertEquals(newsResponse.getMessage(), "Url Is Invalid");

        softAssert.assertAll();
    }

    @Issue("203")
    @Test
    @Description("Verify that the news cannot be created with special symbols ($,@,%,#) in 'url' field using POST method")
    public void testVerifyCreationWithSpecialSymbolsInUrl() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "News-Item#";
        String creationDate = Instant.now().toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);

        NewsErrorResponse newsResponse = response.body().as(NewsErrorResponse.class);
        softAssert.assertEquals(newsResponse.getMessage(), "Url Is Invalid");

        softAssert.assertAll();
    }

    @Issue("204")
    @Test
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'text' field using POST method")
    public void testVerifyCreationWithMaxLengthInText() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = "Test News Item";
        String text = generateString(15000);
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        softAssert.assertEquals(newsResponse.getTitle(), title);
        softAssert.assertEquals(newsResponse.getText(), text);
        softAssert.assertEquals(newsResponse.getImageId(), imageId);
        softAssert.assertEquals(newsResponse.getUrl(), url);
        softAssert.assertEquals(newsResponse.getCreationDate(), creationDate);

        softAssert.assertAll();
    }

    @Issue("205")
    @Test
    @Description("Verify that the news cannot be created with character limit exceeded of 'text' field using POST method")
    public void testVerifyCreationWithExceededLengthInText() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = "Test News Item";
        String text = generateString(15001);
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);

        NewsErrorResponse newsResponse = response.body().as(NewsErrorResponse.class);
        softAssert.assertEquals(newsResponse.getMessage(), "'Text': 'Max Length is 15000'");

        softAssert.assertAll();
    }

    @Issue("206")
    @Test
    @Description("Verify that the news is created with the future date chosen in the 'creationDate' field using POST method will have 'Запланована' статус")
    public void testVerifyCreationWithFutureDateInCreationDate() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().plus(7, ChronoUnit.DAYS).toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        softAssert.assertEquals(newsResponse.getTitle(), title);
        softAssert.assertEquals(newsResponse.getText(), text);
        softAssert.assertEquals(newsResponse.getImageId(), imageId);
        softAssert.assertEquals(newsResponse.getUrl(), url);
        softAssert.assertEquals(newsResponse.getCreationDate(), creationDate);
        //TODO: Add assertions for the status field ("Запланована")

        softAssert.assertAll();
    }

    @Issue("207")
    @Test
    @Description("Verify that the news is not created with the past date chosen in the 'creationDate' field using POST method")
    public void testVerifyCreationWithPastDateInCreationDate() {
        NewsRequestBody newNews = new NewsRequestBody();

        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = 3298;
        String url = "news-item";
        String creationDate = Instant.now().minus(7, ChronoUnit.DAYS).toString();

        newNews.setTitle(title);
        newNews.setText(text);
        newNews.setImageId(imageId);
        newNews.setUrl(url);
        newNews.setCreationDate(creationDate);

        Response response = client.create(newNews);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);

        NewsErrorResponse newsResponse = response.body().as(NewsErrorResponse.class);
        softAssert.assertEquals(newsResponse.getMessage(), "The news cannot be published with a past date");

        softAssert.assertAll();
    }

    private String generateString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }

}
