package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamSocialMediaComponent extends BaseComponent {

    @FindBy(xpath = "//td[@class='ant-table-cell']//div[@class='team-links']//a")
    private WebElement link;

    public TeamSocialMediaComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    //Extracts the URL directly from the href attribute
    public String getUrl() {
        return link.getAttribute("href");
    }

    public void clickLink() {
        link.click();
    }

    //TODO Do I need to specify these url: what if we have any other different urls except for the specified ones (Wikipedia, Medium, Git...)??
    //Infers the platform based on the URL
    public String getPlatform() {
        String href = getUrl().toLowerCase();
        if (href.contains("instagram")) {
            return "Instagram";
        } else if (href.contains("behance")) {
            return "Behance";
        } else if (href.contains("facebook")) {
            return "Facebook";
        } else if (href.contains("linkedin")) {
            return "LinkedIn";
        } else {
            return null;
        }
    }
}
