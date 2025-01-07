package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.page.adminpanel.editorpage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoriesRowComponent extends RowComponent {
    private static final String PICTURE_XPATH = ".//td[@class='ant-table-cell'][2]//img";
    private static final String DELETE_ACTION_XPATH = "./td[@class='ant-table-cell'][3]//span[contains(@class, 'anticon-delete')]//*[name()='svg']";  // Relative to rootElement
    private static final String EDIT_ACTION_XPATH = ".//td[@class='ant-table-cell'][3]//span[contains(@class, 'anticon-edit')]//*[name()='svg']";  // Relative to rootElement

    @FindBy(xpath = DELETE_ACTION_XPATH)
    private WebElement deleteAction;
    @FindBy(xpath = EDIT_ACTION_XPATH)
    private WebElement editAction;
    @FindBy(xpath = PICTURE_XPATH)
    private WebElement picture;

    public CategoriesRowComponent(WebDriver driver, WebElement rootElement) {
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
    //TODO Create method to get row picture
    //TODO Create method to click picture
}
