package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.ImageClient;
import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.img.ImageRequest;
import com.historycode.api.testRunners.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Random;

public class BaseNewsTests extends ApiTestRunner {
    private static final int NO_DELETE_ID = -1;
    protected int deleteId = NO_DELETE_ID;
    protected NewsClient client;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
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

    protected NewsRequestBody createNewsRequest(String title, String text, int imageId, String url, String creationDate) {
        NewsRequestBody requestBody = new NewsRequestBody();
        requestBody.setTitle(title);
        requestBody.setText(text);
        requestBody.setImageId(imageId);
        requestBody.setUrl(url);
        requestBody.setCreationDate(creationDate);
        return requestBody;
    }

    public static String generateRandomAlphanumericString(int length) {
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

        newsImage.setTitle("TestImg" + System.currentTimeMillis());
        newsImage.setBaseFormat(encodeImageToBase64());
        newsImage.setMimeType("image/jpeg");
        newsImage.setExtension("jpeg");
        newsImage.setAlt("1");

        Response response = imageClient.post(newsImage);
        Assert.assertEquals(response.getStatusCode(), 200, "Image was not created");

        return response.getBody().jsonPath().getInt("id");
    }

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
