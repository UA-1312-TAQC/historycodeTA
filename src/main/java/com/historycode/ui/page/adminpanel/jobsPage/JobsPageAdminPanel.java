package com.historycode.ui.page.adminpanel.jobsPage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobsPageAdminPanel extends BasePageAdminPanel {
    @FindBy(xpath = "")
    WebElement addNewJobButton;
    JobsPageGridComponent jobsPageGridComponent;

    public JobsPageAdminPanel(WebDriver driver) {
        super(driver);
    }
}
