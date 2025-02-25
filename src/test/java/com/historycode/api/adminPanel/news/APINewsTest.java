package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.ImageClient;
import com.historycode.api.clients.NewsClient;

import com.historycode.api.models.adminPanel.news.*;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Instant;
import java.util.List;



public class APINewsTest extends ApiTestRunner {
    private NewsClient newsClient;
    private int newsId;

    @BeforeClass
    public void init() {
        newsClient = new NewsClient(testValueProvider.getBaseAPIUrl());
        newsClient.setToken(testValueProvider.getAccessToken());
    }

    @Issue("190")
    @Test
    @Description("Verify if all news are displayed using GET method.")
    public void testGetAllNews() {
        Response response = newsClient.getAll();
        SoftAssert softAssert = new SoftAssert();

        Assert.assertEquals(response.getStatusCode(), 200, "Response status is not 200 OK");
        GetAllNewsResponse getAllResponse = response.body().as(GetAllNewsResponse.class);
        Assert.assertTrue(getAllResponse.getTotalAmount() > 0, "Total amount of news is 0");

        for (News news : getAllResponse.getNews()) {
            softAssert.assertNotNull(news.getId(), "Key 'id' is missing in the response for news");
            softAssert.assertNotNull(news.getTitle(), "Key 'title' is missing in the response for news");
            softAssert.assertNotNull(news.getText(), "Key 'text' is missing in the response for news");
            softAssert.assertNotNull(news.getCreationDate(), "Key 'creationDate' is missing in the response for news");

            NewsImage image = news.getImage();
            if (image != null) {
                softAssert.assertNotNull(image.getId(), "Key 'image.id' is missing in the response for news");
                softAssert.assertNotNull(image.getBlobName(), "Key 'image.blobName' is missing in the response for news");
                softAssert.assertNotNull(image.getMimeType(), "Key 'image.mimeType' is missing in the response for news");

                ImageDetails imageDetails = image.getImageDetails();
                if (imageDetails != null) {
                    softAssert.assertNotNull(imageDetails.getId(), "Key 'imageDetails.id' is missing in the response for news");
                    softAssert.assertNotNull(imageDetails.getTitle(), "Key 'imageDetails.title' is missing in the response for news");
                    softAssert.assertNotNull(imageDetails.getAlt(), "Key 'imageDetails.alt' is missing in the response for news");
                }
            }
        }
        softAssert.assertAll();
    }

    @Issue("213")
    @Test
    @Description("Verify that creating a news item with invalid data returns 400 Bad Request.")
    public void testCreateInvalidNews() {
        SoftAssert softAssert = new SoftAssert();
        NewsRequestBody invalidNews = new NewsRequestBody();

        invalidNews.setTitle("Тестова новина");
        invalidNews.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et.");
        invalidNews.setImageId(0);
        invalidNews.setUrl("test-link");
        invalidNews.setCreationDate(Instant.now().toString());

        Response response = newsClient.create(invalidNews);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        softAssert.assertEquals(response.getStatusCode(), 400, "Response status is not 400 Bad Request");

        softAssert.assertAll();
    }

    @Issue("214")
    @Test
    @Description("Verify that an existing news item can be updated using PUT method.")
    public void testUpdateNews() {
        SoftAssert softAssert = new SoftAssert();
        GetAllNewsResponse getAllResponse = newsClient.getAll().body().as(GetAllNewsResponse.class);
        List<News> newsList = getAllResponse.getNews();
        softAssert.assertFalse(newsList.isEmpty(), "No news found in the system");

        int newsId = newsList.getFirst().getId();
        int imageId = newsList.getFirst().getImage().getId();

        NewsUpdateRequestBody updatedNews = new NewsUpdateRequestBody();
        updatedNews.setId(newsId);
        updatedNews.setTitle("News KH");
        updatedNews.setText("News Testing");
        updatedNews.setImageId(imageId);
        updatedNews.setUrl("news");
        updatedNews.setCreationDate(Instant.now().toString());

        Response updateResponse = newsClient.update(updatedNews);
        softAssert.assertEquals(updateResponse.getStatusCode(), 200, "News update failed");

        Response updatedNewsData = newsClient.getById(newsId);
        News updatedNewsResponse = updatedNewsData.body().as(News.class);

        softAssert.assertEquals(updatedNewsResponse.getId(), newsId, "News ID mismatch");
        softAssert.assertEquals(updatedNewsResponse.getTitle(), "News KH", "Title mismatch");
        softAssert.assertEquals(updatedNewsResponse.getText(), "News Testing", "Text mismatch");
        softAssert.assertEquals(Integer.parseInt(updatedNewsResponse.getImageId()), imageId, "Image ID mismatch");
        softAssert.assertEquals(updatedNewsResponse.getUrl(), "news", "URL mismatch");

        softAssert.assertAll();
    }

    @Issue("215")
    @Test
    @Description("Verify that the news cannot be updated if the mandatory field 'title' is empty using PUT method.")
    public void testUpdateNewsWithEmptyTitle() {
        SoftAssert softAssert = new SoftAssert();
        GetAllNewsResponse getAllResponse = newsClient.getAll().body().as(GetAllNewsResponse.class);
        List<News> newsList = getAllResponse.getNews();
        softAssert.assertFalse(newsList.isEmpty(), "No news found in the system");

        int newsId = newsList.get(0).getId();
        int imageId = newsList.get(0).getImage().getId();

        NewsUpdateRequestBody invalidNews = new NewsUpdateRequestBody();
        invalidNews.setId(newsId);
        invalidNews.setTitle("");
        invalidNews.setText("News Testing");
        invalidNews.setImageId(imageId);
        invalidNews.setUrl("news");
        invalidNews.setCreationDate(Instant.now().toString());

        Response response = newsClient.update(invalidNews);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        softAssert.assertEquals(response.getStatusCode(), 400, "Response status is not 400 Bad Request");

        String responseBody = response.getBody().asString();
        softAssert.assertTrue(responseBody.contains("The Title field is required."),
                "Expected error message 'The Title field is required.' was not found in response body");

        softAssert.assertAll();
    }

    @Issue("216")
    @Test
    @Description("Verify that the news cannot be updated if the mandatory field 'text' is empty using PUT method.")
    public void testUpdateNewsWithEmptyText() {
        SoftAssert softAssert = new SoftAssert();
        GetAllNewsResponse getAllResponse = newsClient.getAll().body().as(GetAllNewsResponse.class);
        List<News> newsList = getAllResponse.getNews();
        softAssert.assertFalse(newsList.isEmpty(), "No news found in the system");

        int newsId = newsList.get(0).getId();
        int imageId = newsList.get(0).getImage().getId();

        NewsUpdateRequestBody invalidNews = new NewsUpdateRequestBody();
        invalidNews.setId(newsId);
        invalidNews.setTitle("Invalid News");
        invalidNews.setText("");
        invalidNews.setImageId(imageId);
        invalidNews.setUrl("news");
        invalidNews.setCreationDate(Instant.now().toString());

        Response response = newsClient.update(invalidNews);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        softAssert.assertEquals(response.getStatusCode(), 400, "Response status is not 400 Bad Request");

        String responseBody = response.getBody().asString();
        softAssert.assertTrue(responseBody.contains("The Text field is required."),
                "Expected error message 'The Text field is required.' was not found in response body");

        softAssert.assertAll();
    }

    @Issue("217")
    @Test
    @Description("Verify that the news cannot be updated if the mandatory field 'imageId' is empty using PUT method.")
    public void testUpdateNewsWithEmptyImageId() {
        SoftAssert softAssert = new SoftAssert();

        GetAllNewsResponse getAllResponse = newsClient.getAll().body().as(GetAllNewsResponse.class);
        List<News> newsList = getAllResponse.getNews();
        softAssert.assertFalse(newsList.isEmpty(), "No news found in the system");

        int newsId = newsList.get(0).getId();

        NewsUpdateRequestBody invalidNews = new NewsUpdateRequestBody();
        invalidNews.setId(newsId);
        invalidNews.setTitle("Victory for Ukraine");
        invalidNews.setText("News Testing");
        invalidNews.setImageId(0);
        invalidNews.setUrl("news");
        invalidNews.setCreationDate(Instant.now().toString());

        Response response = newsClient.update(invalidNews);

        String responseBody = response.getBody().asString();
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + responseBody);

        softAssert.assertEquals(response.getStatusCode(), 400, "Response status is not 400 Bad Request");

        softAssert.assertTrue(responseBody.contains("Поле 'Ідентифікатор картинки' не може бути пусте"),
                "Expected error message about imageId was not found in response body. Actual response: " + responseBody);

        softAssert.assertAll();
    }

    @Issue("218")
    @Test
    @Description("Verify that the news cannot be updated if the mandatory field 'url' is empty using PUT method.")
    public void testUpdateNewsWithEmptyUrl() {
        SoftAssert softAssert = new SoftAssert();

        GetAllNewsResponse getAllResponse = newsClient.getAll().body().as(GetAllNewsResponse.class);
        List<News> newsList = getAllResponse.getNews();
        softAssert.assertFalse(newsList.isEmpty(), "No news found in the system");

        int newsId = newsList.get(0).getId();
        int imageId = newsList.get(0).getImage().getId();

        NewsUpdateRequestBody invalidNews = new NewsUpdateRequestBody();
        invalidNews.setId(newsId);
        invalidNews.setTitle("Test News");
        invalidNews.setText("News Testing");
        invalidNews.setImageId(imageId);
        invalidNews.setUrl("");
        invalidNews.setCreationDate(Instant.now().toString());

        Response response = newsClient.update(invalidNews);

        String responseBody = response.getBody().asString();
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + responseBody);

        softAssert.assertEquals(response.getStatusCode(), 400, "Response status is not 400 Bad Request");

        softAssert.assertTrue(responseBody.contains("The URL field is required."),
                "Expected error message 'The URL field is required.' was not found in response body. Actual response: " + responseBody);

        softAssert.assertAll();
    }

    @Issue("219")
    @Test
    @Description("Verify that the news cannot be updated if the mandatory field 'creationDate' is empty using PUT method.")
    public void testUpdateNewsWithEmptyCreationDate() {
        SoftAssert softAssert = new SoftAssert();

        GetAllNewsResponse getAllResponse = newsClient.getAll().body().as(GetAllNewsResponse.class);
        List<News> newsList = getAllResponse.getNews();
        softAssert.assertFalse(newsList.isEmpty(), "No news found in the system");

        int newsId = newsList.get(0).getId();
        int imageId = newsList.get(0).getImage().getId();

        NewsUpdateRequestBody invalidNews = new NewsUpdateRequestBody();
        invalidNews.setId(newsId);
        invalidNews.setTitle("Test News");
        invalidNews.setText("News Testing");
        invalidNews.setImageId(imageId);
        invalidNews.setUrl("news");
        invalidNews.setCreationDate("");

        Response response = newsClient.update(invalidNews);

        String responseBody = response.getBody().asString();
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + responseBody);

        softAssert.assertEquals(response.getStatusCode(), 400, "Response status is not 400 Bad Request");

        softAssert.assertTrue(responseBody.contains("The JSON value could not be converted to System.DateTime"),
                "Expected error message about creationDate format was not found in response body. Actual response: " + responseBody);

        softAssert.assertAll();
    }

    @Issue("220")
    @Test
    @Description("Verify that the 'title' field cannot exceed the 100-character limit when updating news using the PUT method.")
    public void testUpdateNewsWithLongTitle() {
        SoftAssert softAssert = new SoftAssert();

        GetAllNewsResponse getAllResponse = newsClient.getAll().body().as(GetAllNewsResponse.class);
        List<News> newsList = getAllResponse.getNews();
        softAssert.assertFalse(newsList.isEmpty(), "No news found in the system");

        int newsId = newsList.get(0).getId();
        int imageId = newsList.get(0).getImage().getId();

        String longTitle = "A".repeat(100) + "q";

        NewsUpdateRequestBody invalidNews = new NewsUpdateRequestBody();
        invalidNews.setId(newsId);
        invalidNews.setTitle(longTitle);
        invalidNews.setText("News Testing");
        invalidNews.setImageId(imageId);
        invalidNews.setUrl("news");
        invalidNews.setCreationDate(Instant.now().toString());

        Response response = newsClient.update(invalidNews);

        String responseBody = response.getBody().asString();
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + responseBody);

        softAssert.assertEquals(response.getStatusCode(), 400, "Response status is not 400 Bad Request");

        softAssert.assertTrue(responseBody.contains("Max Length is 100"),
                "Expected error message 'Title: Max Length is 100' was not found in response body. Actual response: " + responseBody);

        softAssert.assertAll();
    }

    @Issue("221")
    @Test
    @Description("Verify that the 'url' field does not accept values exceeding the 200-character limit when updating news using the PUT method.")
    public void testUpdateNewsWithLongUrl() {
        SoftAssert softAssert = new SoftAssert();

        GetAllNewsResponse getAllResponse = newsClient.getAll().body().as(GetAllNewsResponse.class);
        List<News> newsList = getAllResponse.getNews();
        softAssert.assertFalse(newsList.isEmpty(), "No news found in the system");

        int newsId = newsList.get(0).getId();
        int imageId = newsList.get(0).getImage().getId();

        String longUrl = "a".repeat(200) + "q";

        NewsUpdateRequestBody invalidNews = new NewsUpdateRequestBody();
        invalidNews.setId(newsId);
        invalidNews.setTitle("Test News");
        invalidNews.setText("News Testing");
        invalidNews.setImageId(imageId);
        invalidNews.setUrl(longUrl);
        invalidNews.setCreationDate(Instant.now().toString());

        Response response = newsClient.update(invalidNews);

        String responseBody = response.getBody().asString();
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + responseBody);

        softAssert.assertEquals(response.getStatusCode(), 400, "Response status is not 400 Bad Request");

        softAssert.assertTrue(responseBody.contains("Max Length is 200"),
                "Expected error message 'URL: Max Length is 200' was not found in response body. Actual response: " + responseBody);

        softAssert.assertAll();
    }

    @AfterClass
    public void clearTestNews() {
        if (newsId > 0) {
            Response deleteResponse = newsClient.delete(newsId);
            System.out.println("Delete Status Code: " + deleteResponse.getStatusCode());
            System.out.println("Delete Response Body: " + deleteResponse.getBody().asString());
        }
    }
}

