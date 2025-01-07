package com.historycode.ui.page.privacyPolicyPage;

import com.historycode.ui.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PrivacyPolicyPage extends BasePage {
    
    @FindBy(xpath="//main")
    private WebElement sectionRootElement;
    @FindBy(xpath = "//div[@class='titleContainer']")
    private WebElement titleRootElement;

    private SectionListComponent sectionComponent;
    private TitleContainerComponent titleComponent;

    public PrivacyPolicyPage(WebDriver driver) {
        super(driver);
        this.sectionComponent = new SectionListComponent(driver, sectionRootElement);
        this.titleComponent = new TitleContainerComponent(driver, titleRootElement);
    }
    
    public List<String> getSectionsName() {
        return sectionComponent.SectionsName();
    }
    public List<String> getSectionsText() {
        return sectionComponent.SectionsText();
    }
    public List<String> getSectionsLinks() {
        return sectionComponent.SectionsLinks();
    }
    public String getTitleName() {
        return titleComponent.titleName();
    }
    public String getSubTitle() {
        return titleComponent.subTitle();
    }
    public String getDisclaimer() {
        return titleComponent.disclaimer();
    }


}




