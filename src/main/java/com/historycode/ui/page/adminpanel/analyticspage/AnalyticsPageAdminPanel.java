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


    //TODO add new table creation
    public void clickNextPagePaginationItem(){analyticsPageGridComponent.clickNextPage();}
    public void clickPreviousPagePaginationItem(){analyticsPageGridComponent.clickPrevPage();}
    public void clickPagePaginationItemByPageNumber(int pageNumber){analyticsPageGridComponent.clickPaginationItem(pageNumber);}


}
