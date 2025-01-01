package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoriesRowComponent extends RowComponent {
    private static final String PICTURE_XPATH = "//td[@class='ant-table-cell'][2]//img";
    private static final String DELETE_ACTION_XPATH = ".//td[@class='ant-table-cell'][3]//span[contains(@class, 'anticon-delete')]//*[name()='svg']";  // Relative to rootElement
    private static final String EDIT_ACTION_XPATH = ".//td[@class='ant-table-cell'][3]//span[contains(@class, 'anticon-edit')]//*[name()='svg']";  // Relative to rootElement

    private final WebElement deleteAction;
    private final WebElement editAction;
    private final WebElement picture;

    public CategoriesRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.picture = driver.findElement(By.xpath(PICTURE_XPATH));
        this.deleteAction = rootElement.findElement(By.xpath(DELETE_ACTION_XPATH));
        this.editAction = rootElement.findElement(By.xpath(EDIT_ACTION_XPATH));
    }

    public void clickEdit() {
        editAction.click();
    }

    public void clickDelete() {
        deleteAction.click();
    }
    //TODO Create method to get row picture
    //TODO Create method to click picture
}
