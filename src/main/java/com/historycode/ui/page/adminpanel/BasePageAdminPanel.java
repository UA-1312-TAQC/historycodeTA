package com.historycode.ui.page.adminpanel;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Getter
public abstract class BasePageAdminPanel extends BasePage {

    @FindBy(css = "div.PageBarContainer")
    private WebElement adminMenuBarRoot;

    protected AdminMenuBarComponent adminMenuBar;

    protected BasePageAdminPanel(WebDriver driver) {
        super(driver);
        adminMenuBar = new AdminMenuBarComponent(driver, adminMenuBarRoot);
    }
}
