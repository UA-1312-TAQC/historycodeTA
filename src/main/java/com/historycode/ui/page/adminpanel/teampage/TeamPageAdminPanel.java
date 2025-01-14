package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.editModal.EditMemberModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TeamPageAdminPanel extends BasePageAdminPanel {

    protected TeamPageGridComponent teamPageGridComponent;
    @FindBy(xpath = "//div[@class = 'team-page-container']")
    protected WebElement gridRootElement;
    @FindBy(xpath = "//button[span[text()='Створити нового члена команди']]")
    protected WebElement addNewMemberButton;

    public TeamPageAdminPanel(WebDriver driver) {
        super(driver);
        teamPageGridComponent = new TeamPageGridComponent(driver, gridRootElement);
    }

    public TeamPageGridComponent getTeamPageGridComponent() {
        return teamPageGridComponent;
    }

    public void clickAddNewMemberButton() {
        addNewMemberButton.click();
    }

    public EditMemberModal editMemberByIndex(int index) {
        TeamRowComponent teamMemberToEdit = teamPageGridComponent.getRowById(index);
        return teamMemberToEdit.clickEdit();
    }

    public DeleteItemModal deleteMemberByIndex(int index) {
        TeamRowComponent teamMemberToDelete = teamPageGridComponent.getRowById(index);
        return teamMemberToDelete.clickDelete();
    }

    public TeamPageAdminPanel clickNextPage() {
        teamPageGridComponent.clickNextPage();
        return this;
    }

    public TeamPageAdminPanel clickPrevPage() {
        teamPageGridComponent.clickPrevPage();
        return this;
    }

    public TeamPageAdminPanel clickPaginationItem(int index) {
        teamPageGridComponent.clickPaginationItem(index);
        return this;
    }

    public TeamPageAdminPanel clickNextFivePages() {
        teamPageGridComponent.clickNextFivePages();
        return this;
    }

    public TeamPageAdminPanel clickPrevFivePages() {
        teamPageGridComponent.clickPrevFivePages();
        return this;
    }
}
