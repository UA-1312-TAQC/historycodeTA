package com.historycode.cucumber.steps.adminpanel;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;

@Getter
public class CreateTheSameNewsSteps extends BaseStep {
    private final SoftAssert softAssert = new SoftAssert();

    private NewsPageAdminPanel pageAdminPanel;
    private HistoryCodesAdminPanelPage historyCodesAdminPanelPage;
    private CreateEditNewsModal createEditNewsModal;

    private final String newsTitle = "Тестова новина";
    private final String newsLink = "test-link";
    private final String newsText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et.";
    private final String imagePath = "src/test/resources/test-image.jpg";
    private final String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Given("User open the admin-panel page of the site and login admin")
    public void loginWithAdmin() {
        initDriver();
        driver.get(provider.getBaseUIUrl());
        setAccessToken();
        driver.get(provider.getBaseUIUrl() + "/admin-panel");
        historyCodesAdminPanelPage = new HistoryCodesAdminPanelPage(driver);
    }

    @When("I navigate to the {string} tab")
    public void navigateToNews(String name) {
        AdminMenuBarComponent adminMenuBar = new BasePageAdminPanel(driver).getAdminMenuBar();
        switch (name) {
            case "History-коди" -> historyCodesAdminPanelPage = adminMenuBar.goToHistoryCodesPage();
            case "Новини" -> pageAdminPanel = adminMenuBar.goToNewsPage();
        }
        sleep(1);
    }

    @And("I click on the Створити новину button")
    public void clickOnTheCreateNewsButton() {
        createEditNewsModal = new NewsPageAdminPanel(driver).clickAddNewInfo();
    }

    @And("I am writing a new {} with {string}")
    public void fillNewsDetails() {
        createEditNewsModal.inputNewsTitle(newsTitle);
        createEditNewsModal.inputNewsLinkTranslit(newsLink);
        createEditNewsModal.inputNewsTextEditor(newsText);
        createEditNewsModal.clickUploadNewsPhoto(imagePath);
        try {
            Date parsedDate = dateFormat.parse(currentDate);
            createEditNewsModal.inputNewsCreationDate(parsedDate);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + currentDate, e);
        }
    }

    @And("I save the news")
    public void saveNews() {
        createEditNewsModal.saveNews();
    }

    @Then("The news item should be successfully created")
    public void verifyNewsCreated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOf(createEditNewsModal.getSuccessMessage()));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message was not displayed.");
        Assert.assertEquals(successMessage.getText(), "Новину успішно додано/оновлено!",
                "Unexpected success message text.");
    }


    @And("I try to {string} create the same {string} again")
    public void createDuplicateNews() {
        clickOnTheCreateNewsButton();
        fillNewsDetails();
        saveNews();
    }

    @Then("I should see the error notification")
    public void iShouldSeeTheErrorNotification(String field, String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement errorElement = switch (field) {
            case "Title" -> wait.until(ExpectedConditions.visibilityOf(createEditNewsModal.getSimilarTitleError()));
            case "Link" -> wait.until(ExpectedConditions.visibilityOf(createEditNewsModal.getSimilarUrlError()));
            default -> throw new IllegalArgumentException("Unknown field: " + field);
        };

        String actualMessage = errorElement.getText();

        softAssert.assertEquals(actualMessage, expectedMessage, "Validation message for field: " + field);
        softAssert.assertAll();
    }
}

