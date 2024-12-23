package com.historycode.ui.page;

import com.historycode.ui.component.contact_us.ContactUsContactBlockElement;
import com.historycode.ui.component.contact_us.ContactUsContentElement;
import com.historycode.ui.component.contact_us.ContactUsFormElement;
import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ContactUsPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div/div/div")
    private WebElement rootElement;

    private ContactUsFormElement contactUsFormElement;
    private ContactUsContactBlockElement contactUsContactBlockElement;
    private ContactUsContentElement contactUsContentElement;

    public ContactUsPage(WebDriver driver) {
        super(driver);

        this.contactUsFormElement = new ContactUsFormElement(driver, rootElement);
        this.contactUsContactBlockElement = new ContactUsContactBlockElement(driver, rootElement);
        this.contactUsContentElement = new ContactUsContentElement(driver, rootElement);
    }
}
