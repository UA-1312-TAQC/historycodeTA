package com.historycode.ui.page.adminpanel.editorpage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PositionsRowComponent extends BaseComponent {
    @FindBy(xpath = "//td[@class='ant-table-cell'][1]//div")
    WebElement title;
    @FindBy(xpath = "//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    WebElement deleteAction;
    @FindBy(xpath = "//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    WebElement editAction;

    public PositionsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
