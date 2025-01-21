package com.historycode.ui.page.adminpanel.newspage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.EditNewsModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewsPageAdminPanel extends BasePageAdminPanel {
    @FindBy(xpath = "//button[span[text()='Створити новину']]")
    private WebElement addNewInfo;
    private final NewsPageGridComponent newsGridComponent;
    @FindBy(xpath = "//h2[starts-with(text(),'Додати')]/ancestor::div[@class = 'ant-modal-content']")
    private WebElement createEditModalNode;

    public NewsPageAdminPanel(WebDriver driver) {
        super(driver);
        newsGridComponent = new NewsPageGridComponent(driver, driver.findElement(By.className("partners-page-container")));
    }

    public NewsPageGridComponent getNewsPageGridComponent() {
        return newsGridComponent;
    }

    public EditNewsModal clickAddNewInfo() {
        addNewInfo.click();
        waitUntilElementVisible(createEditModalNode);
        return new EditNewsModal(driver, createEditModalNode);
    }

    public EditNewsModal editNewsByIndex(int index) {
        NewsRowComponent newsInfoToEdit = newsGridComponent.getRowById(index);
        return newsInfoToEdit.clickEdit();
    }

    public DeleteItemModal deleteNewsByIndex(int index) {
        NewsRowComponent newsInfoToEdit = newsGridComponent.getRowById(index);
        return newsInfoToEdit.clickDelete();
    }

    public NewsPageAdminPanel clickNextPage() {
        newsGridComponent.clickNextPage();
        return this;
    }

    public NewsPageAdminPanel clickPrevPage() {
        newsGridComponent.clickPrevPage();
        return this;
    }

    public NewsPageAdminPanel clickPaginationItem(int index) {
        newsGridComponent.clickPaginationItem(index);
        return this;
    }

    public NewsPageAdminPanel clickNextFivePages() {
        newsGridComponent.clickNextFivePages();
        return this;
    }

    public NewsPageAdminPanel clickPrevFivePages() {
        newsGridComponent.clickPrevFivePages();
        return this;
    }
}

