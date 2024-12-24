package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeamPageAdminPanel extends BasePageAdminPanel {

    @FindBy(xpath = "")
    WebElement addNewMemberButton;
    TeamPageGridComponent teamPageGridComponent;

    public TeamPageAdminPanel(WebDriver driver) { super(driver); }
}
