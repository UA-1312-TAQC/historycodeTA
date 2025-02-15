package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagsRowComponent extends RowComponent {

    @Getter
    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    private WebElement deleteAction;
    @Getter
    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    private WebElement editAction;

    public TagsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Check Tags Row is Displayed.")
    public boolean isExist() {
        return getTitle().isDisplayed() && editAction.isDisplayed() &&  deleteAction.isDisplayed();
    }

    public void clickEdit() {
        scrollToElement(editAction);
        actions.click().perform();
    }

    public void clickDelete() {
        scrollToElement(deleteAction);
        actions.click().perform();
    }
}
