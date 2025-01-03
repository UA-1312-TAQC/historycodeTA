package com.historycode.ui.page.adminpanel;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;


@Getter
public abstract class BasePageAdminPanel extends BasePage {
    protected AdminMenuBarComponent adminMenuBar;

    public BasePageAdminPanel(WebDriver driver) {
        super(driver);
    }
}
