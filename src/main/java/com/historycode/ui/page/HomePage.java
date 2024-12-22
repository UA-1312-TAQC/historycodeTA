package com.historycode.ui.page;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.footer.FooterComponent;
import com.historycode.ui.component.header.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseComponent {

    public HomePage(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
        HeaderComponent header = new HeaderComponent(driver, rootElement);
        FooterComponent footer = new FooterComponent(driver, rootElement);
        PersonCardComponent personCard = new PersonCardComponent(driver, rootElement);
        NewsCardComponent newsCard = new NewsCardComponent(driver, rootElement);
        StaticBannerComponent staticBanner = new StaticBannerComponent(driver, rootElement);
        TeamCardComponent teamCard = new TeamCardComponent(driver, rootElement);
        PartnersComponent partners = new PartnersComponent(driver, rootElement);
    }
}

class PersonCardComponent extends BaseComponent {
    public PersonCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class NewsCardComponent extends BaseComponent {
    public NewsCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class StaticBannerComponent extends BaseComponent {
    public StaticBannerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class TeamCardComponent extends BaseComponent {
    public TeamCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class PartnersComponent extends BaseComponent {
    public PartnersComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
