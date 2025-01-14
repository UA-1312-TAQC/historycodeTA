package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.Routable;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamPageGridComponent extends BaseGridComponent {

    protected List<TeamRowComponent> teamRowComponents;

    @FindBy(xpath = "//tbody//tr")
    protected List<WebElement> teamRowNodes;

    public TeamPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        teamRowComponents = new ArrayList<>();
        List<WebElement> tempRowNodes = driver.findElements(By.xpath("//tbody//tr")); //element for debugging
        //System.out.println("List size " + tempRowNodes.size());
        for(WebElement element: tempRowNodes){
            teamRowComponents.add(new TeamRowComponent(driver, element));
        }
    }

    public List<TeamRowComponent> getTeamRowComponents() {
        return teamRowComponents;
    }


    public TeamRowComponent findUserByName(String name){
        List<TeamRowComponent> temp = getTeamRowComponents();
        for(TeamRowComponent item: getTeamRowComponents()){
            //TODO remove this
            System.out.println(item.getLastFirstName());
            if(item.getLastFirstName().equals(name))
                return item;
        }
        return null;
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

    public void clickLastPage(){
        pagination.clickLastPage();
        //return new TeamPageGridComponent(driver, rootElement);
    }
}
