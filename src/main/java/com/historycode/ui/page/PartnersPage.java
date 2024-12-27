package com.historycode.ui.page;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersCreateButton;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersGridComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PartnersPage extends BasePage {


    private PartnersCreateButton partnersCreateButton;
    private PartnersGridComponent partnersGreedTable;
    protected WebElement rootElement;

    public PartnersPage(WebDriver driver) {
        super(driver);
        this.rootElement = rootElement;
        this.partnersCreateButton = new PartnersCreateButton(driver, rootElement);
        this.partnersGreedTable = new PartnersGridComponent(driver, rootElement, null, null);
    }
}
