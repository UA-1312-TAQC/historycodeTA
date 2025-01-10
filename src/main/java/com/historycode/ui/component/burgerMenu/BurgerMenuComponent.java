package com.historycode.ui.component.burgerMenu;

import com.historycode.ui.component.header.HeaderComponent;
import com.historycode.ui.page.contactUs.ContactUsPage;
import com.historycode.ui.page.HelpUsPage.HelpUsPage;
import com.historycode.ui.page.homePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BurgerMenuComponent extends HeaderComponent {

    @FindBy(xpath = "//a[@href='/' and contains(@class, 'headerItem')]")
    private WebElement main;

    @FindBy(xpath = "//a[@href='/catalog' and contains(@class, 'headerItem')]")
    private WebElement historyCode;

    @FindBy(xpath = "//a[@href='/about-us' and contains(@class, 'headerItem')]")
    private WebElement aboutUs;

    @FindBy(xpath = "//a[@href='/partners-page' and contains(@class, 'headerItem')]")
    private WebElement partners;

    @FindBy(xpath = "//a[@href='/support-us' and contains(@class, 'headerItem')]")
    private WebElement donate;

    @FindBy(xpath = "//a[@href='/contact-us' and contains(@class, 'headerItem')]")
    private WebElement contacts;

    @FindBy(xpath = "//button[contains(@class, 'ant-drawer-close') and @aria-label='Close']")
    private WebElement close;

    public BurgerMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public HomePage clickMain() {
        main.click();
        return new HomePage(driver);
    }

  /*  public HistoryCodePage clickHistoryCode() {
        historyCode.click();
        return new HistoryCodePage(driver);
    }

    public AboutUsPage clickAboutUs() {
        aboutUs.click();
        return new AboutUsPage(driver);
    }

    public PartnersPage clickPartners() {
        partners.click();
        return new PartnersPage(driver);
    }*/

    public HelpUsPage clickDonate() {
        donate.click();
        return PageFactory.initElements(driver, HelpUsPage.class);
    }

    public ContactUsPage clickContacts() {
        contacts.click();
        return PageFactory.initElements(driver, ContactUsPage.class);
    }

    public void clickClose() {
        close.click();
    }
}
