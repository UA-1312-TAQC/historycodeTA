package com.historycode.ui.page.privacyPolicyPage;

import com.historycode.ui.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PrivacyPolicyPage extends BasePage {

    SectionListComponent sectionComponent;
    TitleContainerComponent title;

    public PrivacyPolicyPage(WebDriver driver) {
        super(driver);
        WebElement sectionRootElement = driver.findElement(By.xpath("//main"));
        WebElement titleRootElement = driver.findElement(By.xpath("//div[@class='titleContainer']"));
        sectionComponent = new SectionListComponent(driver, sectionRootElement);
        title = new TitleContainerComponent(driver, titleRootElement);
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
        return title.titleName();
    }
    public String getSubTitle() {
        return title.subTitle();
    }
    public String getDisclaimer() {
        return title.disclaimer();
    }


}




