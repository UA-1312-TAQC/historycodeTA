package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.ImageClient;
import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.img.ImageRequest;
import com.historycode.api.testRunners.ApiTestRunner;
import com.historycode.utils.ImageProcessor;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Instant;
import java.util.Random;

public class BaseNewsTests extends ApiTestRunner {
    private static final int NO_DELETE_ID = -1;
    protected int deleteId = NO_DELETE_ID;
    protected NewsClient client;
    protected ImageClient imageClient;
    NewsRequestBody requestBody;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
        imageClient = new ImageClient(testValueProvider.getBaseAPIUrl());
    }

    @BeforeMethod
    public void initNewsRequest() {
        requestBody = new NewsRequestBody();
        requestBody.setTitle("Test News Item");
        requestBody.setText("News Item Testing");
        requestBody.setImageId(createNewImg());
        requestBody.setUrl("news-item");
        requestBody.setCreationDate(Instant.now().toString());
    }

    @AfterMethod
    public void tearDownMethod() {
        if (deleteId != NO_DELETE_ID) {
            try {
                client.delete(deleteId);
            } finally {
                deleteId = NO_DELETE_ID;
            }
        }
    }

    public static String generateRandomAlphanumeric(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }

    protected int createNewImg() {
        ImageClient imageClient = new ImageClient(testValueProvider.getBaseAPIUrl());
        ImageRequest newsImage = new ImageRequest();

        newsImage.setTitle("TempImg" + System.currentTimeMillis());
        newsImage.setBaseFormat(ImageProcessor.encodeImage("src/test/resources/newsTest.png"));
        newsImage.setMimeType("image/png");
        newsImage.setExtension("png");
        newsImage.setAlt("1");

        Response response = imageClient.post(newsImage);
        Assert.assertEquals(response.getStatusCode(), 200, "Image was not created");

        return response.getBody().jsonPath().getInt("id");
    }

}
