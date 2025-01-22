package com.historycode.ui.page.partnerPage;

import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PartnerPage extends BasePage {

    private static final String LOGO_XPATH = ".//img";
    private static final String DESCRIPTION_XPATH = ".//div[@class='description']/p";

    @FindBy(xpath = "//div[@class='otherPartnersBlock']/div[@class='partnersItem']")
    protected List<WebElement> notKeyPartners;

    @Getter
    @FindBy(xpath = "//div[@class='keyPartnersBlock']//img[@alt='Гадяцька міська рада']")
    private WebElement ConstantKeyPartners;

    @FindBy(xpath = "//div[@class='keyPartnersBlock']/div[@class='partnersItem']")
    protected List<WebElement> keyPartners;

    @FindBy(xpath = "//div[@class='ant-popover-content']")
    protected WebElement popoverContainer;

    public PartnerPage(WebDriver driver) {
        super(driver);
    }

    public void hoverOverNotKeyPartner(String alt) {
        WebElement logo = findLogo(notKeyPartners, alt);
        hoverOverLogo(logo);
    }

    public void hoverOverKeyPartner(String alt) {
        WebElement logo = findLogo(keyPartners, alt);
        hoverOverLogo(logo);
    }

    public String getPopoverDescription() {
        waitUntilElementVisible(popoverContainer);
        WebElement description = popoverContainer.findElement(By.xpath(DESCRIPTION_XPATH));
        return description.getText();
    }

    protected void hoverOverLogo (WebElement imageElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(imageElement).perform();
        waitUntilElementVisible(popoverContainer);
    }

    protected WebElement findLogo (List<WebElement> listOfLogo, String alt) {
        if (alt == null || alt.isEmpty()) {
            throw new IllegalArgumentException("Alt text must not be null or empty");
        }
        for (WebElement logoContainer : listOfLogo) {
            WebElement logo = logoContainer.findElement(By.xpath(LOGO_XPATH));
            String logoAltText = logo.getAttribute("alt");
            if (logoAltText.contains(alt)) {
                return logo;
            }
        }
        throw new NoSuchElementException("Logo with alt text '" + alt + "' not found");
    }
}
