package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamSocialMediaComponent extends BaseComponent {

    @FindBy(xpath = "./svg/path")
    protected WebElement icon;

    public TeamSocialMediaComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        return rootElement.getDomAttribute("href");
    }

    public String getIcon() {
        return icon.getDomAttribute("d");
    }

    public void clickLink() {
        rootElement.click();
    }

}
