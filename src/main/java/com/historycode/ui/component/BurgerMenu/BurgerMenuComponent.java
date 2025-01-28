package com.historycode.ui.component.BurgerMenu;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.HelpUsPage.HelpUsPage;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.contactUs.ContactUsPage;
import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.partnerPage.PartnerPage;
import com.historycode.ui.page.privacyPolicyPage.PrivacyPolicyPage;
import com.historycode.ui.page.streetcodecatalogpage.StreetCodeCatalogPage;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BurgerMenuComponent extends BaseComponent {

    @FindBy(xpath = ".//div[contains(@class, 'drawerContainer')]")
    private WebElement containerMenu;

    @FindBy(xpath = ".//a[contains(@class, 'headerItem')]")
    private List<WebElement> menuItems;

    @FindBy(xpath = ".//a[@class='headerItem' and text()='Головна']")
    WebElement mainPageNode;
    @FindBy(xpath = ".//a[@class='headerItem'and @href='/catalog']")
    WebElement historyCodesNode;
    @FindBy(xpath = ".//a[@class='headerItem' and @href='/about-us']")
    WebElement aboutUsNode;
    @FindBy(xpath = ".//a[@class='headerItem' and @href='/partners-page']")
    WebElement partnersNode;
    @FindBy(xpath = ".//a[@class='headerItem' and @href='/support-us']")
    WebElement donationNode;
    @FindBy(xpath = ".//a[@class='headerItem' and @href='/contact-us']")
    WebElement contactsNode;
    @FindBy(xpath = ".//a[@class='headerItem' and @href='/privacy-policy']")
    WebElement privacyPolicyNode;

    public BurgerMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    @Step("Click on the 'Головна' button in the burger-menu")
    public HomePage goToHonePage() {
        mainPageNode.click();
        return new HomePage (driver);
    }

    @Step("Click on the 'History-коди' button in the burger-menu")
    public StreetCodeCatalogPage goToStreetCodeCatalogPage() {
        historyCodesNode.click();
        return new StreetCodeCatalogPage(driver);
    }

//    /** AboutUsPage does not exist */
//    @Step("Click on the 'Про нас' button in the burger-menu")
//    public AboutUsPage goToAboutUsPage() {
//        aboutUsNode.click();
//        return new AboutUsPage(driver);
//    }

    @Step("Click on the 'Партнери' button in the burger-menu")
    public PartnerPage goToPartnerPage() {
        partnersNode.click();
        return new PartnerPage(driver);
    }

    @Step("Click on the 'Донати' button in the burger-menu")
    public HelpUsPage goToHelpUsPage() {
        donationNode.click();
        return new HelpUsPage(driver);
    }

    @Step("Click on the 'Контакти' button in the burger-menu")
    public ContactUsPage goToContactUsPage() {
        contactsNode.click();
        return new ContactUsPage(driver);
    }

    @Step("Click on the 'Політика конфіденційності' button in the burger-menu")
    public PrivacyPolicyPage goToPrivacyPolicyPage() {
        privacyPolicyNode.click();
        return new PrivacyPolicyPage(driver);
    }

    public void clickMenuItem(String itemName) {
        WebElement menuItem = wait.until(ExpectedConditions.visibilityOfAllElements(menuItems))
                .stream()
                .filter(item -> item.getText().equalsIgnoreCase(itemName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Menu item " + itemName + " not found"));
        wait.until(ExpectedConditions.elementToBeClickable(menuItem));
        menuItem.click();
    }
}