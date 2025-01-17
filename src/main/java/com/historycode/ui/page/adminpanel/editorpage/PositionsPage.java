package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.adminpanel.editorpage.components.grids.PositionsGridComponent;

import com.historycode.ui.page.adminpanel.editorpage.components.modals.PositionsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class PositionsPage extends BasePage {
    @FindBy(xpath = "//button[span[text()='Додати нову позицію']]")
    WebElement addNewPositionButton;

    @FindBy(xpath = "//div[@class='positions-page']//div[@class='positions-page-container']")
    private WebElement gridRootElement;

    @FindBy(xpath = "//div[@class='ant-modal-content' and .//h2[text()='Додати нову позицію']]\n")
    private WebElement createModalRootElement;

    @FindBy(xpath = "//div[@class='ant-modal-content' and .//h2[text()='Редагувати позицію']]")
    private WebElement editModalRootElement;

    private PositionsGridComponent grid;

    public PositionsPage(WebDriver driver) {
        super(driver);
        this.grid = new PositionsGridComponent(driver, gridRootElement);
    }

    @Step("Check Positions Grid Is Displayed Correctly.")
    public boolean isGridDisplayed() {
        return grid.isDisplayed();
    }

    public PositionsModalComponent addPosition() {
        waitUntilElementClickable(addNewPositionButton);
        addNewPositionButton.click();
        return new PositionsModalComponent(driver, createModalRootElement);
    }

    @Step("Get Table Headers.")
    public List<String> getTableHeadersString() {
        return grid.getHeaderItemsString();
    }

    public int getTableRowsCount() {
        return grid.getRows().size();
    }

    public List<String> getTableRowsTitles() {
        return grid.getRowsTitles();
    }

    public List<PositionsRowComponent> getTableRows() {
        return grid.getRows();
    }

    public List<PositionsRowComponent> getTableRowsByTitlePart(String part) {
        return grid.getRowsByTitlePart(part);
    }

    public PositionsRowComponent getTableRowByNumber(int num) {
        return grid.getRowByNum(num);
    }

    public PositionsRowComponent getTableRowByTitle(String title) {
        return grid.getRowByTitle(title);
    }

    public String getAddButtonText() {
        return addNewPositionButton.getText();
    }

    public PositionsModalComponent editTableRow(PositionsRowComponent row) {
        grid.editRow(row);
        return new PositionsModalComponent(driver, editModalRootElement);
    }

    public DeleteItemModal deleteTableRow(PositionsRowComponent row) {
        waitUntilElementVisible(gridRootElement);
        return grid.deleteRow(row);
    }
}
