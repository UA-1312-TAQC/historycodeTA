package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.editModal.EditMemberModal;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalTime;

public class TeamPageAdminPanel extends BasePageAdminPanel {

    @Getter
    protected TeamPageGridComponent teamPageGridComponent;
    @FindBy(xpath = "//div[@class = 'team-page-container']")
    protected WebElement gridRootElement;
    @FindBy(xpath = "//button[span[text()='Створити нового члена команди']]")
    protected WebElement addNewMemberButton;
    @FindBy(xpath = "//h2[starts-with(text(),'Додати')]/ancestor::div[@class = 'ant-modal-content']")
    protected WebElement createEditModalNode;

    public TeamPageAdminPanel(WebDriver driver) {
        super(driver);
    }

    public TeamPageGridComponent getTeamPageGridComponent(){
        if(teamPageGridComponent == null){
            teamPageGridComponent = new TeamPageGridComponent(driver, gridRootElement);
        }
        return teamPageGridComponent;
    }

    @Step("Click on the last page of the pagination")
    public TeamPageAdminPanel clickLastPaginationItem(){
        this.getTeamPageGridComponent().clickLastPage();
        return new TeamPageAdminPanel(driver);
    }

    @Step("Click the 'Створити нового члена команди' button")
    public EditMemberModal clickAddNewMemberButton() {
        addNewMemberButton.click();
        waitUntilElementVisible(createEditModalNode);
        return new EditMemberModal(driver, createEditModalNode);
    }

    public EditMemberModal editMemberByIndex(int index) {
        if (index < 0 || index >= teamPageGridComponent.getRowsCount()){
            throw new IllegalArgumentException("Member with index" + index + " is not available or invalid");
        }
        TeamRowComponent teamMemberToEdit = teamPageGridComponent.getRowById(index);
        return teamMemberToEdit.clickEdit();
    }

    public DeleteItemModal deleteMemberByIndex(int index) {
        if (index < 0 || index >= teamPageGridComponent.getRowsCount()){
            throw new IllegalArgumentException("Member with index" + index + " is not available or invalid");
        }
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
