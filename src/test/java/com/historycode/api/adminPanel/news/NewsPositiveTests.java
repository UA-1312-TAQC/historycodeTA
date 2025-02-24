package com.historycode.api.adminPanel.news;

import com.historycode.api.models.adminPanel.news.NewsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
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

        newsRequestBody.setTitle(RandomStringUtils.randomAlphabetic(100));
        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);
        Assert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        verifyNewsResponseStructure(newsResponse);
    }

    @Issue("199")
    @Test(priority = 1)
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'url' field using POST method")
    public void testVerifyCreationWithMaxUrlLength() {

        newsRequestBody.setUrl(RandomStringUtils.randomAlphabetic(200).toLowerCase());
        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);
        Assert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        verifyNewsResponseStructure(newsResponse);
    }

    @Issue("204")
    @Test(priority = 1)
    @Description("Verify that the news is created with the maximum number of characters allowed in the 'text' field using POST method")
    public void testVerifyCreationWithMaxLengthInText() {

        newsRequestBody.setText(RandomStringUtils.randomAlphabetic(15000));
        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);
        Assert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        verifyNewsResponseStructure(newsResponse);
    }

    @Issue("206")
    @Test(priority = 1)
    @Description("Verify that the news is created with the future date chosen in the 'creationDate' field using POST method will have 'Запланована' статус")
    public void testVerifyCreationWithFutureDateInCreationDate() {

        newsRequestBody.setCreationDate(Instant.now().plus(7, ChronoUnit.DAYS).toString());

        Response response = newsClient.create(newsRequestBody);
        Assert.assertEquals(response.getStatusCode(), 200);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        verifyNewsResponseStructure(newsResponse);
        assertEquals(response.path("Status"), "Запланована", "The 'Status' field is absent or has incorrect value");
    }

    @Issue("208")
    @Test(priority = 1)
    @Description("Verify that the news is deleted by 'id' using DELETE method")
    public void testVerifyDeletionById() {

        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);

        assertEquals(response.getStatusCode(), 200, "Failed to create the news item");
        NewsResponse newsResponse = response.body().as(NewsResponse.class);

        Response deleteResponse = newsClient.delete(newsResponse.getId());

        assertEquals(deleteResponse.getStatusCode(), 200, "Failed to delete the news item with ID " + newsResponse.getId());
    }

    private void verifyNewsResponseStructure(NewsResponse newsResponse) {
        setNewsId(newsResponse.getId());

        Instant actualCreationDate = Instant.parse(newsResponse.getCreationDate()).truncatedTo(ChronoUnit.MINUTES);
        Instant expectedCreationDate = Instant.parse(newsRequestBody.getCreationDate()).truncatedTo(ChronoUnit.MINUTES);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newsResponse.getTitle(), newsRequestBody.getTitle(), String.format(errorMessageFieldNotMatch, "title"));
        softAssert.assertEquals(newsResponse.getText(), newsRequestBody.getText(), String.format(errorMessageFieldNotMatch, "text"));
        softAssert.assertEquals(newsResponse.getImageId(), newsRequestBody.getImageId(), String.format(errorMessageFieldNotMatch, "imageId"));
        softAssert.assertEquals(newsResponse.getUrl(), newsRequestBody.getUrl(), String.format(errorMessageFieldNotMatch, "url"));
        softAssert.assertEquals(actualCreationDate, expectedCreationDate, String.format(errorMessageFieldNotMatch, "creationDate"));

        softAssert.assertAll();
    }

}
