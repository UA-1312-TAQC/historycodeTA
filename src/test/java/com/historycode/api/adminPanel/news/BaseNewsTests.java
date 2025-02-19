package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.ImageClient;
import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.adminPanel.news.NewsResponse;
import com.historycode.api.models.img.ImageRequest;
import com.historycode.api.testRunners.ApiTestRunner;
import com.historycode.utils.ImageProcessor;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Instant;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class BaseNewsTests extends ApiTestRunner {
    @Getter
    @Setter
    private Integer deleteId = null;
    protected NewsClient client;
    protected ImageClient imageClient;
    protected NewsRequestBody requestBody;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
        imageClient = new ImageClient(testValueProvider.getBaseAPIUrl());
    }

    @BeforeMethod
    public void initNewsRequest() {
        requestBody = createNewsRequest();
    }

    @AfterMethod
    public void tearDownMethod() {
        if (getDeleteId() != null) {
            try {
                Response deleteResponse = client.delete(getDeleteId());
                assertEquals(deleteResponse.getStatusCode(), 200,
                        String.format("The test news item with ID %s was not deleted.", getDeleteId()));
            } finally {
                setDeleteId(null);
            }
        }
    }

    private NewsRequestBody createNewsRequest() {
        long timestamp = Instant.now().toEpochMilli();

        requestBody = new NewsRequestBody();
        requestBody.setTitle("Test News Item " + timestamp);
        requestBody.setText("News Item Testing " + timestamp);
        requestBody.setImageId(createNewImg());
        requestBody.setUrl("news-item-" + timestamp);

        return requestBody;
    }

    private int createNewImg() {
        ImageClient imageClient = new ImageClient(testValueProvider.getBaseAPIUrl());
        ImageRequest newsImage = new ImageRequest();

        newsImage.setTitle("TestImg" + Instant.now().toEpochMilli());
        newsImage.setBaseFormat(ImageProcessor.encodeImage("src/test/resources/newsTest.png"));
        newsImage.setMimeType("image/png");
        newsImage.setExtension("png");
        newsImage.setAlt("1");

        Response response = imageClient.post(newsImage);
        Assert.assertEquals(response.getStatusCode(), 200,
                "The test image item was not created.");

        return response.getBody().jsonPath().getInt("id");
    }

    protected static String generateRandomAlphanumeric(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        char[] result = new char[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result[i] = characters.charAt(random.nextInt(characters.length()));
        }

        return new String(result);
    }

    protected void checkError200StatusCode(Response response) {
        if (response.getStatusCode() == 200) {
            NewsResponse newsResponse = response.body().as(NewsResponse.class);
            setDeleteId(newsResponse.getId());
        }
    }

}
