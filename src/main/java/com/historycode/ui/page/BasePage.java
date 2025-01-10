package com.historycode.ui.page;

import com.historycode.ui.Base;
import com.historycode.ui.component.BurgerMenu.BurgerMenuComponent;
import com.historycode.ui.component.footer.FooterComponent;
import com.historycode.ui.component.header.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public abstract class BasePage extends Base {

    @FindBy(xpath = "//div[@class='HeaderBlock']")
    private WebElement headerNode;

    @FindBy(xpath = "//div[@class='footerContainer']")
    private WebElement footerNode;

    @FindBy(xpath = "//div[contains(@class, 'rightPartContainer')]//div[contains(@class, 'drawerContainer')]//div")
    private WebElement burgerMenu;

    protected HeaderComponent header;
    protected FooterComponent footer;
    protected BurgerMenuComponent burgerMenuComponent;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver, this.headerNode);
        this.footer = new FooterComponent(driver, this.footerNode);
        this.burgerMenuComponent = new BurgerMenuComponent(driver, this.burgerMenu);
        PageFactory.initElements(driver, this);
    }

    public boolean isBurgerMenuVisible() {
        sleep(5000);
        return burgerMenu.isDisplayed();
    }

    public void openBurgerMenu() {
        sleep(5000);
        burgerMenu.click();
    }
}