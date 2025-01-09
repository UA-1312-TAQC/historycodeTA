package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.page.adminpanel.editorpage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsRowComponent extends RowComponent {
    private static final String DELETE_ACTION_XPATH = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-delete')]//*[name()='svg']";  // Relative to rootElement
    private static final String EDIT_ACTION_XPATH = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-edit')]//*[name()='svg']";  // Relative to rootElement

    @FindBy(xpath = DELETE_ACTION_XPATH)
    private WebElement deleteAction;
    @FindBy(xpath = EDIT_ACTION_XPATH)
    private WebElement editAction;

    public ContextsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickEdit() {
        BasePage.moveToElement(driver, editAction);
        editAction.click();
    }

    public void clickDelete() {
        BasePage.moveToElement(driver, editAction);
        deleteAction.click();
    }

    public WebElement getEditAction(){
        return editAction;
    }

    public WebElement getDeleteAction() {
        return deleteAction;
    }
}
