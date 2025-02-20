package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ContextsRowComponent extends RowComponent {

    @Getter
    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    private WebElement deleteAction;
    @Getter
    @FindBy(xpath = ".//td[@class='ant-table-cell'][2]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    private WebElement editAction;

    public ContextsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Check Contexts Row is Displayed.")
    public boolean isExist() {
        return getTitle().isDisplayed() && editAction.isDisplayed() &&  deleteAction.isDisplayed();
    }

    public void clickEdit() {
        actions.moveToElement(editAction).pause(Duration.ofMillis(300)).click().perform();
    }

    public void clickDelete() {
        actions.moveToElement(deleteAction).pause(Duration.ofMillis(300)).click().perform();
    }
}

//    public ContextsModalComponent clickEdit() {
//        editAction.click();
//        WebElement modalContentRoot = driver.findElement(By.xpath("//div[@class='ant-modal-content']"));
//        return new ContextsModalComponent(driver, modalContentRoot);
//    }
//
//    public DeleteItemModal clickDelete() {
//        deleteAction.click();
//        WebElement modalContentRoot = driver.findElement(By.xpath("//div[@class='ant-modal-content']"));
//        return new DeleteItemModal(driver, modalContentRoot);
//    }

