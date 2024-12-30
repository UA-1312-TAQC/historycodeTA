package com.historycode.ui.page.adminpanel.jobspage.modal;

import com.historycode.ui.component.BaseModal;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class DeleteJobModal extends BaseModal {
    @FindBy(xpath = "//div[@class='ant-modal-header']//div[@class='ant-modal-title']")
    public WebElement title;
    @FindBy(xpath = "//div[@class='ant-modal-body']/p")
    public WebElement confirmationText;
    @FindBy(xpath = "//div[@class='ant-modal-footer']//button[contains(text(),'Cancel')]")
    public WebElement cancel;
    @FindBy(xpath = "//div[@class='ant-modal-footer']//button[contains(text(),'OK')]")
    public WebElement ok;

    public DeleteJobModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickOkButton() {
        ok.click();
    }

    public void clickCancelButton() {
        cancel.click();
    }
}
