package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class PartnerPopupComponent extends BaseComponent {

    @FindBy(css = ".ant-popover-inner .partner-details-title")
    private WebElement titleElement;

    @FindBy(css = ".ant-popover-inner .partner-details-link")
    private WebElement linkElement;

    @FindBy(css = ".ant-popover-inner .partner-details-text")
    private WebElement descriptionElement;

    public PartnerPopupComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public String getTitle() {
        return titleElement.getText().trim();
    }

    public String getLinkHref() {
        return linkElement.getAttribute("href");
    }

    public String getDescription() {
        return descriptionElement.getText().trim();
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }
}
