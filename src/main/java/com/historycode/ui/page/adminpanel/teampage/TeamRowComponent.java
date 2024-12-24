package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseRowComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class TeamRowComponent extends BaseRowComponent {

    @FindBy(xpath = "")
    WebElement lastFirstName;
    @FindBy(xpath = "")
    WebElement position;
    @FindBy(xpath = "")
    WebElement description;
    @FindBy(xpath = "")
    WebElement photo;
    @FindBy(xpath = "")
    List<TeamSocialMediaComponent> socialMediaComponents = new ArrayList<>();
    @FindBy(xpath = "")
    WebElement deleteAction;
    @FindBy(xpath = "")
    WebElement editAction;

    public TeamRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
