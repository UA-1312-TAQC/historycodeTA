package com.historycode.ui.page.adminpanel.partnerspage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;

import lombok.Getter;

@Getter
public class PartnersPageGridComponent extends BaseGridComponent {

    protected List<PartnersRowComponent> partnersRowComponents;

    public PartnersPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<PartnersRowComponent> getPartnersRowComponents() {
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
    }

    public void clickPrevPage() {
        pagination.clickPrevPage();
    }

    public void clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
    }

}
