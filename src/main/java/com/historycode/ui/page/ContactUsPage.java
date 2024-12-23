package com.historycode.ui.page;

import com.historycode.ui.component.contact_us.ContactUsContactBlockComponent;
import com.historycode.ui.component.contact_us.ContactUsContentComponent;
import com.historycode.ui.component.contact_us.ContactUsFormComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ContactUsPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div/div/div")
    private WebElement rootElement;

    private ContactUsFormComponent contactUsFormComponent;
    private ContactUsContactBlockComponent contactUsContactBlockComponent;
    private ContactUsContentComponent contactUsContentComponent;

    public ContactUsPage(WebDriver driver) {
        super(driver);

        this.contactUsFormComponent = new ContactUsFormComponent(driver, rootElement);
        this.contactUsContactBlockComponent = new ContactUsContactBlockComponent(driver, rootElement);
        this.contactUsContentComponent = new ContactUsContentComponent(driver, rootElement);
    }
}
