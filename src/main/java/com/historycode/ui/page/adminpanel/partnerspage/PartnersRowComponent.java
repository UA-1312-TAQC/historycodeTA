package com.historycode.ui.page.adminpanel.partnerspage;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.partnerspage.modal.EditPartnersModal;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PartnersRowComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = "./td[1]//div[@class='partner-table-item-name']//p")
    private WebElement name;

    @Getter
    @FindBy(xpath = "./td[2]//a[@class = 'site-link']")
    private WebElement link;

    @Getter
    @FindBy(xpath = "./td[3]//img[@class = 'partners-table-logo']")
    private WebElement logo;

    @Getter
    @FindBy(xpath = ".td[4]//div[@class = 'partner-links']")
    List<PartnersSocialMediaComponent> socialMediaComponents;

    @Getter
    @FindBy(xpath = "./td[5]//span[@aria-label = 'delete']")
    private WebElement deleteAction;

    @Getter
    @FindBy(xpath = "./td[5]//span[@aria-label = 'edit']")
    private WebElement editAction;

    @FindBy(xpath = "//div[@class='ant-modal-content']//div[text()='Підтвердження']/../..")
    private WebElement deleteModalRootElement;

    @FindBy(xpath = "//div[@class='ant-modal-content']//span[text()='Зберегти']/../../../..")
    private WebElement editModalRootElement;

    public PartnersRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getNameText() {
        return name.getText();
    }

    public String getLogoSrc() {
        return logo.getDomAttribute("src");
    }

    @Step("Click on the delete button next to partner item")
    public DeleteItemModal clickDelete() {
        deleteAction.click();
        return new DeleteItemModal(driver, deleteModalRootElement);
    }

    @Step("Click on the edit button next to partner item")
    public EditPartnersModal clickEdit() {
        editAction.click();
        return new EditPartnersModal(driver, editModalRootElement);
    }

    @Override
    public String toString(){
        return " name " + getNameText();
    }

}
