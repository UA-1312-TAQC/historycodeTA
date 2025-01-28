package com.historycode.ui.component.header;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.BurgerMenu.BurgerMenuComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BaseComponent {

    @FindBy(xpath = "//div[@class = 'logoContainer']")
    WebElement logoNode;

    @FindBy(xpath = "//div[@class = 'searchHeaderSkeleton']")
    WebElement searchNode;

    @FindBy(xpath = "//div[@class = 'burgerMenuContainer']")
    WebElement burgerMenuNode;

    @FindBy(xpath = "//button[contains(@class, 'loginBtn')]")
    WebElement joinBtnNode;

    @Getter
    SearchElement searchElement;


    @FindBy(xpath = "//div[contains(@class, 'rightPartContainer')]//div[contains(@class, 'drawerContainer')]//div")
    private WebElement burgerMenuBtn;
    @FindBy(xpath = "//div[@class='ant-drawer-body']")
    private WebElement burgerMenuBody;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        searchElement = new SearchElement(driver, searchNode);
    }

    public boolean isBurgerMenuVisible() {
        return burgerMenuBody.isDisplayed();
    }


    public BurgerMenuComponent clickBurgerMenuBtn(){
        burgerMenuBtn.click();
        return new BurgerMenuComponent(driver, burgerMenuBody);
    }
}
