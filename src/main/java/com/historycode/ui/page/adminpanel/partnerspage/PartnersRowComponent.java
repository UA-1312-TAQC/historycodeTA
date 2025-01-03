package com.historycode.ui.page.adminpanel.partnerspage;

import java.util.List;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.teampage.TeamSocialMediaComponent;

import lombok.Getter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class PartnersRowComponent extends BaseComponent {
    @FindBy(xpath = "//td[1]//div[@class='partner-table-item-name']//p")
    private WebElement name;
    @FindBy(xpath = "//td[2]//a[@class = 'site-link']")
    private WebElement link;
    @FindBy(xpath = "//td[2]//a[@class = 'site-link']")
    private WebElement logo;
    @FindBy(xpath = "/td[4]//div[@class = 'partner-links']")
    List<PartnersSocialMediaComponent> socialMediaComponents;
    @FindBy(xpath = "//td[5]//span[@aria-label = 'delete']")
    private WebElement deleteAction;
    @FindBy(xpath = "//td[5]//span[@aria-label = 'edit']")
    private WebElement editAction;

    public PartnersRowComponent(WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
    }

    /*TODO. Do function which return list of objects social media */
    public void getSocialMediaLinks() {
    }

    public DeleteItemModal clickDelete() {
        deleteAction.click();
        return new DeleteItemModal(driver, rootElement);
    }
    
    /*TODO. rewrite this function used modal class EditPartnerModal */
    public void clickEdit() {
        editAction.click();
    }

}
