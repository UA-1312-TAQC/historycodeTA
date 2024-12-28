package com.historycode.ui.component.adminPanel.adminMenuBar;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.EditorPageAdminPanel;
import com.historycode.ui.page.adminpanel.jobspage.JobsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminMenuBarComponent extends BaseComponent {
    @FindBy(xpath = "")
    WebElement streetCodes;
    @FindBy(xpath = "")
    WebElement partners;
    @FindBy(xpath = "")
    WebElement editor;
    @FindBy(xpath = "")
    WebElement team;
    @FindBy(xpath = "")
    WebElement news;
    @FindBy(xpath = "")
    WebElement jobs;

    public AdminMenuBarComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

/*    StreetCodeCatalogPageAdminPanel goToSreetcodePage() {
        return new StreetCodeCatalogPageAdminPanel(driver);
    }*/

    PartnersPageAdminPanel goToPartnersPage() {
        return new PartnersPageAdminPanel(driver);
    }

/*    EditorPageAdminPanel goToEditorPage() {
        return new EditorPageAdminPanel(driver);
    }*/

    NewsPageAdminPanel goToNewsPage() {
        news.click();
        return new NewsPageAdminPanel(driver);
    }

    TeamPageAdminPanel goToTeamPage() {
        team.click();
        return new TeamPageAdminPanel(driver);
    }

    JobsPageAdminPanel goToJobsPage() {
        jobs.click();
        return new JobsPageAdminPanel(driver);
    }
}
