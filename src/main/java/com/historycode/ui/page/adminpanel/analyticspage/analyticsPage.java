package com.historycode.ui.page.adminpanel.analyticspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class analyticsPage extends BasePageAdminPanel {
    public analyticsPage(WebDriver driver) {
        super(driver);
        //TODO add streetCodeName initialization
    }

    private WebElement streetCodeName;

    private WebElement statisticTable;
    //TODO change to proper component
}
