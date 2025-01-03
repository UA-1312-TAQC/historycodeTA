package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamSocialMediaComponent extends BaseComponent {

    @FindBy(xpath = "//td[@class='ant-table-cell']//div[@class='team-links']//a")
    protected WebElement link;

    //TODO It doesn't see such xpath in the inspect window
    @FindBy(xpath = "//td[@class='ant-table-cell']//div[@class='team-links']//a//svg//path")
    protected WebElement icon;

    public TeamSocialMediaComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    //Extracts the URL directly from the href attribute
    public String getUrl() {
        if (link == null) {
            throw new IllegalStateException("Link element is not initialized or missing.");
        }
        return link.getDomAttribute("href");
    }

    //TODO Is it correct?
    public String getIcon() {
        return icon.getDomAttribute("d");
    }

    public void clickLink() {
        link.click();
    }

}
