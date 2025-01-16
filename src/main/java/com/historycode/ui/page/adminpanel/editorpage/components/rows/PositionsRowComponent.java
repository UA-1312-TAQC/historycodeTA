package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.PositionsModalComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PositionsRowComponent extends RowComponent {
    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    private WebElement deleteAction;

    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    private WebElement editAction;

    @FindBy(xpath = "//div[@class='ant-modal-content']")
    private WebElement modalContentRoot;

    @FindBy(xpath = "//p[contains(text(),'видалити')]/ancestor::div[@class = 'ant-modal-content']")
    private WebElement deleteModalRoot;

    public PositionsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Check Positions Row is Displayed.")
    public boolean isExist() {
        return getTitle().isDisplayed() && editAction.isDisplayed() && deleteAction.isDisplayed();
    }

    public PositionsModalComponent clickEdit() {
        scrollToElement(editAction);
        waitUntilElementClickable(editAction);
        editAction.click();
        return new PositionsModalComponent(driver, modalContentRoot);
    }

    public DeleteItemModal clickDelete() {
        scrollToElement(editAction);
        waitUntilElementClickable(deleteAction);
        deleteAction.click();
        return new DeleteItemModal(driver, deleteModalRoot);
    }

    public WebElement getEditAction() {
        return editAction;
    }

    public WebElement getDeleteAction() {
        return deleteAction;
    }
}
