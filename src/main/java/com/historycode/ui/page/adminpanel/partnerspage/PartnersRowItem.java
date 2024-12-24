package com.historycode.ui.page.adminpanel.partnerspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.historycode.ui.component.BaseComponent;

public class PartnersRowItem extends BaseComponent {

    private SocialNetwork socialNetwork;
    private ItemActions itemActions;

    public PartnersRowItem(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.socialNetwork = new SocialNetwork(driver);
        this.itemActions = new ItemActions(driver);

    }
}