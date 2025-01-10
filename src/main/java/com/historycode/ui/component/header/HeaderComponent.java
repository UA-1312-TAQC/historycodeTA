package com.historycode.ui.component.header;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.burgerMenu.BurgerMenuComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BaseComponent {

    @FindBy(css = ".burgerMenuButton")
    private WebElement burgerMenuButton;

    @FindBy(css = ".ant-drawer-content")
    private WebElement burgerMenuContainer;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public BurgerMenuComponent clickBurgerMenu() {
        burgerMenuButton.click();
        return new BurgerMenuComponent(driver, burgerMenuContainer);
    }
}
