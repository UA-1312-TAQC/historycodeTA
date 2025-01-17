package com.historycode.ui.page.partnerPage;

import com.historycode.ui.page.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PartnerPage extends BasePage {

    @FindBy(xpath = "//div[@Class='otherPartnersBlock']/div[@class='partnersItem']")
    protected List<WebElement> notKeyPartners;

    @FindBy(xpath = "//div[@class='ant-popover-content']")
    protected WebElement popoverContainer;

    public PartnerPage(WebDriver driver) {
        super(driver);
    }

    public void hoverOverNotKeyPartner(String alt) {
        WebElement logo = findLogo(notKeyPartners, alt);
        hoverOverLogo(logo);
    }

    public String getPopoverDescription() {
        WebElement description = popoverContainer.findElement(By.xpath(".//div[@class='description']//p"));
        System.out.printf(description.getText());
        return description.getText();
    }

    protected void hoverOverLogo (WebElement imageElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(imageElement).perform();
    }

    protected WebElement findLogo (List<WebElement> ListOfLogo, String alt) {
        for (WebElement logoContainer : ListOfLogo) {

            WebElement logo = logoContainer.findElement(By.xpath(".//img"));
            String logoAltText = logo.getAttribute("alt");

            if (logoAltText.contains(alt)) {
                return logo;
            }
        }
        return null;
    }





}
