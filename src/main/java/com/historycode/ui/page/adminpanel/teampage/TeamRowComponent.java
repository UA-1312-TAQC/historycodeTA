package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TeamRowComponent extends BaseComponent {
    @FindBy(xpath = "//td[1]//div[@class='team-table-item-name']//p")
    WebElement lastFirstName;
    @FindBy(xpath = "//td[2]//div[@class='team-table-item-name']//p")
    WebElement position;
    @FindBy(xpath = "//td[3]//div[@class='team-table-item-name']//p")
    WebElement description;
    @FindBy(xpath = "//td[4]//img")
    WebElement photo;
    @FindBy(xpath = "//td[5]//a")
    List<WebElement> socialMediaElements;
    @FindBy(xpath = "//td[6]//span[contains(@class, 'delete')]")
    WebElement deleteAction;
    @FindBy(xpath = "//td[6]//span[contains(@class, 'edit')]")
    WebElement editAction;

    public TeamRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    public WebElement getLastFirstName() {
        return lastFirstName;
    }

    public WebElement getPosition() {
        return position;
    }

    public WebElement getDescription() {
        return description;
    }

    public WebElement getPhoto() {
        return photo;
    }
    //TODO Which method is better and more needful for the photo WebElement: the above one or the bottom one??
    public boolean hasPhoto() {
        return photo.isDisplayed();
    }

    //TODO Is it the right approach for the getSocialMediaLinks method??
    //List<WebElement> socialMediaElements; or List<TeamSocialMediaComponent> socialMediaElements;
    private List<TeamSocialMediaComponent> socialMediaLinks;
    public List<TeamSocialMediaComponent> getSocialMediaLinks() {
        if (socialMediaLinks == null) {
            socialMediaLinks = new ArrayList<>();
            for (WebElement element : socialMediaElements) {
                socialMediaLinks.add(new TeamSocialMediaComponent(driver, element));
            }
        }
        return socialMediaLinks;
    }

    public void clickDelete() {
        deleteAction.click();
    }
    public void clickEdit() {
        editAction.click();
    }
}
