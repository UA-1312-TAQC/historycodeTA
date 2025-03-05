package com.historycode.cucumber.steps.adminpanel;

import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.GetAllNewsResponse;
import com.historycode.api.models.adminPanel.news.ImageDetails;
import com.historycode.api.models.adminPanel.news.News;
import com.historycode.api.models.adminPanel.news.NewsImage;
import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsRowComponent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import jdk.jfr.Description;
import lombok.Getter;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import io.cucumber.java.en.*;
import lombok.Getter;
import org.testng.Assert;
import java.util.List;

@Getter
public class GetAllNewsSteps extends BaseStep {

    private HistoryCodesAdminPanelPage historyCodesAdminPanelPage;
    private NewsPageAdminPanel pageAdminPanel;
    private List<NewsRowComponent> newsRowComponents;
    /*
    @Given("User open the admin-panel page of the site and login admin")
    public void loginAdmin() {
        initDriver();
        driver.get(provider.getBaseUIUrl());
        setAccessToken();
        driver.get(provider.getBaseUIUrl() + "/admin-panel");
        historyCodesAdminPanelPage = new HistoryCodesAdminPanelPage(driver);
    }

    @When("Navigate to the News")
    public void navigateToNews(String name) {
        AdminMenuBarComponent adminMenuBar = new BasePageAdminPanel(driver).getAdminMenuBar();
        switch (name) {
            case "History-коди" -> historyCodesAdminPanelPage = adminMenuBar.goToHistoryCodesPage();
            case "Новини" -> pageAdminPanel = adminMenuBar.goToNewsPage();
        }
        sleep(1);
    }

     */

    @Then("I Get all News")
    public void allGetNews() {
        newsRowComponents = pageAdminPanel.getNewsPageGridComponent().updateNewsRows(driver);
        Assert.assertFalse(newsRowComponents.isEmpty(), "No news found!");
        System.out.println("Total news found: " + newsRowComponents.size());
    }

    @And("I click on the first News")
    public void clickOnNewsByIndex(int index) {
        Assert.assertFalse(newsRowComponents.isEmpty(), "No news to click on!");
        pageAdminPanel.getNewsPageGridComponent().clickOnNewsByIndex(index);
    }

    @And("the News should contain the information:")
    public void validateNews() {
        Assert.assertFalse(newsRowComponents.isEmpty(), "No news available for validation!");

        System.out.println("\n==================== ALL NEWS DETAILS ====================");

        for (NewsRowComponent news : newsRowComponents) {
            String title = news.getName().getText();
            String text = news.getText().getText();
            String image = news.getPicture().getAttribute("src");
            String date = news.getDateOfCreation().getText();

            // Логування кожної новини
            System.out.println("\n--- News Details ---");
            System.out.println(" Title: " + title);
            System.out.println(" Text: " + text);
            System.out.println(" Image URL: " + image);
            System.out.println(" Creation Date: " + date);

        }
    }
}

//
//@Description("Verify if all news are displayed using GET method #190")
//@Getter
//public class GetAllNewsSteps extends BaseStep {
//
//    private HistoryCodesAdminPanelPage historyCodesAdminPanelPage;
//
//    private NewsPageAdminPanel pageAdminPanel;
//
//    private List<NewsRowComponent> newsRowComponents;
//
//
//    @Given("User open the admin-panel page of the site and login admin")
//    public void loginAdmin() {
//        initDriver();
//        driver.get(provider.getBaseUIUrl());
//        setAccessToken();
//        driver.get(provider.getBaseUIUrl() + "/admin-panel");
//        historyCodesAdminPanelPage = new HistoryCodesAdminPanelPage(driver);
//    }
//
//    @When("Navigate to the News")
//    public void navigateToNews(String name) {
//        AdminMenuBarComponent adminMenuBar = new BasePageAdminPanel(driver).getAdminMenuBar();
//        switch (name) {
//            case "History-коди" -> historyCodesAdminPanelPage = adminMenuBar.goToHistoryCodesPage();
//            case "Новини" -> pageAdminPanel = adminMenuBar.goToNewsPage();
//        }
//        sleep(1);
//
//    }
//
//    @Then("I Get all News")
//    public void allGetNews() {
//        newsRowComponents = pageAdminPanel.getNewsPageGridComponent().updateNewsRows(driver);
//    }
//
//    @And("I click on th first News")
//    public void clickOnNewsByIndex(int index) {
//        // newsRowComponents = pageAdminPanel.getNewsPageGridComponent().updateNewsRows(driver);
//        pageAdminPanel.getNewsPageGridComponent().clickOnNewsByIndex(0);
//    }
//
//    @And("the News should contain the information:")
//    public void validateNews() {
//        for (NewsRowComponent news : newsRowComponents) {
//            System.out.println("\n--- News Details ---");
//            System.out.println("Title: " + news.getName().getText());
//            System.out.println("Text: " + news.getText().getText());
//            System.out.println("Image: " + news.getPicture().getAttribute("src"));
//            System.out.println("Creation Date: " + news.getDateOfCreation().getText());
//
//        }
//    }
//}

//    private NewsClient newsClient;
//    private Response response;
//
//    @Given("I input a valid endpoint")
//    public void initAPI() {
//        this.newsClient = new NewsClient(provider.getBaseAPIUrl());
//        this.newsClient.setToken(provider.getAccessToken());
//    }
//
//    @When("Get all News")
//    public void getAllNews() {
//        response = newsClient.getAll();
//        System.out.println("Received news response: " + response.asString());
//    }
//
//    @Then("the response status code should be 200")
//    public void verifyStatus200() {
//        Assert.assertNotNull(response, "Response is null, make sure 'Get all News' step is executed before this.");
//        Assert.assertEquals(response.statusCode(), 200, "Expected status 200, but got: " + response.statusCode());
//    }
//
//    @And("the response body should be in JSON format")
//    public void verifyJSONFormat() {
//        Assert.assertTrue(response.getContentType().contains("application/json"),
//                "Expected JSON response but got: " + response.getContentType());
//    }
//
//    @And("the response should contain the following fields:")
//    public void validateNewsFields() {
//        Assert.assertNotNull(response, "Response is null, ensure 'Get all News' is executed before this.");
//        GetAllNewsResponse getAllResponse = response.body().as(GetAllNewsResponse.class);
//        SoftAssert softAssert = new SoftAssert();
//
//        for (News news : getAllResponse.getNews()) {
//            softAssert.assertNotNull(news.getId(), "Key 'id' is missing in the response for news");
//            softAssert.assertNotNull(news.getTitle(), "Key 'title' is missing in the response for news");
//            softAssert.assertNotNull(news.getText(), "Key 'text' is missing in the response for news");
//            softAssert.assertNotNull(news.getCreationDate(), "Key 'creationDate' is missing in the response for news");
//        }
//        softAssert.assertAll();
//    }
//
//    @And("the image object should contain:")
//    public void validateImageFields() {
//        Assert.assertNotNull(response, "Response is null, ensure 'Get all News' is executed before this.");
//        GetAllNewsResponse getAllResponse = response.body().as(GetAllNewsResponse.class);
//        SoftAssert softAssert = new SoftAssert();
//
//        for (News news : getAllResponse.getNews()) {
//            NewsImage image = news.getImage();
//            if (image != null) {
//                softAssert.assertNotNull(image.getId(), "Key 'image.id' is missing in the response for news");
//                softAssert.assertNotNull(image.getBlobName(), "Key 'image.blobName' is missing in the response for news");
//                softAssert.assertNotNull(image.getBase64(), "Key 'image.base64' is missing in the response for news");
//                softAssert.assertNotNull(image.getMimeType(), "Key 'image.mimeType' is missing in the response for news");
//            }
//        }
//        softAssert.assertAll();
//    }
//
//    @And("the imageDetails object should contain:")
//    public void validateImageDetailsFields() {
//        Assert.assertNotNull(response, "Response is null, ensure 'Get all News' is executed before this.");
//        GetAllNewsResponse getAllResponse = response.body().as(GetAllNewsResponse.class);
//        SoftAssert softAssert = new SoftAssert();
//
//        for (News news : getAllResponse.getNews()) {
//            NewsImage image = news.getImage();
//            if (image != null && image.getImageDetails() != null) {
//                ImageDetails imageDetails = image.getImageDetails();
//                softAssert.assertNotNull(imageDetails.getId(), "Key 'imageDetails.id' is missing in the response for news");
//                softAssert.assertNotNull(imageDetails.getTitle(), "Key 'imageDetails.title' is missing in the response for news");
//                softAssert.assertNotNull(imageDetails.getAlt(), "Key 'imageDetails.alt' is missing in the response for news");
//                softAssert.assertNotNull(imageDetails.getImageId(), "Key 'imageDetails.imageId' is missing in the response for news");
//            }
//        }
//        softAssert.assertAll();
//    }
//}
