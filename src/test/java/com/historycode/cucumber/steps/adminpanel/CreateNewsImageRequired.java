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
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;

@Getter
public class CreateNewsImageRequired extends BaseStep {

    private final SoftAssert softAssert = new SoftAssert();

    private NewsPageAdminPanel pageAdminPanel;
    private HistoryCodesAdminPanelPage historyCodesAdminPanelPage;
    private CreateEditNewsModal createEditNewsModal;

    private final String newsTitle = "Ukrainian Modern";
    private final String newsLink = "ukrainian-modern";
    private final String newsText = "Ukrainian Modern is a fusion of traditional culture and innovation, where art, architecture, and technology reflect the spirit of an independent and progressive Ukraine.";
    private final String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /*
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

     */

    //@And("I click on the Створити новину button")
    public void clickOnTheCreateNewsButton() {
        createEditNewsModal = new NewsPageAdminPanel(driver).clickAddNewInfo();
    }

    @And("I create the news with {string} to {string}")
    public void fillNewsDetails() {
        createEditNewsModal.inputNewsTitle(newsTitle);
        createEditNewsModal.inputNewsLinkTranslit(newsLink);
        createEditNewsModal.inputNewsTextEditor(newsText);
        createEditNewsModal.clickUploadNewsPhoto(null);
        try {
            Date parsedDate = dateFormat.parse(currentDate);
            createEditNewsModal.inputNewsCreationDate(parsedDate);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + currentDate, e);
        }
    }
    /*
    @And("I save the news")
    public void saveNews() {
        createEditNewsModal.saveNews();
    }

    @Then("The news item should be error")
    public void verifyNewsCreated() {
        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(createEditNewsModal.getErrorMessage()));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message was not displayed.");
        Assert.assertEquals(errorMessage.getText(), "Будь ласка, заповніть всі обов'язкові поля правильно", "Unexpected error message text.");

    }

     */

}
