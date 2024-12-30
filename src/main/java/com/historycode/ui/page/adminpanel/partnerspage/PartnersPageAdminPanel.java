package com.historycode.ui.page.adminpanel.partnerspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersPageAdminPanel extends BasePageAdminPanel {
    
    @FindBy(xpath = "")
    WebElement addNewPartnersButton;
    PartnersPageGridComponent PartnersPageGridComponent;

    public PartnersPageAdminPanel(WebDriver driver) {
        super(driver);
    }
}
