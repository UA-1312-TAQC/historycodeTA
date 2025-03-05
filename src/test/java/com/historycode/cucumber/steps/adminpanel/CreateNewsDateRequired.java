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

@Getter
public class CreateNewsDateRequired extends BaseStep {

    private final SoftAssert softAssert = new SoftAssert();

    private NewsPageAdminPanel pageAdminPanel;
    private HistoryCodesAdminPanelPage historyCodesAdminPanelPage;
    private CreateEditNewsModal createEditNewsModal;

    private final String newsTitle = "Тестова новина";
    private final String newsLink = "test-link";
    private final String newsText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et.";
    private final String imagePath = "src/test/resources/test-image.jpg";
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

    @And("I click on the Створити новину button")
    public void clickOnTheCreateNewsButton() {
        createEditNewsModal = new NewsPageAdminPanel(driver).clickAddNewInfo();
    }

    @And("I will verify the {string} to {string}")
    public void fillNewsDetails() {
        createEditNewsModal.inputNewsTitle(newsTitle);
        createEditNewsModal.inputNewsLinkTranslit(newsLink);
        createEditNewsModal.inputNewsTextEditor(newsText);
        createEditNewsModal.clickUploadNewsPhoto(imagePath);
        createEditNewsModal.inputNewsCreationDate(null);

    }

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

}
