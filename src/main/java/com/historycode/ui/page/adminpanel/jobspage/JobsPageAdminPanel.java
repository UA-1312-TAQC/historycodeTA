package com.historycode.ui.page.adminpanel.jobspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobsPageAdminPanel extends BasePageAdminPanel {
    @FindBy(xpath = "//button[span[text()='Додати нову вакансію']]")
    WebElement addNewJobButton;
    JobsPageGridComponent jobsPageGridComponent;

    public JobsPageAdminPanel(WebDriver driver) {
        super(driver);
    }
    //TODO what functionality should be here?
    //TODO what about loading new tables when using pagination? Should I return new Grid or smth?
}
