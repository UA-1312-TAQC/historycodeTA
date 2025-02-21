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
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


@Slf4j
public class BaseNewsTests extends ApiTestRunner {
    protected final String errorMessageFieldNotMatch = "The %s field does not match";

    @Getter
    @Setter
    private Integer newsId = null;
    protected NewsClient newsClient;
    protected ImageClient imageClient;
    protected NewsRequestBody newsRequestBody;

    @BeforeClass
    public void setUpClass() {
        newsClient = new NewsClient(testValueProvider.getBaseAPIUrl());
        newsClient.setToken(testValueProvider.getAccessToken());
        imageClient = new ImageClient(testValueProvider.getBaseAPIUrl());
    }

    @BeforeMethod
    public void initNewsRequest() {
        newsRequestBody = createNewsRequestBody();
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (newsId != null) {
                Response deleteNewsResponse = newsClient.delete(newsId);
                if (deleteNewsResponse.getStatusCode() != 200) {
                    log.error("The test news item with ID {} was not deleted.", newsId);
                }
            } else if (newsRequestBody.getImageId() != 0) {
                Response deleteImageResponse = imageClient.delete(newsRequestBody.getImageId());
                if (deleteImageResponse.getStatusCode() != 200) {
                    log.error("The test image item with ID {} was not deleted.", newsRequestBody.getImageId());
                }
            }
        } catch (Exception ex) {
            log.error("An error occurred while deleting test data: {}", ex.getMessage(), ex);
        }
    }

    private NewsRequestBody createNewsRequestBody() {
        long timestamp = System.currentTimeMillis();

        newsRequestBody = new NewsRequestBody();
        newsRequestBody.setTitle("Test News Item " + timestamp);
        newsRequestBody.setText("News Item Testing " + timestamp);
        newsRequestBody.setImageId(createNewImg());
        newsRequestBody.setUrl("news-item" + timestamp);

        return newsRequestBody;
    }

    private int createNewImg() {
        ImageClient imageClient = new ImageClient(testValueProvider.getBaseAPIUrl());
        ImageRequest newsImage = new ImageRequest();

        newsImage.setTitle("TestImg" + System.currentTimeMillis());
        newsImage.setBaseFormat(ImageProcessor.encodeImage("src/test/resources/newsTest.png"));
        newsImage.setMimeType("image/png");
        newsImage.setExtension("png");
        newsImage.setAlt("1");

        Response response = imageClient.post(newsImage);
        Assert.assertEquals(response.getStatusCode(), 200,
                "The test image item was not created.");

        return response.getBody().jsonPath().getInt("id");
    }

    protected void handleUnexpected200StatusCode(Response response) {
        if (response.getStatusCode() == 200) {
            NewsResponse newsResponse = response.body().as(NewsResponse.class);
            setNewsId(newsResponse.getId());
        }
    }

}
