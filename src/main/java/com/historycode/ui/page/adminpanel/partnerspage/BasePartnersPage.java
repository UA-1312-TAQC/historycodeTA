package com.historycode.ui.page.adminpanel.partnerspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;

import lombok.Getter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
public class BasePartnersPage extends BasePageAdminPanel {

    private PartnersPagination partnersPagination;
    private PartnersCreateButton partnersCreateButton;
    private PartnersInfoTable partnersInfoTable;
    protected WebElement rootElement;

    public BasePartnersPage(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.rootElement = rootElement;
        this.partnersPagination = new PartnersPagination(driver, rootElement);
        this.partnersCreateButton = new PartnersCreateButton(driver, rootElement);
        this.partnersInfoTable = new PartnersInfoTable(driver, rootElement);
    }

}