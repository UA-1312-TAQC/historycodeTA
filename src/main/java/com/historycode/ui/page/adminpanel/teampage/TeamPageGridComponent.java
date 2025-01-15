package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamPageGridComponent extends BaseGridComponent {

    protected List<TeamRowComponent> teamRowComponents;

    @FindBy(xpath = "//tbody//tr")
    protected List<WebElement> teamRowNodes;
    @FindBy(xpath = "(//button[contains(@class, 'ant-pagination-item-link')])[1][not(@disabled)]")
    protected WebElement prevPageButton;
    @FindBy(xpath = "(//button[contains(@class, 'ant-pagination-item-link')])[2][not(@disabled)]")
    protected WebElement nextPageButton;
    @FindBy(xpath = "//ul[contains(@class, 'ant-pagination')]//li[contains(@class, 'ant-pagination-item')]")
    protected List<WebElement> paginationItems;

    public TeamPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

        teamRowComponents = new ArrayList<>();
        for(WebElement element: teamRowNodes){
            teamRowComponents.add(new TeamRowComponent(driver, element));
        }
    }

    public List<TeamRowComponent> getTeamRowComponents() {
        return teamRowComponents;
    }

    public List<WebElement> getRowNodes() { return teamRowNodes; }

    public TeamRowComponent getRowById(int id) {
        return teamRowComponents.get(id);
    }

    public int getRowsCount() {
        return teamRowComponents.size();
    }

    public TeamPageGridComponent clickNextPage() {
        pagination.clickNextPage();
        return new TeamPageGridComponent(driver, rootElement);
    }

    public TeamPageGridComponent clickPrevPage() {
        pagination.clickPrevPage();
        return new TeamPageGridComponent(driver, rootElement);
    }

    public TeamPageGridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        return new TeamPageGridComponent(driver, rootElement);
    }

    public TeamPageGridComponent clickPrevFivePages() {
        pagination.clickPrevFivePages();
        return new TeamPageGridComponent(driver, rootElement);
    }

    public TeamPageGridComponent clickNextFivePages() {
        pagination.clickNextFivePages();
        return new TeamPageGridComponent(driver, rootElement);
    }

    public boolean hasPrevPage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(nextPageButton));
            wait.until(ExpectedConditions.elementToBeClickable(prevPageButton));
            return prevPageButton.isDisplayed() && prevPageButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasNextPage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(nextPageButton));
            wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
            return nextPageButton.isDisplayed() && nextPageButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidPageIndex(int index) {
        try {
            WebElement lastPaginationItem = paginationItems.getLast();
            int lastPageNumber = Integer.parseInt(lastPaginationItem.getText().trim());

            return index >= 1 && index <= lastPageNumber;
        } catch (Exception e) {
            return false;
        }
    }

}
