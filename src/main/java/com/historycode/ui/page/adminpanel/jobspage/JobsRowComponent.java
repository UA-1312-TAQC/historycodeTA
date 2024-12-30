package com.historycode.ui.page.adminpanel.jobspage;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobsRowComponent extends BaseComponent {
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
    DropdownComponent dropdown;

    public JobsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver,rootElement);
    }
}
