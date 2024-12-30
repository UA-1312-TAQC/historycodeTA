package com.historycode.ui.page.adminpanel.partnerspage;

import java.util.ArrayList;
import java.util.List;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersRowComponent extends BaseComponent {
    @FindBy(xpath = "")
    WebElement name;
    @FindBy(xpath = "")
    WebElement link;
    @FindBy(xpath = "")
    WebElement logo;
    @FindBy(xpath = "")
    List<PartnersSocialMediaComponent> socialMediaComponents = new ArrayList<>();
    @FindBy(xpath = "")
    WebElement actionDelete;
    @FindBy(xpath = "")
    WebElement actionEdit;

    public PartnersRowComponent(WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
    }
}
