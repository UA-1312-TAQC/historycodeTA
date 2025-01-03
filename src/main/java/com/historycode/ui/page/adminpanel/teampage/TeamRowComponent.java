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
    protected WebElement lastFirstName;
    @FindBy(xpath = "//td[2]//div[@class='team-table-item-name']//p")
    protected WebElement position;
    @FindBy(xpath = "//td[3]//div[@class='team-table-item-name']//p")
    protected WebElement description;
    @FindBy(xpath = "//td[4]//img")
    protected WebElement photo;
    @FindBy(xpath = "//td[5]//a")
    protected List<WebElement> socialMediaElements;
    @FindBy(xpath = "//td[6]//span[contains(@class, 'delete')]")
    protected WebElement deleteAction;
    @FindBy(xpath = "//td[6]//span[contains(@class, 'edit')]")
    protected WebElement editAction;

    private List<TeamSocialMediaComponent> socialMediaLinks;

    public TeamRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    public String getLastFirstName() {
        return lastFirstName.getText();
    }

    public String getPosition() {
        return position.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public String getPhoto() {
        return photo.getDomAttribute("src");
    }

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
        //return new DeleteItemModal(driver, rootElement);
    }

    public void clickEdit() {
        editAction.click();
    }
}
