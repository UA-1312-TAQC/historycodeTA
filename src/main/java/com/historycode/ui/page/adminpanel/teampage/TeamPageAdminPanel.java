package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamPageAdminPanel extends BasePageAdminPanel {

    @FindBy(xpath = "//button[span[text()='Створити нового члена команди']]")
    WebElement addNewMemberButton;
    TeamPageGridComponent teamPageGridComponent;

    public TeamPageAdminPanel(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        teamPageGridComponent = new TeamPageGridComponent(driver, driver.findElement(By.className("team-page-container")));
    }

    public void clickAddNewMemberButton() {
        addNewMemberButton.click();
    }

    public TeamPageGridComponent getTeamPageGridComponent() {
        return teamPageGridComponent;
    }
}
