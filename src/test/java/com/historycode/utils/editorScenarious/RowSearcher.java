package com.historycode.utils;

import com.historycode.ui.page.adminpanel.editorpage.*;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class EditorPageRowSearcher {

    private WebDriver driver;
    private PositionsRowComponent positionRow;

    public EditorPageRowSearcher(WebDriver driver) {
        this.driver = driver;
    }

    public EditorPageRowSearcher searchPositionRow(String title) {

        driver.navigate().refresh();
        PositionsPage currentPage = new CategoriesPage(driver).moveToPositions();

        while (currentPage.getTableRowByTitle(title) == null) {
            if (!currentPage.tableHasNextPage()) {
                break;
            }
            currentPage = currentPage.clickNextPage();
        }
        positionRow = currentPage.getTableRowByTitle(title);
        return this;
    }

    public EditorPageRowSearcher deletePositionRow() {

        PositionsPage currentPage = new PositionsPage(driver);

        if (positionRow != null) {
            currentPage.deleteTableRow(positionRow).clickOkButton();
            positionRow = null;
        }

        driver.navigate().refresh();
        new CategoriesPage(driver).moveToPositions();

        return this;

    }

}
