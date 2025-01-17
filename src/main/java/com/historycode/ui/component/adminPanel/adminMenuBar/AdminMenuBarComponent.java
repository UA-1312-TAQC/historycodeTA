package com.historycode.ui.component.adminPanel.adminMenuBar;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.jobspage.JobsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminMenuBarComponent extends BaseComponent {
    @FindBy(xpath = "./div[@class='BarContainer']//a[@href='/admin-panel']")
    WebElement streetCodes;
    @FindBy(xpath = "./div[@class='BarContainer']//a[@href='/admin-panel/job']")
    WebElement partners;
    @FindBy(xpath = "./div[@class='BarContainer']//a[@href='/admin-panel/editor']")
    WebElement editor;
    @FindBy(xpath = "./div[@class='BarContainer']//a[@href='/admin-panel/team']")
    WebElement team;
    @FindBy(xpath = "./div[@class='BarContainer']//a[@href='/admin-panel/news']")
    WebElement news;
    @FindBy(xpath = "./div[@class='BarContainer']//a[@href='/admin-panel/job']")
    WebElement jobs;

    public AdminMenuBarComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

/*    StreetCodeCatalogPageAdminPanel goToSreetcodePage() {
        return new StreetCodeCatalogPageAdminPanel(driver);
    }*/

    public PartnersPageAdminPanel goToPartnersPage() {
        partners.click();
        return new PartnersPageAdminPanel(driver);
    }

    public CategoriesPage goToEditorPage() {
        waitUntilElementClickable(editor);
        editor.click();
        return new CategoriesPage(driver);
    }

    public NewsPageAdminPanel goToNewsPage() {
        news.click();
        return new NewsPageAdminPanel(driver);
    }

    public TeamPageAdminPanel goToTeamPage() {
        team.click();
        return new TeamPageAdminPanel(driver);
    }

    public JobsPageAdminPanel goToJobsPage() {
        jobs.click();
        return new JobsPageAdminPanel(driver);
    }
}
