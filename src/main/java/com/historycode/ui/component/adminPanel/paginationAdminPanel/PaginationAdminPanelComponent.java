package com.historycode.ui.component.adminPanel.paginationAdminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
        if (!hasNextPage()) {
            throw new IllegalStateException("Next page is not available");
        }
        nextPage.click();
    }

    public void clickPrevPage() {
        if (!hasPrevPage()) {
            throw new IllegalStateException("Previous page is not available");
        }
        prevPage.click();
    }

    public void clickPrevFivePages() {
        if (!hasPrevFivePages()) {
            throw new IllegalStateException("Previous pages are not available");
        }
        prevFivePages.click();
    }

    public void clickNextFivePages() {
        if (!hasNextFivePages()) {
            throw new IllegalStateException("Next pages are not available");
        }
        nextFivePages.click();
    }

    public void clickPaginationItem(int index) {
        if (!isValidPaginationItem(index)) {
            throw new IllegalArgumentException("Pagination item with index " + index + " is not available or invalid");
        }
        paginationItems.get(index).click();
    }


    private boolean isPaginationButtonEnabled(WebElement button) {
        try {
            return button.isDisplayed() && button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasPrevPage() {
        return isPaginationButtonEnabled(prevPage);
    }

    public boolean hasNextPage() {
        return isPaginationButtonEnabled(nextPage);
    }

    public boolean hasPrevFivePages() {
        return isPaginationButtonEnabled(prevFivePages);
    }

    public boolean hasNextFivePages() {
        return isPaginationButtonEnabled(nextFivePages);
    }

    private boolean isValidPaginationItem(int index) {
        if (index < 0 || index >= paginationItems.size()) {
            return false;
        }
        WebElement item = paginationItems.get(index);
        return item.isDisplayed() && item.isEnabled();
    }

}
