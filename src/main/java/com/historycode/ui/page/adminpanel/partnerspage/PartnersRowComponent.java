package com.historycode.ui.page.adminpanel.partnerspage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseRowComponent;

public class PartnersRowComponent extends BaseRowComponent {

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

    public PartnersRowComponent(WebDriver driver, WebElement root){
        super(driver, root);
    }
}
