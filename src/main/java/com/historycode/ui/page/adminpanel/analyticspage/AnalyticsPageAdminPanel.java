package com.historycode.ui.page.adminpanel.analyticspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AnalyticsPageAdminPanel extends BasePageAdminPanel {
    public AnalyticsPageAdminPanel(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".streetcodeImgWrapper .streetcodeName")
    private WebElement streetcodeName;

    private AnalyticsPageGridComponent analyticsPageGridComponent;

    public String getStreetcodeName(){return streetcodeName.getText();}

    public AnalyticsPageGridComponent getGridComponent(){return analyticsPageGridComponent;}


    //TODO implement methods
    public void clickNextPagePaginationItem(){}
    public void clickPreviousPagePaginationItem(){}
    public void clickPagePaginationItemByPageNumber(int pageNumber){}


}
