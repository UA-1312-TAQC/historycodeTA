package com.historycode.api.adminPanel.news;

import com.historycode.api.dataProviders.NewsDP;
import com.historycode.api.models.adminPanel.news.NewsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class NewsNegativeTests extends BaseNewsTests {

    @AfterMethod
    public void setToken() {
        if (newsClient.getToken() == null) {
            newsClient.setToken(testValueProvider.getAccessToken());
        }
    }

    @Issue("198")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with character limit exceeded of 'title' field using POST method")
    public void testVerifyCreationWithExceededTitleLength() {

        newsRequestBody.setTitle(RandomStringUtils.randomAlphabetic(101));
        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);

        verifyUnexpected200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.path("errors.Title[0]"), "Max Length is 100",
                "The error message should indicate that title length exceeds 100 characters limit");
    }

    @Issue("200")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with character limit exceeded of 'url' field using POST method")
    public void testVerifyCreationWithExceededUrlLength() {

        newsRequestBody.setUrl(RandomStringUtils.randomAlphabetic(201).toLowerCase());
        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);

        verifyUnexpected200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.path("errors.URL[0]"), "Max Length is 200",
                "The error message should indicate that URL length exceeds 200 characters limit");
    }

    @Issue("201")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with capital letters in 'url' field using POST method")
    public void testVerifyCreationWithCapitalLettersInUrl() {

        newsRequestBody.setUrl("News-item");
        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);

        verifyUnexpected200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("Url Is Invalid"),
                "Error message should contain 'Url Is Invalid' text");
    }

    @Issue("202")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with cyrillic letters in 'url' field using POST method")
    public void testVerifyCreationWithCyrillicLettersInUrl() {

        newsRequestBody.setUrl("новина");
        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);

        verifyUnexpected200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("Url Is Invalid"),
                "Error message should contain 'Url Is Invalid' text");
    }

    @Issue("203")
    @Test(dataProvider = "specialSymbolsDataProvider", dataProviderClass = NewsDP.class, priority = 1)
    @Description("Verify that the news cannot be created with special symbols ($,@,%,#) in 'url' field using POST method")
    public void testVerifyCreationWithSpecialSymbolsInUrl(String url) {

        newsRequestBody.setUrl(url);
        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);

        verifyUnexpected200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("Url Is Invalid"),
                "Error message should contain 'Url Is Invalid' text");
    }

    @Issue("205")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with character limit exceeded of 'text' field using POST method")
    public void testVerifyCreationWithExceededLengthInText() {

        newsRequestBody.setText(RandomStringUtils.randomAlphabetic(15001));
        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);

        verifyUnexpected200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.path("errors.Text[0]"), "Max Length is 15000",
                "Error message should indicate that the Text field is required");
    }

    @Issue("207")
    @Test(priority = 1)
    @Description("Verify that the news is not created with the past date chosen in the 'creationDate' field using POST method")
    public void testVerifyCreationWithPastDateInCreationDate() {

        newsRequestBody.setCreationDate(Instant.now().minus(7, ChronoUnit.DAYS).toString());

        Response response = newsClient.create(newsRequestBody);

        verifyUnexpected200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("The news cannot be published with a past date"),
                "Error message should indicate that news cannot be published with a past date");
    }

    @Issue("209")
    @Test(priority = 1)
    @Description("Verify that the news cannot be deleted without authorization by 'id' using DELETE method")
    public void testVerifyDeletionWithoutAuthorizationById() {

        newsRequestBody.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(newsRequestBody);

        assertEquals(response.getStatusCode(), 200, "Failed to create the news item");
        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        setNewsId(newsResponse.getId());

        newsClient.setToken(null);
        Response deleteResponse = newsClient.delete(newsResponse.getId());

        assertEquals(deleteResponse.getStatusCode(), 401);
    }

}
