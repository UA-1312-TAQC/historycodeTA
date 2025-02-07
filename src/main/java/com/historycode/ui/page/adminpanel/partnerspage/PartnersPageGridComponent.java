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
        sleep(1000);
        this.partnersRowComponents = initializePartnersRowComponents(driver, partnerRowNodes);
    }

    private List<PartnersRowComponent> initializePartnersRowComponents(WebDriver driver, List<WebElement> partnerRowNodes) {
        List<PartnersRowComponent> components = new ArrayList<>();
        for (WebElement element : partnerRowNodes) {
            components.add(new PartnersRowComponent(driver, element));
        }
        return components;
    }

    public PartnersRowComponent findPartnerByName(String name){
        sleep(1000);
        for(PartnersRowComponent item: getPartnersRowComponents()){
            if(item.getNameText().equals(name))
                return item;
        }
        return null;
    }

    public PartnersRowComponent getRowById(int id) {
        return partnersRowComponents.get(id);
    }

    public void clickNextPage() {
        waitUntilElementClickable(pagination.getNextPage());
        pagination.clickNextPage();
        waitUntilElementInvisible(partnerRowNodes.getFirst());
    }

    public void clickPrevPage() {
        waitUntilElementClickable(pagination.getPrevPage());
        pagination.clickPrevPage();
        waitUntilElementInvisible(partnerRowNodes.getFirst());
    }

    public void clickPaginationItem(int index) {
        waitUntilElementVisible(pagination.getPaginationItems().getLast());
        pagination.clickPaginationItem(index);
        waitUntilElementInvisible(partnerRowNodes.getFirst());
    }

    public void clickLastPage(){
        pagination.clickLastPage();
        waitUntilPageLouder();
    }
}
