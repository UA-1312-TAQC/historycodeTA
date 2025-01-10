package com.historycode.ui.page.adminpanel.partnerspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;

import com.historycode.ui.page.adminpanel.jobspage.JobsPageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersPageAdminPanel extends BasePageAdminPanel {

    @FindBy(xpath = "//button[span[text()='Створити партнера']]")
    private WebElement addNewPartnersButton;
    @FindBy(xpath = "//div[@class='partners-page']//div[@class='partners-page-container']")
    private WebElement rootElement;
    private PartnersPageGridComponent partnersPageGridComponent;

    public PartnersPageAdminPanel(WebDriver driver) {
        super(driver);
        partnersPageGridComponent = new PartnersPageGridComponent(driver, rootElement);
    }

    public void clickAddNewPartnersButton() {
        addNewPartnersButton.click();
    }

    public PartnersPageGridComponent getPartnersPageGridComponent() {
        return partnersPageGridComponent;
    }

    public PartnersPageAdminPanel clickNextPage() {
        partnersPageGridComponent.clickNextPage();
        return this;
    }

    public PartnersPageAdminPanel  clickPrevPage() {
        partnersPageGridComponent.clickPrevPage();
        return this;
    }

    public PartnersPageAdminPanel  clickPaginationItem(int index) {
        partnersPageGridComponent.clickPaginationItem(index);
        return this;
    }
}
