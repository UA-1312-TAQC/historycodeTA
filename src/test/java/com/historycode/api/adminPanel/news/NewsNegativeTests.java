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

        requestBody.setTitle(generateRandomAlphanumeric(101));
        requestBody.setCreationDate(Instant.now().toString());

        Response response = client.create(requestBody);

        checkError200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.path("errors.Title[0]"), "Max Length is 100",
                "Error message is incorrect");
    }

    @Issue("200")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with character limit exceeded of 'url' field using POST method")
    public void testVerifyCreationWithExceededUrlLength() {

        requestBody.setUrl(generateRandomAlphanumeric(201).toLowerCase());
        requestBody.setCreationDate(Instant.now().toString());

        Response response = client.create(requestBody);

        checkError200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.path("errors.URL[0]"), "Max Length is 200",
                "Error message is incorrect");
    }

    @Issue("201")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with capital letters in 'url' field using POST method")
    public void testVerifyCreationWithCapitalLettersInUrl() {

        requestBody.setUrl("News-item");
        requestBody.setCreationDate(Instant.now().toString());

        Response response = client.create(requestBody);

        checkError200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("Url Is Invalid"),
                "Error message is incorrect");
    }

    @Issue("202")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with cyrillic letters in 'url' field using POST method")
    public void testVerifyCreationWithCyrillicLettersInUrl() {

        requestBody.setUrl("новина");
        requestBody.setCreationDate(Instant.now().toString());

        Response response = client.create(requestBody);

        checkError200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("Url Is Invalid"),
                "Error message is incorrect");
    }

    @Issue("203")
    @Test(dataProvider = "specialSymbolsDataProvider", dataProviderClass = NewsDP.class, priority = 1)
    @Description("Verify that the news cannot be created with special symbols ($,@,%,#) in 'url' field using POST method")
    public void testVerifyCreationWithSpecialSymbolsInUrl(String url) {

        requestBody.setCreationDate(url);
        requestBody.setCreationDate(Instant.now().toString());

        Response response = client.create(requestBody);

        checkError200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("Url Is Invalid"),
                "Error message is incorrect");
    }

    @Issue("205")
    @Test(priority = 1)
    @Description("Verify that the news cannot be created with character limit exceeded of 'text' field using POST method")
    public void testVerifyCreationWithExceededLengthInText() {

        requestBody.setText(generateRandomAlphanumeric(15001));
        requestBody.setCreationDate(Instant.now().toString());

        Response response = client.create(requestBody);

        checkError200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.path("errors.Text[0]"), "Max Length is 15000",
                "Error message is incorrect");
    }

    @Issue("207")
    @Test(priority = 1)
    @Description("Verify that the news is not created with the past date chosen in the 'creationDate' field using POST method")
    public void testVerifyCreationWithPastDateInCreationDate() {

        requestBody.setCreationDate(Instant.now().minus(7, ChronoUnit.DAYS).toString());

        Response response = client.create(requestBody);

        checkError200StatusCode(response);
        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.body().asPrettyString().contains("The news cannot be published with a past date"),
                "Error message is incorrect");
    }

    @Issue("209")
    @Test(priority = 1)
    @Description("Verify that the news cannot be deleted without authorization by 'id' using DELETE method")
    public void testVerifyDeletionWithoutAuthorizationById() {

        requestBody.setCreationDate(Instant.now().toString());
        Response response = client.create(requestBody);

        NewsResponse newsResponse = response.body().as(NewsResponse.class);
        setDeleteId(newsResponse.getId());

        client.setToken(null);
        Response deleteResponse = client.delete(newsResponse.getId());

        assertEquals(deleteResponse.getStatusCode(), 401);
    }

}
