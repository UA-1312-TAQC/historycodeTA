package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.page.adminpanel.editorpage.components.modals.ContextsModalComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsRowComponent extends RowComponent {
    @FindBy(xpath = "./td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    private WebElement deleteAction;

    @FindBy(xpath = "./td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    private WebElement editAction;

    public ContextsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public ContextsModalComponent clickEdit() {
        editAction.click();
        WebElement modalContentRoot = driver.findElement(By.xpath("//div[@class='ant-modal-content']"));
        return new ContextsModalComponent(driver, modalContentRoot);
    }

    public DeleteItemModal clickDelete() {
        deleteAction.click();
        WebElement modalContentRoot = driver.findElement(By.xpath("//div[@class='ant-modal-content']"));
        return new DeleteItemModal(driver, modalContentRoot);
    }
}

