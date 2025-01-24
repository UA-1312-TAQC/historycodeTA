package com.historycode.ui.page.adminpanel.partnerspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;

import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersPageAdminPanel extends BasePageAdminPanel {

    @FindBy(xpath = "//button[span[text()='Створити партнера']]")
    private WebElement addNewPartnersButton;

    @FindBy(xpath = "//div[contains(@class, 'partners-table')]")
    private WebElement rootElement;

    @FindBy(xpath = "//div[@class='ant-modal-content']//span[text()='Зберегти']/../../../..")
    private WebElement createModalRootElement;

    @Getter
    private final PartnersPageGridComponent partnersPageGridComponent;

    public PartnersPageAdminPanel(WebDriver driver) {
        super(driver);
        partnersPageGridComponent = new PartnersPageGridComponent(driver, rootElement);
    }

    public CreatePartnersModal clickAddNewPartnersButton() {
        addNewPartnersButton.click();
        waitUntilElementVisible(createModalRootElement);
        return new CreatePartnersModal(driver, createModalRootElement);
    }

    public PartnersPageAdminPanel clickNextPage() {
        partnersPageGridComponent.clickNextPage();
        return this;
    }

    public PartnersPageAdminPanel clickPrevPage() {
        partnersPageGridComponent.clickPrevPage();
        return this;
    }

    public PartnersPageAdminPanel clickPaginationItem(int index) {
        partnersPageGridComponent.clickPaginationItem(index);
        return this;
    }
}
