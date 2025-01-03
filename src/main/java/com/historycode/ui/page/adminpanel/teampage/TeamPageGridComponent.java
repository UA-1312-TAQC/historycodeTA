package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        for(WebElement element: teamRowNodes ){
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

    public void clickNextPage() {
        pagination.clickNextPage();
    }

    public void clickPrevPage() {
        pagination.clickPrevPage();
    }

    public void clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
    }

    /*public void clickPrevFivePages() {
        pagination.clickPrevFivePages();
    }

    public void clickNextFivePages() {
        pagination.clickNextFivePages();
    }*/
}
