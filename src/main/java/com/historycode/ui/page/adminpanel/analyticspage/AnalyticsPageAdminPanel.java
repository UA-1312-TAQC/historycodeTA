package com.historycode.ui.page.adminpanel.analyticspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.jobspage.JobsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AnalyticsPageAdminPanel extends BasePageAdminPanel {

    @FindBy(css = ".streetcodeImgWrapper .streetcodeName")
    private WebElement streetcodeName;

    @FindBy(css = ".ant-table-wrapper")
    private WebElement analyticsPageGridComponentRoot;
    private AnalyticsPageGridComponent analyticsPageGridComponent;

    public AnalyticsPageAdminPanel(WebDriver driver) {
        super(driver);
    }

    public String getStreetcodeName(){return streetcodeName.getText().trim();}

    public AnalyticsPageGridComponent getGridComponent(){
        if(analyticsPageGridComponent == null){
            analyticsPageGridComponent = new AnalyticsPageGridComponent(driver, analyticsPageGridComponentRoot);
        }
        return analyticsPageGridComponent;
    }
    public AnalyticsPageAdminPanel clickNextPagePaginationItem(){
        analyticsPageGridComponent.clickNextPage();
        return new AnalyticsPageAdminPanel(driver);
    }
    public AnalyticsPageAdminPanel clickPreviousPagePaginationItem(){
        analyticsPageGridComponent.clickPrevPage();
        return new AnalyticsPageAdminPanel(driver);
    }
    public AnalyticsPageAdminPanel clickPagePaginationItemByPageNumber(int pageNumber){
        analyticsPageGridComponent.clickPaginationItem(pageNumber);
        return new AnalyticsPageAdminPanel(driver);
    }

    public PartnersPageAdminPanel goToPartnersPage() {
        return adminMenuBar.goToPartnersPage();
    }

    //TODO check return value
/*    EditorPageAdminPanel goToEditorPage() {
        return new EditorPageAdminPanel(driver);
    }*/

    public NewsPageAdminPanel goToNewsPage() {
        return adminMenuBar.goToNewsPage();
    }

    public TeamPageAdminPanel goToTeamPage() {
        return adminMenuBar.goToTeamPage();
    }

    public JobsPageAdminPanel goToJobsPage() {
        return adminMenuBar.goToJobsPage();
    }


}
