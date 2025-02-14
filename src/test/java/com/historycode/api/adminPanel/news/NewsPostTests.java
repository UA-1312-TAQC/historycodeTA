package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.ImageClient;
import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.img.ImageRequest;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class NewsPostTests extends ApiTestRunner {

    private NewsClient client;
    private NewsRequestBody newsRequestBody;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
        setUpRequest();
    }

    @Test
    @Issue("191")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify if the news is created with all required data using POST method")
    public void testCreateNewsWithAllRequiredData() {
        Response response = client.create(newsRequestBody);
        Assert.assertEquals(response.getStatusCode(), 200, "News was not created");
        client.delete(response.getBody().jsonPath().getInt("id"));
    }

    @Test
    @Issue("192")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'title' is empty")
    public void testCreateNewsFailsWhenTitleIsEmpty() {
        NewsRequestBody temp = newsRequestBody;
        temp.setTitle(null);
        Response response = client.create(temp);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    @Issue("193")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'text' is empty")
    public void testCreateNewsFailsWhenTextIsEmpty() {
        NewsRequestBody temp = newsRequestBody;
        temp.setText(null);
        Response response = client.create(temp);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    @Issue("194")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'imageId' is empty using POST method")
    public void testCreateNewsFailsWhenImageIdIsEmpty() {
        NewsRequestBody temp = newsRequestBody;
        temp.setImageId(0);
        Response response = client.create(temp);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    @Issue("195")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'url' is empty using POST method")
    public void testCreateNewsFailsWhenUrlIsEmpty() {
        NewsRequestBody temp = newsRequestBody;
        temp.setUrl(null);
        Response response = client.create(temp);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    @Issue("196")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'creationDate' is empty using POST method")
    public void testCreateNewsFailsWhenCreationDateIsEmpty() {
        NewsRequestBody temp = newsRequestBody;
        temp.setCreationDate(null);
        Response response = client.create(temp);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Step("Filling the request with information")
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
        newsImage.setBaseFormat(encodeImageToBase64());
        newsImage.setMimeType("image/jpeg");
        newsImage.setExtension("jpeg");
        newsImage.setAlt("1");

        Response response = imageClient.post(newsImage);
        Assert.assertEquals(response.getStatusCode(), 200, "Image was not created");

        return response.getBody().jsonPath().getInt("id");
    }

    //todo: Replace with the class that provides this functionality
    private static String encodeImageToBase64() {
        try {
            byte[] imageBytes = Files.readAllBytes(Path.of("src/test/resources/logo.jpeg"));
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
