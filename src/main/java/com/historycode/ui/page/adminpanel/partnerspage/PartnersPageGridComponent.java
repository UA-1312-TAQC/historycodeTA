package com.historycode.ui.page.adminpanel.partnerspage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class PartnersPageGridComponent extends BaseGridComponent {

    @Getter
    private List<PartnersRowComponent> partnersRowComponents;

    @FindBy(xpath = "//tbody//tr")
    protected List<WebElement> partnerRowNodes;

    public PartnersPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        sleep(3000); // Потрібно зачикати поки сторінка завантажиться
        this.partnersRowComponents = initializePartnersRowComponents(driver, partnerRowNodes);
    }

    private List<PartnersRowComponent> initializePartnersRowComponents(WebDriver driver, List<WebElement> partnerRowNodes) {
        List<PartnersRowComponent> components = new ArrayList<>();
        for (WebElement element : partnerRowNodes) {
            components.add(new PartnersRowComponent(driver, element));
        }
        return components;
    }

    public PartnersRowComponent findUserByName(String name){
        for(PartnersRowComponent item: getPartnersRowComponents()){
            if(item.getNameText().equals(name))
                return item;
        }
        return null;
    }

    public PartnersRowComponent getRowById(int id) {
        return partnersRowComponents.get(id);
    }

    public int getRowCount() {
        return partnersRowComponents.size();
    }

    public void clickNextPage() {
        sleep(1000);
        pagination.clickNextPage();
        waitUntilElementInvisible(partnersRowComponents.get(0).getName());
    }

    public void clickPrevPage() {
        sleep(1000);
        pagination.clickPrevPage();
        waitUntilElementInvisible(partnersRowComponents.get(0).getName());
    }

    public void clickPaginationItem(int index) {
        sleep(1000);
        pagination.clickPaginationItem(index);
        waitUntilElementInvisible(partnersRowComponents.getLast().getName());
    }

    public void clickLastPage(){
        sleep(6000);
        pagination.clickLastPage();
        waitUntilElementInvisible(partnersRowComponents.getLast().getName());
        sleep(2000);
    }
}
