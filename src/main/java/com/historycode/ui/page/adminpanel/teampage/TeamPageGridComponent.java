package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.utils.customExpectedConditions.CustomExpectedConditions;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamPageGridComponent extends BaseGridComponent {

    @Getter
    protected List<TeamRowComponent> teamRowComponents;

    @FindBy(xpath = "//tbody//tr")
    protected List<WebElement> teamRowNodes;

    @FindBy(xpath = "//ul[contains(@class, 'ant-pagination')]//li[contains(@class, 'ant-pagination-item')]")
    protected List<WebElement> paginationItems;

    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);


    public TeamPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        teamRowComponents = new ArrayList<>();
        wait.until(CustomExpectedConditions.StalenessOfElementLocatedBy(By.cssSelector("#loadingGif")));
        for(WebElement element: teamRowNodes){
            teamRowComponents.add(new TeamRowComponent(driver, element));
        }
    }


    public TeamRowComponent findUserByName(String name){
        for(TeamRowComponent item: getTeamRowComponents()){
            if(item.getLastFirstName().equals(name))
                return item;
        }
        return null;
    }

    public List<WebElement> getRowNodes() {
        return teamRowNodes;
    }

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

    public void clickLastPage(){
        pagination.clickLastPage();
    }
}
