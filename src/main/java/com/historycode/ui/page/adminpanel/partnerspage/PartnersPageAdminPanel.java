package com.historycode.ui.page.adminpanel.partnerspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersPageAdminPanel extends BasePageAdminPanel {

    @FindBy(xpath = "//button[span[text()='Створити партнера']]")
    private WebElement addNewPartnersButton;
    @FindBy(xpath = "//div[@class='partners-page']//div[@class='partners-page-container']")
    private WebElement rooElement;
    private PartnersPageGridComponent partnersPageGridComponent;

    public PartnersPageAdminPanel(WebDriver driver) {
        super(driver);
        partnersPageGridComponent = new PartnersPageGridComponent(driver, rooElement);
    }

    public void clickAddNewPartnersButton() {
        addNewPartnersButton.click();
    }

    public PartnersPageGridComponent getPartnersPageGridComponent() {
        return partnersPageGridComponent;
    }
}
