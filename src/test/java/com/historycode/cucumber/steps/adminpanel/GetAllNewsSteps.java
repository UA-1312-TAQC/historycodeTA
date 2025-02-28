package com.historycode.cucumber.steps.adminpanel;

import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.GetAllNewsResponse;
import com.historycode.api.models.adminPanel.news.ImageDetails;
import com.historycode.api.models.adminPanel.news.News;
import com.historycode.api.models.adminPanel.news.NewsImage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class GetAllNewsSteps extends NewsPageSteps {
    private NewsClient newsClient;
    private Response response;

    @Given("I input a valid endpoint")
    public void initAPI() {
        this.newsClient = new NewsClient(provider.getBaseAPIUrl());
        this.newsClient.setToken(provider.getAccessToken());
    }

    @When("Get all News")
    public void getAllNews() {
        response = newsClient.getAll();
        System.out.println("Received news response: " + response.asString());
    }

    @Then("the response status code should be 200")
    public void verifyStatus200() {
        Assert.assertNotNull(response, "Response is null, make sure 'Get all News' step is executed before this.");
        Assert.assertEquals(response.statusCode(), 200, "Expected status 200, but got: " + response.statusCode());
    }

    @And("the response body should be in JSON format")
    public void verifyJSONFormat() {
        Assert.assertTrue(response.getContentType().contains("application/json"),
                "Expected JSON response but got: " + response.getContentType());
    }

    @And("the response should contain the following fields:")
    public void validateNewsFields() {
        Assert.assertNotNull(response, "Response is null, ensure 'Get all News' is executed before this.");
        GetAllNewsResponse getAllResponse = response.body().as(GetAllNewsResponse.class);
        SoftAssert softAssert = new SoftAssert();

        for (News news : getAllResponse.getNews()) {
            softAssert.assertNotNull(news.getId(), "Key 'id' is missing in the response for news");
            softAssert.assertNotNull(news.getTitle(), "Key 'title' is missing in the response for news");
            softAssert.assertNotNull(news.getText(), "Key 'text' is missing in the response for news");
            softAssert.assertNotNull(news.getCreationDate(), "Key 'creationDate' is missing in the response for news");
        }
        softAssert.assertAll();
    }

    @And("the image object should contain:")
    public void validateImageFields() {
        Assert.assertNotNull(response, "Response is null, ensure 'Get all News' is executed before this.");
        GetAllNewsResponse getAllResponse = response.body().as(GetAllNewsResponse.class);
        SoftAssert softAssert = new SoftAssert();

        for (News news : getAllResponse.getNews()) {
            NewsImage image = news.getImage();
            if (image != null) {
                softAssert.assertNotNull(image.getId(), "Key 'image.id' is missing in the response for news");
                softAssert.assertNotNull(image.getBlobName(), "Key 'image.blobName' is missing in the response for news");
                softAssert.assertNotNull(image.getBase64(), "Key 'image.base64' is missing in the response for news");
                softAssert.assertNotNull(image.getMimeType(), "Key 'image.mimeType' is missing in the response for news");
            }
        }
        softAssert.assertAll();
    }

    @And("the imageDetails object should contain:")
    public void validateImageDetailsFields() {
        Assert.assertNotNull(response, "Response is null, ensure 'Get all News' is executed before this.");
        GetAllNewsResponse getAllResponse = response.body().as(GetAllNewsResponse.class);
        SoftAssert softAssert = new SoftAssert();

        for (News news : getAllResponse.getNews()) {
            NewsImage image = news.getImage();
            if (image != null && image.getImageDetails() != null) {
                ImageDetails imageDetails = image.getImageDetails();
                softAssert.assertNotNull(imageDetails.getId(), "Key 'imageDetails.id' is missing in the response for news");
                softAssert.assertNotNull(imageDetails.getTitle(), "Key 'imageDetails.title' is missing in the response for news");
                softAssert.assertNotNull(imageDetails.getAlt(), "Key 'imageDetails.alt' is missing in the response for news");
                softAssert.assertNotNull(imageDetails.getImageId(), "Key 'imageDetails.imageId' is missing in the response for news");
            }
        }
        softAssert.assertAll();
    }
}
