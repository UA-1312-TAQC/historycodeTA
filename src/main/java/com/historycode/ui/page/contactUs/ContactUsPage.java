package com.historycode.ui.page.contactUs;

import com.historycode.ui.component.footer.FooterComponent;
import com.historycode.ui.component.header.HeaderComponent;
import com.historycode.ui.page.contactUs.component.ContactUsContactBlockComponent;
import com.historycode.ui.page.contactUs.component.ContactUsContentComponent;
import com.historycode.ui.page.contactUs.component.ContactUsFormComponent;
import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'formWrapper')]")
    private WebElement contactUsFormNode;
    @FindBy(xpath = "//div[contains(@class, 'contactBlock')]")
    private WebElement contactUsContactBlockNode;
    @FindBy(xpath = "//div[contains(@class, 'contactUsContent')]")
    private WebElement contactUsContentNode;

    @Getter
    private ContactUsFormComponent contactUsFormComponent;
    @Getter
    private ContactUsContactBlockComponent contactUsContactBlockComponent;
    @Getter
    private ContactUsContentComponent contactUsContentComponent;

    public ContactUsPage(WebDriver driver) {
        super(driver);

        this.contactUsFormComponent = new ContactUsFormComponent(driver, contactUsFormNode);
        this.contactUsContactBlockComponent = new ContactUsContactBlockComponent(driver, contactUsContactBlockNode);
        this.contactUsContentComponent = new ContactUsContentComponent(driver, contactUsContentNode);
    }

}
