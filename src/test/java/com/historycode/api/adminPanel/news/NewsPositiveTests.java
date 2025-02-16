package com.historycode.api.adminPanel.news;

import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.adminPanel.news.NewsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class NewsPositiveTests extends BaseNewsTests {

    @Issue("197")
    @Test(priority = 1)
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'title' field using POST method")
    public void testVerifyCreationWithMaxTitleLength() {
        String title = generateRandomAlphanumericString(100);
        String text = "News Item Testing";
        int imageId = createNewImg();
        String url = "news-item";
        String creationDate = Instant.now().toString();

        setNewsRequest(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        deleteId = newsResponse.getId();

        softAssert.assertEquals(newsResponse.getTitle(), title);
        softAssert.assertEquals(newsResponse.getText(), text);
        softAssert.assertEquals(newsResponse.getImageId(), imageId);
        softAssert.assertEquals(newsResponse.getUrl(), url);
        softAssert.assertEquals(newsResponse.getCreationDate(), creationDate);
        softAssert.assertAll();
    }

    @Issue("199")
    @Test(priority = 1)
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'url' field using POST method")
    public void testVerifyCreationWithMaxUrlLength() {
        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = createNewImg();
        String url = generateRandomAlphanumericString(200).toLowerCase();
        String creationDate = Instant.now().toString();

        setNewsRequest(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        deleteId = newsResponse.getId();

        softAssert.assertEquals(newsResponse.getTitle(), title);
        softAssert.assertEquals(newsResponse.getText(), text);
        softAssert.assertEquals(newsResponse.getImageId(), imageId);
        softAssert.assertEquals(newsResponse.getUrl(), url);
        softAssert.assertEquals(newsResponse.getCreationDate(), creationDate);
        softAssert.assertAll();
    }

    @Issue("204")
    @Test(priority = 1)
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'text' field using POST method")
    public void testVerifyCreationWithMaxLengthInText() {
        String title = "Test News Item";
        String text = generateRandomAlphanumericString(15000);
        int imageId = createNewImg();
        String url = "news-item";
        String creationDate = Instant.now().toString();

        setNewsRequest(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        deleteId = newsResponse.getId();

        softAssert.assertEquals(newsResponse.getTitle(), title);
        softAssert.assertEquals(newsResponse.getText(), text);
        softAssert.assertEquals(newsResponse.getImageId(), imageId);
        softAssert.assertEquals(newsResponse.getUrl(), url);
        softAssert.assertEquals(newsResponse.getCreationDate(), creationDate);
        softAssert.assertAll();
    }

    @Issue("206")
    @Test(priority = 1)
    @Description("Verify that the news is created with the future date chosen in the 'creationDate' field using POST method will have 'Запланована' статус")
    public void testVerifyCreationWithFutureDateInCreationDate() {
        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = createNewImg();
        String url = "news-item";

        String creationDate = Instant.now().plus(7, ChronoUnit.DAYS).toString();

        setNewsRequest(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        deleteId = newsResponse.getId();

        //TODO: Add assertions for the status field ("Запланована")
        softAssert.assertEquals(newsResponse.getTitle(), title);
        softAssert.assertEquals(newsResponse.getText(), text);
        softAssert.assertEquals(newsResponse.getImageId(), imageId);
        softAssert.assertEquals(newsResponse.getUrl(), url);
        softAssert.assertEquals(newsResponse.getCreationDate(), creationDate);
        softAssert.assertAll();
    }


    @Issue("208")
    @Test(priority = 1)
    @Description("Verify that the news is deleted by 'id' using DELETE method")
    public void testVerifyDeletionById() {
        String title = "Test News Item";
        String text = "News Item Testing";
        int imageId = createNewImg();
        String url = "news-item";
        String creationDate = Instant.now().toString();

        setNewsRequest(title, text, imageId, url, creationDate);

        Response response = client.create(requestBody);
        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        Response deleteResponse = client.delete(newsResponse.getId());

        Assert.assertEquals(deleteResponse.getStatusCode(), 200);
    }

}
