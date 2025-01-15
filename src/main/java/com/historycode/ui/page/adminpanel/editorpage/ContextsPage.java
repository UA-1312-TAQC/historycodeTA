package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.editorpage.components.grids.ContextsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.ContextsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContextsPage extends BasePage {
    @FindBy(xpath = "//button[span[text()='Додати новий контекст']]")
    WebElement addNewContextButton;

    @FindBy(xpath = "//div[@class='contexts-page']//div[@class='contexts-page-container']")
    private WebElement gridRootElement;

    @FindBy(xpath = "//div[@role = 'dialog']/div[2]")
    private WebElement createModalRootElement;

    private ContextsGridComponent gridComponent;

    public ContextsPage(WebDriver driver) {
        super(driver);
        this.gridComponent = new ContextsGridComponent(driver, gridRootElement);
    }

    @Step("Add new context")
    public ContextsModalComponent addContext() {
        addNewContextButton.click();
        waitUntilElementVisible(createModalRootElement);
        return new ContextsModalComponent(driver, createModalRootElement);
    }

    public int getTableHeadersCount() {
        return gridComponent.getHeaderItems().size();
    }

    public List<String> getTableHeadersString() {
        return gridComponent.getHeaderItemsString();
    }

    public List<WebElement> getTableHeaders() {
        return gridComponent.getHeaderItems();
    }

    public int getTableRowsCount() {
        return gridComponent.getRows().size();
    }

    public List<String> getTableRowsTitles() {
        return gridComponent.getRowsTitles();
    }

    public List<ContextsRowComponent> getTableRows() {
        return gridComponent.getRows();
    }

    public List<ContextsRowComponent> getTableRowsByTitlePart(String part) {
        return gridComponent.getRowsByTitlePart(part);
    }

    public ContextsRowComponent getTableRowByNumber(int num) {
        return gridComponent.getRowByNum(num);
    }

    public ContextsRowComponent getTableRowByTitle(String title) {
        waitUntilElementVisible(gridRootElement);
        return gridComponent.getRowByTitle(title);
    }

    public String getAddButtonText() {
        return addNewContextButton.getText();
    }

    public ContextsModalComponent editTableRow(ContextsRowComponent row) throws InterruptedException {
        return gridComponent.editRow(row);
    }

    public DeleteItemModal deleteTableRow(ContextsRowComponent row) {
        waitUntilElementVisible(gridRootElement);
        return gridComponent.deleteRow(row);
    }
}
