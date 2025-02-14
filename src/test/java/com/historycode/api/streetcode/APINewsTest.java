package com.historycode.api.streetcode;

import com.historycode.api.clients.NewsClient;

import com.historycode.api.models.adminPanel.news.GetAllNewsResponse;
import com.historycode.api.models.adminPanel.news.ImageDetails;
import com.historycode.api.models.adminPanel.news.News;
import com.historycode.api.models.adminPanel.news.NewsImage;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class APINewsTest extends ApiTestRunner {
    private NewsClient newsClient;

    @BeforeClass
    public void init() {
        newsClient = new NewsClient(testValueProvider.getBaseAPIUrl());
    }

    @Issue("190")
    @Test
    @Description("Verify if all news are displayed using GET method.")
    public void testGetAllNews() {
        Response response = newsClient.getAll();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), 200, "Response status is not 200 OK");
        GetAllNewsResponse getAllResponse = response.body().as(GetAllNewsResponse.class);
        softAssert.assertTrue(getAllResponse.getTotalAmount() > 0, "Total amount of news is 0");

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

        System.out.println("Parsed Response: " + getAllResponse);

        softAssert.assertAll();
    }
}
