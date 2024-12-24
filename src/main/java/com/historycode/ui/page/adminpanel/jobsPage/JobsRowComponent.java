package com.historycode.ui.page.adminpanel.jobsPage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseRowComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobsRowComponent extends BaseRowComponent {
    @FindBy(xpath = "")
    WebElement title;
    @FindBy(xpath = "")
    WebElement salary;
    @FindBy(xpath = "")
    WebElement status;
    @FindBy(xpath = "")
    WebElement deleteAction;
    @FindBy(xpath = "")
    WebElement editAction;

    //TODO Ask about how to find dropdown
    JobsDropdownComponent dropdown;

    public JobsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
