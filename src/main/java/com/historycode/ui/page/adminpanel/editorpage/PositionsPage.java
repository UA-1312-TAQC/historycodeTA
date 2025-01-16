package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.editorpage.components.grids.PositionsGridComponent;

import com.historycode.ui.page.adminpanel.editorpage.components.modals.PositionsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.addButtonElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class PositionsPage extends BasePage {
    @FindBy(xpath = "//button[span[text()='Додати нову позицію']]")
    WebElement addNewPositionButton;

    //TODO Added another grid root
    @FindBy(xpath = "//div[@class='positions-page']//div[@class='positions-page-container']")
    private WebElement gridRootElement;

    @FindBy(xpath = "//div[@role = 'dialog']/div[2]")
    private WebElement createModalRootElement;

    private PositionsGridComponent gridComponent;

    public PositionsPage(WebDriver driver) {
        super(driver);
        this.gridComponent = new PositionsGridComponent(driver, gridRootElement);
    }

    @Step("Add new position")
    public PositionsModalComponent addPosition() {
        waitUntilElementClickable(addNewPositionButton);
        addNewPositionButton.click();
        return new PositionsModalComponent(driver, createModalRootElement);
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

    public List<PositionsRowComponent> getTableRows() {
        return gridComponent.getRows();
    }

    public List<PositionsRowComponent> getTableRowsByTitlePart(String part) {
        return gridComponent.getRowsByTitlePart(part);
    }

    public PositionsRowComponent getTableRowByNumber(int num) {
        return gridComponent.getRowByNum(num);
    }

    public PositionsRowComponent getTableRowByTitle(String title) {
        waitUntilElementVisible(gridRootElement);
        return gridComponent.getRowByTitle(title);
    }

    public String getAddButtonText() {
        return addNewPositionButton.getText();
    }

    public PositionsModalComponent editTableRow(PositionsRowComponent row) {
        gridComponent.editRow(row);
        return new PositionsModalComponent(driver, getDisplayedModalRoot());
    }

    public DeleteItemModal deleteTableRow(PositionsRowComponent row) {
        waitUntilElementVisible(gridRootElement);
        return gridComponent.deleteRow(row);
    }
}
