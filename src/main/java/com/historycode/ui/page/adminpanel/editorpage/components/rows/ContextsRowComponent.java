package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.page.adminpanel.editorpage.EditorBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsRowComponent extends RowComponent {

    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    private WebElement deleteAction;
    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    private WebElement editAction;

    public ContextsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickEdit() {
        scrollToElement(editAction);
        editAction.click();
    }

    public void clickDelete() {
        scrollToElement(editAction);
        deleteAction.click();
    }

    public WebElement getEditAction(){
        return editAction;
    }

    public WebElement getDeleteAction() {
        return deleteAction;
    }
}
