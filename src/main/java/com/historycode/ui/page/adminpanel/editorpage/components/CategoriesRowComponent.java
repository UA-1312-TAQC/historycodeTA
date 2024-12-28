package com.historycode.ui.page.adminpanel.editorpage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoriesRowComponent extends BaseComponent {
    @FindBy(xpath = "//td[@class='ant-table-cell'][1]//div")
    WebElement title;
    @FindBy(xpath = "//td[@class='ant-table-cell'][2]//img")
    WebElement picture;
    @FindBy(xpath = "//td[@class='ant-table-cell'][3]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    WebElement deleteAction;
    @FindBy(xpath = "//td[@class='ant-table-cell'][3]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    WebElement editAction;

    public CategoriesRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
