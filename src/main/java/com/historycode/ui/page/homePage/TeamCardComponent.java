package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class TeamCardComponent extends BaseComponent {

    @FindBy(css = ".team-photo")
    private WebElement photo;

    @FindBy(css = ".team-name")
    private WebElement name;

    @FindBy(css = ".team-position")
    private WebElement position;

    @FindBy(css = ".social-links")
    private List<WebElement> socialLinks;

    public TeamCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }


    public String getPhotoUrl() {
        return photo.getAttribute("src");
    }


    public String getMemberName() {
        return name.getText();
    }


    public String getMemberPosition() {
        return position.getText();
    }

    public List<String> getSocialLinks() {
        return socialLinks.stream()
                .map(el -> el.getAttribute("href"))
                .toList();
    }
}
