package com.historycode.ui.page.adminpanel.historycodePage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.NoDataComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HistoryCodesAdminPanelStatisticsPage extends BasePageAdminPanel {

    @Getter
    @FindBy(xpath = ".//h2[@class='streetcodeName']")
    protected WebElement title;

    @Getter
    @FindBy(xpath = ".//img[@class='streetcodeImg no_exif_metadata']")
    protected WebElement image;

    @Getter
    @FindBy(xpath = ".//div[@class='statisticTableWrapper']//h2[@class='streetcodeName' and text()='Статистика']")
    protected WebElement statisticHeader;

    @Getter
    protected final List<WebElement> statisticTitles = new ArrayList<>();
    @FindBy(xpath = ".//thead[@class='ant-table-thead']//th[@class='ant-table-cell']")
    private List<WebElement> statisticTitlesNode;

    protected NoDataComponent noDataComponent;
    @FindBy(xpath = "//tr[@class='ant-table-placeholder']//div[@class='ant-empty-description']")
    private WebElement noData;

    public HistoryCodesAdminPanelStatisticsPage(WebDriver driver) {
        super(driver);

        for (WebElement item : statisticTitlesNode) {
            if (item.isDisplayed()) {
                statisticTitles.add(item);
            }
        }
    }

    public NoDataComponent getNoDataComponent() {
        if (noDataComponent == null) {
            WebElement root = noData;
            noDataComponent = new NoDataComponent(driver, root);
        }
        return noDataComponent;
    }
}
