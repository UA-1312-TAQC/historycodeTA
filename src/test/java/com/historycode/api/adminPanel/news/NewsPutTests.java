package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.ImageClient;
import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.adminPanel.news.NewsUpdateRequestBody;
import com.historycode.api.models.img.ImageRequest;
import com.historycode.api.testRunners.ApiTestRunner;
import com.historycode.utils.ImageProcessor;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class NewsPutTests extends ApiTestRunner {

    private NewsClient client;
    private NewsRequestBody newsRequestBody;
    NewsUpdateRequestBody newsUpdateRequestBody;
    private final String IMAGE_PATH = "src/test/resources/logo.jpeg";
    private int NEWS_ID;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());

        setUpRequest();

        Response response = client.create(newsRequestBody);
        Assert.assertEquals(response.getStatusCode(), 200, "News was not created");
        NEWS_ID = response.getBody().jsonPath().getInt("id");

        setUpUpdateRequest();
    }


    @Test
    @Issue("225")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify Maximum Length Restriction for the \"text\" Field When Updating News")
    public void verifyMaximumLengthRestrictionForTextFieldWhenUpdatingNews() {
        NewsUpdateRequestBody requestBodyTemp = newsUpdateRequestBody;

        String text = "A".repeat(15000) + "CharText";

        requestBodyTemp.setText(text);

        Response response = client.update(requestBodyTemp);
        Assert.assertEquals(response.getStatusCode(), 400, "The news was updated with more text than allowed");
    }

    @Test
    @Issue("224")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the 'url' field does not accept special characters ($,@,%,#) when updating news using the PUT method")
    public void verifyUrlFieldDoesNotAcceptSpecialCharactersWhenUpdatingNewsUsingPUT() {
        NewsUpdateRequestBody requestBodyTemp = newsUpdateRequestBody;
        String baseUrl = requestBodyTemp.getUrl();

        String specialCharacter = "$";
        requestBodyTemp.setUrl(baseUrl + specialCharacter);
        Response response = client.update(requestBodyTemp);
        Assert.assertEquals(response.getStatusCode(), 400, "The news was updated with unacceptable special characters");

        specialCharacter = "@";
        requestBodyTemp.setUrl(baseUrl + specialCharacter);
        response = client.update(requestBodyTemp);
        Assert.assertEquals(response.getStatusCode(), 400, "The news was updated with unacceptable special characters");

        specialCharacter = "%";
        requestBodyTemp.setUrl(baseUrl + specialCharacter);
        response = client.update(requestBodyTemp);
        Assert.assertEquals(response.getStatusCode(), 400, "The news was updated with unacceptable special characters");

        specialCharacter = "#";
        requestBodyTemp.setUrl(baseUrl + specialCharacter);
        response = client.update(requestBodyTemp);
        Assert.assertEquals(response.getStatusCode(), 400, "The news was updated with unacceptable special characters");
    }

    @Test
    @Issue("223")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the 'url' field does not accept Cyrillic letters when updating news using the PUT method")
    public void verifyUrlFieldDoesNotAcceptCyrillicLettersWhenUpdatingNewsUsingPUT() {
        NewsUpdateRequestBody requestBodyTemp = newsUpdateRequestBody;
        String baseUrl = requestBodyTemp.getUrl();

        String specialCharacter = "привіт";
        requestBodyTemp.setUrl(baseUrl + specialCharacter);
        Response response = client.update(requestBodyTemp);
        Assert.assertEquals(response.getStatusCode(), 400, "The news was updated with unacceptable Cyrillic letters");
    }

    @Test
    @Issue("222")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the 'url' field does not accept capital letters when updating news using the PUT method")
    public void verifyUrlFieldDoesNotAcceptCapitalLettersWhenUpdatingNewsUsingPUT() {
        NewsUpdateRequestBody requestBodyTemp = newsUpdateRequestBody;
        String baseUrl = requestBodyTemp.getUrl();

        String specialCharacter = "CAPITAL";
        requestBodyTemp.setUrl(baseUrl + specialCharacter);
        Response response = client.update(requestBodyTemp);
        Assert.assertEquals(response.getStatusCode(), 400, "The news was updated with unacceptable capital letters");
    }


    @AfterClass
    public void clearClass() {
        Response response = client.delete(NEWS_ID);
        Assert.assertEquals(response.getStatusCode(), 200, "Image was not deleted");
    }

    @Step("Filling the request with information")
    private void setUpUpdateRequest() {
        newsUpdateRequestBody = new NewsUpdateRequestBody();

        newsUpdateRequestBody.setTitle(newsRequestBody.getTitle());
        newsUpdateRequestBody.setText(newsRequestBody.getText());
        newsUpdateRequestBody.setImageId(newsRequestBody.getImageId());
        newsUpdateRequestBody.setUrl(newsRequestBody.getUrl());
        newsUpdateRequestBody.setCreationDate(newsRequestBody.getCreationDate());
        newsUpdateRequestBody.setId(NEWS_ID);
    }

    @Step("Filling the post request with information")
    private void setUpRequest() {
        newsRequestBody = new NewsRequestBody();

        newsRequestBody.setTitle("TestNews" + System.currentTimeMillis());
        newsRequestBody.setText(newsRequestBody.getTitle());
        newsRequestBody.setImageId(createNewImg());
        newsRequestBody.setUrl(newsRequestBody.getTitle().toLowerCase());
        newsRequestBody.setCreationDate(DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC).format(Instant.now()));
    }

    @Step("Creating a new image for a request")
    private int createNewImg() {
        ImageClient imageClient = new ImageClient(testValueProvider.getBaseAPIUrl());
        ImageRequest newsImage = new ImageRequest();

        newsImage.setTitle("TestImg" + System.currentTimeMillis());
        newsImage.setBaseFormat(ImageProcessor.encodeImage(IMAGE_PATH));
        newsImage.setMimeType("image/jpeg");
        newsImage.setExtension("jpeg");
        newsImage.setAlt("1");

        Response response = imageClient.post(newsImage);
        Assert.assertEquals(response.getStatusCode(), 200, "Image was not created");

        return response.getBody().jsonPath().getInt("id");
    }
}
