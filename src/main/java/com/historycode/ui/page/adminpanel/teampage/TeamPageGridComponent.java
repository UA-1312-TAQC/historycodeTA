package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public class TeamPageGridComponent extends BaseGridComponent {

    @Getter
    protected List<TeamRowComponent> teamRowComponents;

    @FindBy(xpath = "//tbody//tr")
    protected List<WebElement> teamRowNodes;

    @FindBy(xpath = "//ul[contains(@class, 'ant-pagination')]//li[contains(@class, 'ant-pagination-item')]")
    protected List<WebElement> paginationItems;

    public TeamPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        log.debug("Team page - Table initialization started");
        teamRowComponents = new ArrayList<>();
        for(WebElement element: teamRowNodes){
            teamRowComponents.add(new TeamRowComponent(driver, element));
        }
        log.debug("Team page - Table initialization finished");
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
        if(!teamRowComponents.isEmpty()){
            teamRowComponents.getFirst().waitUntilRowDisappears();
        }
        return new TeamPageGridComponent(driver, rootElement);
    }

    public TeamPageGridComponent clickPrevPage() {
        pagination.clickPrevPage();
        if(!teamRowComponents.isEmpty()){
            teamRowComponents.getFirst().waitUntilRowDisappears();
        }
        return new TeamPageGridComponent(driver, rootElement);
    }

    public TeamPageGridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        if(!teamRowComponents.isEmpty()){
            teamRowComponents.getFirst().waitUntilRowDisappears();
        }
        return new TeamPageGridComponent(driver, rootElement);
    }

    public TeamPageGridComponent clickPrevFivePages() {
        pagination.clickPrevFivePages();
        if(!teamRowComponents.isEmpty()){
            teamRowComponents.getFirst().waitUntilRowDisappears();
        }
        return new TeamPageGridComponent(driver, rootElement);
    }

    public TeamPageGridComponent clickNextFivePages() {
        pagination.clickNextFivePages();
        if(!teamRowComponents.isEmpty()){
            teamRowComponents.getFirst().waitUntilRowDisappears();
        }
        return new TeamPageGridComponent(driver, rootElement);
    }

    public void clickLastPage(){
        pagination.clickLastPage();
        if(!teamRowComponents.isEmpty()){
            teamRowComponents.getFirst().waitUntilRowDisappears();
        }
    }
}
