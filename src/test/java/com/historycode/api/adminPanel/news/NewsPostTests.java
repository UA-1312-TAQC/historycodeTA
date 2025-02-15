package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.ImageClient;
import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
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

public class NewsPostTests extends ApiTestRunner {

    private NewsClient client;
    private NewsRequestBody newsRequestBody;
    private final String IMAGE_PATH = "src/test/resources/logo.jpeg";
    private int NEWS_ID;

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
        NEWS_ID = response.getBody().jsonPath().getInt("id");
    }

    @Test(dependsOnMethods = "testCreateNewsWithAllRequiredData")
    @Issue("192")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'title' is empty")
    public void testCreateNewsFailsWhenTitleIsEmpty() {
        NewsRequestBody temp = newsRequestBody;
        temp.setTitle(null);
        Response response = client.create(temp);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(dependsOnMethods = "testCreateNewsWithAllRequiredData")
    @Issue("193")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'text' is empty")
    public void testCreateNewsFailsWhenTextIsEmpty() {
        NewsRequestBody temp = newsRequestBody;
        temp.setText(null);
        Response response = client.create(temp);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(dependsOnMethods = "testCreateNewsWithAllRequiredData")
    @Issue("194")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'imageId' is empty using POST method")
    public void testCreateNewsFailsWhenImageIdIsEmpty() {
        NewsRequestBody temp = newsRequestBody;
        temp.setImageId(0);
        Response response = client.create(temp);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(dependsOnMethods = "testCreateNewsWithAllRequiredData")
    @Issue("195")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the news cannot be created if the mandatory field 'url' is empty using POST method")
    public void testCreateNewsFailsWhenUrlIsEmpty() {
        NewsRequestBody temp = newsRequestBody;
        temp.setUrl(null);
        Response response = client.create(temp);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(dependsOnMethods = "testCreateNewsWithAllRequiredData")
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

    @AfterClass
    private void deleteNews() {
        client.delete(NEWS_ID);
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
