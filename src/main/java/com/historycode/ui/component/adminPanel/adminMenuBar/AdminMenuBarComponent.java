package com.historycode.ui.component.adminPanel.adminMenuBar;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.jobsPage.JobsPageAdminPanel;
import com.historycode.ui.page.adminpanel.teamPage.TeamPageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminMenuBarComponent extends BaseComponent {
    @FindBy(xpath = "")
    WebElement streetCodesMenuItem;
    @FindBy(xpath = "")
    WebElement partnersMenuItem;
    @FindBy(xpath = "")
    WebElement editorMenuItem;
    @FindBy(xpath = "")
    WebElement teamMenuItem;
    @FindBy(xpath = "")
    WebElement newsMenuItem;
    @FindBy(xpath = "")
    WebElement jobsMenuItem;

    public AdminMenuBarComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

// TODO according to new blueprint

/*    StreetCodeCatalogPageAdminPanel goToSreetcodePage() {
        return new StreetCodeCatalogPageAdminPanel(driver);
    }

    PartnersPageAdminPanel goToPartnersPage() {
        return new PartnersPageAdminPanel(driver);
    }

    EditorPageAdminPanel goToEditorPage() {
        return new EditorPageAdminPanel(driver);
    }*/

    TeamPageAdminPanel goToTeamPage() {
        teamMenuItem.click();
        return new TeamPageAdminPanel(driver);
    }

    NewsPageAdminPanel goToNewsPage() {
        newsMenuItem.click();
        return new NewsPageAdminPanel(driver);
    }

    JobsPageAdminPanel goToJobsPage() {
        jobsMenuItem.click();
        return new JobsPageAdminPanel(driver);
    }
}
