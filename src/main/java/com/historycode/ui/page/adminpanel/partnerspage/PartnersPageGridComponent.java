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
        partnersRowComponents = getPartnersRows(driver);
    }

    public List<PartnersRowComponent> getPartnersRows(WebDriver driver) {

        List<WebElement> gridRows = driver.findElements(By.xpath("./tbody//tr"));

        for (WebElement rootElement : gridRows) {
            partnersRowComponents.add(new PartnersRowComponent(driver, rootElement));
        }
        return partnersRowComponents;
    }

    public PartnersRowComponent getRowById(int id) {
        return partnersRowComponents.get(id);
    }

    public int getRowCount() {
        return partnersRowComponents.size();
    }

    public PartnersPageGridComponent clickNextPage() {
        pagination.clickNextPage();
        return new PartnersPageGridComponent(driver, rootElement);
    }

    public PartnersPageGridComponent clickPrevPage() {
        pagination.clickPrevPage();
        return new PartnersPageGridComponent(driver, rootElement);
    }

    public PartnersPageGridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        return new PartnersPageGridComponent(driver, rootElement);
    }
}
