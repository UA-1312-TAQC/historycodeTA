package com.historycode.ui.page.adminpanel.partnerspage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;

import lombok.Getter;

public class PartnersPageGridComponent extends BaseGridComponent {

    @Getter
    private List<PartnersRowComponent> partnersRowComponents;

    public PartnersPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        partnersRowComponents = updatePartnersRows(driver);
    }

    public List<PartnersRowComponent> updatePartnersRows(WebDriver driver) {

        List<WebElement> gridRows = driver.findElements(By.xpath("//tbody//tr"));

        for (WebElement element : gridRows) {
            partnersRowComponents.add(new PartnersRowComponent(driver, element));
        }
        return partnersRowComponents;
    }

    public PartnersRowComponent getRowById(int id) {
        return partnersRowComponents.get(id);
    }

    public int getRowCount() {
        return partnersRowComponents.size();
    }

    public void clickNextPage() {
        pagination.clickNextPage();
        this.partnersRowComponents = updatePartnersRows(driver);
    }

    public void clickPrevPage() {
        pagination.clickPrevPage();
        this.partnersRowComponents = updatePartnersRows(driver);
    }

    public void clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        this.partnersRowComponents = updatePartnersRows(driver);
    }
}
