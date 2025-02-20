package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class CategoriesRowComponent extends RowComponent {

    @Getter
    @FindBy(xpath = "./td[@class='ant-table-cell'][3]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    private WebElement deleteAction;
    @Getter
    @FindBy(xpath = ".//td[@class='ant-table-cell'][3]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    private WebElement editAction;
    @Getter
    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//img")
    private WebElement picture;

    public CategoriesRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Check Categories Row is Displayed.")
    public boolean isExist() {
        return getTitle().isDisplayed() && picture.isDisplayed() && editAction.isDisplayed() &&  deleteAction.isDisplayed();
    }

    public void clickEdit() {
        actions.moveToElement(editAction).pause(Duration.ofMillis(300)).click().perform();
    }

    public void clickDelete() {
        actions.moveToElement(deleteAction).pause(Duration.ofMillis(300)).click().perform();
    }
}
