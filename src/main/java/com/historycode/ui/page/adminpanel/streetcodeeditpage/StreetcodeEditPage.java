package com.historycode.ui.page.adminpanel.streetcodeeditpage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StreetcodeEditPage extends BasePageAdminPanel {

    @FindBy(id = "streetcodeNumber")
    @Getter
    private WebElement streetcodeNumber;

    @FindBy(id = "streetcodeType")
    @Getter
    private WebElement streetcodeType;

    @FindBy(id = "mainTitle")
    @Getter
    private WebElement mainTitle;

    @FindBy(id = "name")
    @Getter
    private WebElement firstName;

    @FindBy(id = "surname")
    @Getter
    private WebElement surname;

    public StreetcodeEditPage(WebDriver driver) {
        super(driver);
    }
}
