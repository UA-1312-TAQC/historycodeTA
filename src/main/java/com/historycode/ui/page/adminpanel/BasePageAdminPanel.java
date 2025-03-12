package com.historycode.ui.page.adminpanel;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.PopUpMessageComponent;
import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BasePageAdminPanel extends BasePage {

    @Getter
    protected AdminMenuBarComponent adminMenuBar;
    @FindBy(xpath = "//div[contains(@class,'PageBarContainer')]")
    private WebElement adminMenuBarNode;

    public BasePageAdminPanel(WebDriver driver) {
        super(driver);
        adminMenuBar = new AdminMenuBarComponent(driver, adminMenuBarNode);
    }

    public PopUpMessageComponent getPopUpMessageComponent(){
        return new PopUpMessageComponent(driver);
    }
}
