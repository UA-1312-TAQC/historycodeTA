package com.historycode.ui.page.adminpanel.partnerspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersPageAdminPanel extends BasePageAdminPanel {

    protected PartnersPageGridComponent partnersPageGridComponent;

    @FindBy(xpath = "//button[span[text()='Створити партнера']]")
    private WebElement addNewPartnersButton;

    @FindBy(xpath = "//div[@class = 'ant-table-container']")
    private WebElement gridRootElement;

    @FindBy(xpath = "//div[@class='ant-modal-content']//span[text()='Зберегти']/../../../..")
    private WebElement createModalRootElement;

    public PartnersPageAdminPanel(WebDriver driver) {
        super(driver);
    }

    public PartnersPageGridComponent getPartnersPageGridComponent(){
        if(partnersPageGridComponent == null){
            waitUntilElementVisible(gridRootElement);
            partnersPageGridComponent = new PartnersPageGridComponent(driver, gridRootElement);
        }
        return partnersPageGridComponent;
    }

    @Step("Click the 'Створити нового члена команди' button")
    public CreatePartnersModal clickAddNewPartnersButton() {
        addNewPartnersButton.click();
        waitUntilElementVisible(createModalRootElement);
        return new CreatePartnersModal(driver, createModalRootElement);
    }

    public PartnersPageAdminPanel clickNextPage() {
        getPartnersPageGridComponent().clickNextPage();
        return new PartnersPageAdminPanel(driver);
    }

    public PartnersPageAdminPanel clickPrevPage() {
        getPartnersPageGridComponent().clickPrevPage();
        return new PartnersPageAdminPanel(driver);
    }

    public PartnersPageAdminPanel clickPaginationItem(int index) {
        getPartnersPageGridComponent().clickPaginationItem(index);
        return new PartnersPageAdminPanel(driver);
    }

    public PartnersPageAdminPanel clickLastPage(){
        getPartnersPageGridComponent().clickLastPage();
        return new PartnersPageAdminPanel(driver);
    }
}
