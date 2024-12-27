package com.historycode.ui.page.privacyPolicyPage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TitleContainerComponent extends BaseComponent {

    @FindBy(xpath = ".//div[@class='title']")
    private WebElement titleContainer;

    public TitleContainerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String titleName() {
        try {
            WebElement nameElement = titleContainer.findElement(By.xpath(".//div[@class='titleBig']"));
            return nameElement.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Title element not found.");
            return "Title not found";
        }
     }

     public String subTitle() {
        try {
            WebElement subTitleElement = titleContainer.findElement(By.xpath(".//div[@class='subTitle']"));
            return subTitleElement.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Subtitle element not found.");
            return "Subtitle not found";
        }
     }
     public String disclaimer() {
        try {
            WebElement disclaimerElement = titleContainer.findElement(By.xpath(".//div[@class='disclaimer']"));
            return disclaimerElement.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Disclaimer element not found.");
            return "Disclaimer not found";
        }
    }



}
