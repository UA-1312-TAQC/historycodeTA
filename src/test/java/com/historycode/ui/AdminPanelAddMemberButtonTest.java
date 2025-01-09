package com.historycode.ui;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AdminPanelAddMemberButtonTest extends TestRunnerWithAdmin {

    private AdminMenuBarComponent adminMenuBarComponent;
    private TeamPageAdminPanel teamPageAdminPanel;
    private final By ADMIN_MENU_ROOT = By.xpath("//div[@class='BarContainer']");
    private final By ADD_BUTTON = By.xpath("//button[span[text()='Створити нового члена команди']]");
    private final By MODAL_WINDOW = By.xpath("//div[contains(@class, 'ant-modal-content')]");

    @BeforeMethod
    public void setUp(){
        teamPageAdminPanel = new TeamPageAdminPanel(driver);
        adminMenuBarComponent = new AdminMenuBarComponent(driver, driver.findElement(ADMIN_MENU_ROOT));
    }

    @Issue("115")
    @Test
    @Description("Verify the ability to create a new team member through the admin panel")
    public void verifyAdminPanelAddMemberButtonTest() {

        goToTeamPage();

        //Wait until the "Створити нового члена команди" button is visible and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_BUTTON));

        adminClickAddNewMember();

        //Wait for the modal to be displayed (ensure visibility)
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_WINDOW));

        Assert.assertTrue(driver.findElement(MODAL_WINDOW).isDisplayed(), "Modal window is not displayed");
    }

    @Step("Go to the 'Команда' page in the left menu")
    private void goToTeamPage(){
        adminMenuBarComponent.goToTeamPage();
    }

    @Step("Click the 'Створити нового члена команди' button")
    private void adminClickAddNewMember(){
        teamPageAdminPanel.clickAddNewMemberButton();
    }

}