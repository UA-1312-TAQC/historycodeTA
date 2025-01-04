package com.historycode.ui.page.adminpanel.partnerspage;

import java.util.List;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;

import com.historycode.ui.page.adminpanel.partnerspage.modal.EditPartnersModal;

import lombok.Getter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersRowComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = "//td[1]//div[@class='partner-table-item-name']//p")
    private WebElement name;

    @Getter
    @FindBy(xpath = "//td[2]//a[@class = 'site-link']")
    private WebElement link;

    @Getter
    @FindBy(xpath = "//td[2]//a[@class = 'site-link']")
    private WebElement logo;

    @Getter
    @FindBy(xpath = "/td[4]//div[@class = 'partner-links']")
    List<PartnersSocialMediaComponent> socialMediaComponents;

    @Getter
    @FindBy(xpath = "//td[5]//span[@aria-label = 'delete']")
    private WebElement deleteAction;

    @Getter
    @FindBy(xpath = "//td[5]//span[@aria-label = 'edit']")
    private WebElement editAction;

    public PartnersRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public DeleteItemModal clickDelete() {
        deleteAction.click();
        return new DeleteItemModal(driver, rootElement);
    }

    /* Звітки в нас буде іти роот елемент ? Який саме він буде ? */
    public EditPartnersModal clickEdit() {
        editAction.click();
        return new EditPartnersModal(driver, rootElement);
    }

}
