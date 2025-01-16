package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.editModal.EditMemberModal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TeamPageAdminPanel extends BasePageAdminPanel {

    @FindBy(xpath = "//button[span[text()='Створити нового члена команди']]")
    protected WebElement addNewMemberButton;
    @Getter
    protected TeamPageGridComponent teamPageGridComponent;

    @FindBy(xpath="//h2[starts-with(text(),'Додати')]/ancestor::div[@class = 'ant-modal-content']")
    private WebElement modalRootElement;
    public TeamPageAdminPanel(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        teamPageGridComponent = new TeamPageGridComponent(driver, driver.findElement(By.className("team-page-container")));
    }

    public EditMemberModal clickAddNewMemberButton() {
        addNewMemberButton.click();
        return new EditMemberModal(driver, modalRootElement);
    }

    public TeamPageAdminPanel clickLastPaginationItem(){
        this.getTeamPageGridComponent().clickLastPage();
        return new TeamPageAdminPanel(driver);
    }

    //TODO What wrapper methods to implement here?
}
