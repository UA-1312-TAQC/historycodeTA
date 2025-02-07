package com.historycode.ui.page.adminpanel;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.PopUpMessageComponent;
import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public abstract class BasePageAdminPanel extends BasePage {

    protected AdminMenuBarComponent adminMenuBar;
    @FindBy(xpath = "//div[contains(@class,'PageBarContainer')]")
    private WebElement adminMenuBarNode;

    public BasePageAdminPanel(WebDriver driver) {
        super(driver);
        sleep(2000);
        adminMenuBar = new AdminMenuBarComponent(driver, adminMenuBarNode);
    }

    public PopUpMessageComponent getPopUpMessageComponent(){
        return new PopUpMessageComponent(driver);
    }

    public AdminMenuBarComponent getAdminMenuBar () {
        return adminMenuBar;
    }

}
