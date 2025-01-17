package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagsRowComponent extends RowComponent {

    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    private WebElement deleteAction;
    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    private WebElement editAction;
    @FindBy(xpath = "(//div[contains(@class, 'ant-modal-content')])[2]")
    private WebElement deleteModal;

    public TagsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Check Tags Row is Displayed.")
    public boolean isExist() {
        return getTitle().isDisplayed() && editAction.isDisplayed() &&  deleteAction.isDisplayed();
    }

    public void clickEdit() {
        scrollToElement(editAction);
        editAction.click();
    }

    public DeleteItemModal clickDelete() {
        scrollToElement(deleteAction);
        deleteAction.click();
        return new DeleteItemModal(driver, deleteModal);
    }

    public WebElement getEditAction(){
        return editAction;
    }

    public WebElement getDeleteAction() {
        return deleteAction;
    }
}
