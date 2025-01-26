package com.historycode.ui.page.adminpanel.newspage;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewsRowComponent extends BaseComponent {
    @FindBy(xpath = "//td[1]//div")
    @Getter
    private WebElement name;

    @FindBy(xpath = "//td[2]//img[@class='partners-table-logo']")
    @Getter
    private WebElement picture;

    @FindBy(xpath = "//td[3]//div[@class='partner-table-item-name']//p")
    @Getter
    private WebElement dateOfCreation;

    @FindBy(xpath = "//td[4]//span[contains(@class, 'anticon-delete')]")
    @Getter
    private WebElement actionDelete;

    @FindBy(xpath = "//td[4]//span[contains(@class, 'anticon-edit')]")
    @Getter
    private WebElement actionEdit;

    public NewsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public DeleteItemModal clickDelete() {
        actionDelete.click();
        return new DeleteItemModal(driver, rootElement);
    }

    public CreateEditNewsModal clickEdit() {
        actionEdit.click();
        return new CreateEditNewsModal(driver, rootElement);
    }
}
