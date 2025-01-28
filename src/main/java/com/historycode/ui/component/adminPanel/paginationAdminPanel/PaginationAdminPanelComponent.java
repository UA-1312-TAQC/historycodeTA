package com.historycode.ui.component.adminPanel.paginationAdminPanel;

import com.historycode.ui.component.BaseComponent;
import io.qameta.allure.Step;
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

    @Getter
    @FindBy(xpath = "//*[@title and number(@title) = number(@title)]")
    List<WebElement> paginationItems;

    public PaginationAdminPanelComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickNextPage() {
        if (!hasNextPage()) {
            throw new IllegalStateException("Next page is not available");
        }
        scrollToElement(nextPage);
        nextPage.click();
    }

    public void clickPrevPage() {
        if (!hasPrevPage()) {
            throw new IllegalStateException("Previous page is not available");
        }
        scrollToElement(prevPage);
        prevPage.click();
    }

    @Step("Click on the prev page of the pagination")
    public void clickPrevFivePages() {
        if (!hasPrevFivePages()) {
            throw new IllegalStateException("Previous pages are not available");
        }
        scrollToElement(prevFivePages);
        prevFivePages.click();
    }

    @Step("Click on the next page of the pagination")
    public void clickNextFivePages() {
        if (!hasNextFivePages()) {
            throw new IllegalStateException("Next pages are not available");
        }
        scrollToElement(nextFivePages);
        nextFivePages.click();
    }

    @Step("Click on the {index} page of the pagination")
    public void clickPaginationItem(int index) {
        if (!isValidPaginationItem(index)) {
            throw new IllegalArgumentException("Pagination item with index " + index + " is not available or invalid");
        }
        scrollToElement(prevPage);
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

    @Step("Click on the last page of the pagination")
    public void clickLastPage(){
        scrollToElement(nextPage);
        waitUntilElementClickable(paginationItems.getLast());
        paginationItems.getLast().click();
    }
}
