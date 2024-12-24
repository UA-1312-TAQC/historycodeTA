package com.historycode.ui.page.adminpanel.jobsPage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobsDropdownComponent extends BaseComponent {
    @FindBy(xpath = "")
    WebElement activeStatusOption;
    @FindBy(xpath = "")
    WebElement inactiveStatusOption;

    public JobsDropdownComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
