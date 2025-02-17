package com.historycode.api.adminPanel.news;

import com.historycode.api.dataProviders.NewsDP;
import com.historycode.api.models.adminPanel.news.NewsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class NewsNegativeTests extends BaseNewsTests {

    @AfterMethod
    public void setToken() {
        if (client.getToken() == null) {
            client.setToken(testValueProvider.getAccessToken());
        }
    }

    @Issue("198")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with character limit exceeded of 'title' field using POST method")
    public void testVerifyCreationWithExceededTitleLength() {
        setNewsRequest(
                generateRandomAlphanumeric(101),
                "News Item Testing" + System.currentTimeMillis(),
                createNewImg(),
                "news-item" + + System.currentTimeMillis(),
                Instant.now().toString());

        Response response = client.create(requestBody);

        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.path("errors.Title[0]"), "Max Length is 100",
                "Error message is incorrect");
    }

    @Issue("200")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with character limit exceeded of 'url' field using POST method")
    public void testVerifyCreationWithExceededUrlLength() {
        setNewsRequest(
                "Test News Item" + + System.currentTimeMillis(),
                "News Item Testing" + + System.currentTimeMillis(),
                createNewImg(),
                generateRandomAlphanumeric(201).toLowerCase(),
                Instant.now().toString());

        Response response = client.create(requestBody);

        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.path("errors.URL[0]"), "Max Length is 200",
                "Error message is incorrect");
    }

    @Issue("201")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with capital letters in 'url' field using POST method")
    public void testVerifyCreationWithCapitalLettersInUrl() {
        setNewsRequest(
                "Test News Item" + System.currentTimeMillis(),
                "News Item Testing" + System.currentTimeMillis(),
                createNewImg(),
                "News-item" + + System.currentTimeMillis(),
                Instant.now().toString());

        Response response = client.create(requestBody);

        assertEquals(response.getStatusCode(), 400);
        System.out.println(response.body().asPrettyString());
        assertTrue(response.body().asPrettyString().contains("Url Is Invalid"),
                "Error message is incorrect");
    }

    @Issue("202")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with cyrillic letters in 'url' field using POST method")
    public void testVerifyCreationWithCyrillicLettersInUrl() {
        setNewsRequest(
                "Test News Item" + System.currentTimeMillis(),
                "News Item Testing" + System.currentTimeMillis(),
                createNewImg(),
                "новина" + System.currentTimeMillis(),
                Instant.now().toString());

        Response response = client.create(requestBody);

        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("Url Is Invalid"),
                "Error message is incorrect");
    }

    @Issue("203")
    @Test(dataProvider = "specialSymbolsDataProvider", dataProviderClass = NewsDP.class, priority = 1)
    @Description("Verify that the news cannot be created with special symbols ($,@,%,#) in 'url' field using POST method")
    public void testVerifyCreationWithSpecialSymbolsInUrl(String url) {
        setNewsRequest(
                "Test News Item" + System.currentTimeMillis(),
                "News Item Testing" + System.currentTimeMillis(),
                createNewImg(),
                url + System.currentTimeMillis(),
                Instant.now().toString());

        Response response = client.create(requestBody);

        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("Url Is Invalid"),
                "Error message is incorrect");
    }

    @Issue("205")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with character limit exceeded of 'text' field using POST method")
    public void testVerifyCreationWithExceededLengthInText() {
        setNewsRequest(
                "Test News Item" + System.currentTimeMillis(),
                generateRandomAlphanumeric(15001),
                createNewImg(),
                "news-item" + System.currentTimeMillis(),
                Instant.now().toString());

        Response response = client.create(requestBody);

        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.path("errors.Text[0]"), "Max Length is 15000",
                "Error message is incorrect");
    }

    @Issue("207")
    @Test(priority = 1)
    @Description("Verify that the news is not created with the past date chosen in the 'creationDate' field using POST method")
    public void testVerifyCreationWithPastDateInCreationDate() {
        setNewsRequest(
                "Test News Item" + System.currentTimeMillis(),
                "News Item Testing" + System.currentTimeMillis(),
                createNewImg(),
                "news-item" + System.currentTimeMillis(),
                Instant.now().minus(7, ChronoUnit.DAYS).toString());

        Response response = client.create(requestBody);

        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("The news cannot be published with a past date"),
                "Error message is incorrect");
    }

    @Issue("209")
    @Test(priority = 1)
    @Description("Verify that the news cannot be deleted without authorization by 'id' using DELETE method")
    public void testVerifyDeletionWithoutAuthorizationById() {
        setNewsRequest(
                "Test News Item" + System.currentTimeMillis(),
                "News Item Testing" + System.currentTimeMillis(),
                createNewImg(),
                "news-item" + System.currentTimeMillis(),
                Instant.now().toString());

        Response response = client.create(requestBody);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        deleteId = newsResponse.getId();

        client.setToken(null);
        Response deleteResponse = client.delete(newsResponse.getId());

        assertEquals(deleteResponse.getStatusCode(), 401);
    }

}
