package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TeamCardComponent extends BaseComponent {

    @FindBy(css = ".teamImg")
    private WebElement photo;

    @FindBy(css = ".teamItemTitle")
    private WebElement name;

    @FindBy(css = ".teamItemDescription")
    private WebElement position;

    @FindBy(css = ".teamLinkItems a")
    private List<WebElement> socialLinks;

    public TeamCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getPhotoUrl() {
            wait.until(driver -> photo.getAttribute("src") != null);
            return photo.getAttribute("src");

    }
    public String getMemberName() {
        return (name != null) ? name.getText().trim() : "";
    }

    public String getMemberPosition() {
        return (position != null) ? position.getText().trim() : "";
    }

    public List<String> getSocialLinks() {
        return socialLinks.stream()
                .map(link -> link.getAttribute("href"))
                .collect(Collectors.toList());
    }


}
