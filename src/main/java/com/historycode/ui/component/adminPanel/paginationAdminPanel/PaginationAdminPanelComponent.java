package com.historycode.ui.component.adminPanel.paginationAdminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaginationAdminPanelComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = "//li[@title = 'Next Page']")
    WebElement nextPage;

    @Getter
    @FindBy(xpath = "//li[@title = 'Previous Page']")
    WebElement prevPage;

    @Getter
    @FindBy(xpath = "//li[@title = 'Previous 5 Pages']")
    WebElement prevFivePages;

    @Getter
    @FindBy(xpath = "//li[@title = 'Next 5 Pages']")
    WebElement nextFivePages;

    @FindBy(xpath = "//*[@title and number(@title) = number(@title)]")
    List<WebElement> paginationItems;

    public PaginationAdminPanelComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickNextPage() {
        nextPage.click();
    }

    public void clickPrevPage() {
        prevPage.click();
    }

    public void clickPrevFivePages() {
        prevFivePages.click();
    }

    public void clickNextFivePages() {
        nextFivePages.click();
    }

    public void clickPaginationItem(int index) {
        paginationItems.get(index).click();
    }
}
