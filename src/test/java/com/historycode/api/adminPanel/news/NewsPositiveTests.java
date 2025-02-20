package com.historycode.api.adminPanel.news;

import com.historycode.api.models.adminPanel.news.NewsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.testng.Assert.assertEquals;


public class NewsPositiveTests extends BaseNewsTests {

    @Issue("197")
    @Test(priority = 1)
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'title' field using POST method")
    public void testVerifyCreationWithMaxTitleLength() {

        requestBody.setTitle(generateRandomAlphanumeric(100));
        requestBody.setCreationDate(Instant.now().toString());

        Response response = client.create(requestBody);

        Assert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        setDeleteId(newsResponse.getId());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newsResponse.getTitle(), requestBody.getTitle());
        softAssert.assertEquals(newsResponse.getText(), requestBody.getText());
        softAssert.assertEquals(newsResponse.getImageId(), requestBody.getImageId());
        softAssert.assertEquals(newsResponse.getUrl(), requestBody.getUrl());
        softAssert.assertNotNull(newsResponse.getCreationDate());
        softAssert.assertAll();
    }

    @Issue("199")
    @Test(priority = 1)
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'url' field using POST method")
    public void testVerifyCreationWithMaxUrlLength() {

        requestBody.setUrl(generateRandomAlphanumeric(200).toLowerCase());
        requestBody.setCreationDate(Instant.now().toString());

        Response response = client.create(requestBody);

        Assert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        setDeleteId(newsResponse.getId());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newsResponse.getTitle(), requestBody.getTitle());
        softAssert.assertEquals(newsResponse.getText(), requestBody.getText());
        softAssert.assertEquals(newsResponse.getImageId(), requestBody.getImageId());
        softAssert.assertEquals(newsResponse.getUrl(), requestBody.getUrl());
        softAssert.assertNotNull(newsResponse.getCreationDate());
        softAssert.assertAll();
    }

    @Issue("204")
    @Test(priority = 1)
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'text' field using POST method")
    public void testVerifyCreationWithMaxLengthInText() {

        requestBody.setText(generateRandomAlphanumeric(15000));
        requestBody.setCreationDate(Instant.now().toString());

        Response response = client.create(requestBody);

        Assert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        setDeleteId(newsResponse.getId());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newsResponse.getTitle(), requestBody.getTitle());
        softAssert.assertEquals(newsResponse.getText(), requestBody.getText());
        softAssert.assertEquals(newsResponse.getImageId(), requestBody.getImageId());
        softAssert.assertEquals(newsResponse.getUrl(), requestBody.getUrl());
        softAssert.assertNotNull(newsResponse.getCreationDate());
        softAssert.assertAll();
    }

    @Issue("206")
    @Test(priority = 1)
    @Description("Verify that the news is created with the future date chosen in the 'creationDate' field using POST method will have 'Запланована' статус")
    public void testVerifyCreationWithFutureDateInCreationDate() {

        requestBody.setCreationDate(Instant.now().plus(7, ChronoUnit.DAYS).toString());

        Response response = client.create(requestBody);

        Assert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        setDeleteId(newsResponse.getId());

        SoftAssert softAssert = new SoftAssert();
        assertEquals(response.path("Status"), "Запланована");
        softAssert.assertEquals(newsResponse.getTitle(), requestBody.getTitle());
        softAssert.assertEquals(newsResponse.getText(), requestBody.getText());
        softAssert.assertEquals(newsResponse.getImageId(), requestBody.getImageId());
        softAssert.assertEquals(newsResponse.getUrl(), requestBody.getUrl());
        softAssert.assertNotNull(newsResponse.getCreationDate());
        softAssert.assertAll();
    }

    @Issue("208")
    @Test(priority = 1)
    @Description("Verify that the news is deleted by 'id' using DELETE method")
    public void testVerifyDeletionById() {

        requestBody.setCreationDate(Instant.now().toString());
        Response response = client.create(requestBody);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        Response deleteResponse = client.delete(newsResponse.getId());

        assertEquals(deleteResponse.getStatusCode(), 200);
    }

}
