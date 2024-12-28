package com.historycode.ui.component.adminPanel.paginationAdminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class PaginationAdminPanelComponent extends BaseComponent {
    @FindBy(xpath = "//li[@title = 'Next Page']")
    WebElement nextPage;
    @FindBy(xpath = "//li[@title = 'Previous Page']")
    WebElement prevPage;

    //TODO ask about title="Previous 5 Pages" ...

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

    public void clickPaginationItem(int index) {
        paginationItems.get(index).click();
    }
}
