package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

class PartnersComponent extends BaseComponent {

    @FindBy(css = ".partnersBlockMainContainer .partnerItem")
    private WebElement partnerLogo;

    public PartnersComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public String getLogoSrc() {
        return partnerLogo.getAttribute("src");
    }

    public void hoverOnPartnerLogo() {
        new Actions(driver).moveToElement(partnerLogo).perform();
    }
}
