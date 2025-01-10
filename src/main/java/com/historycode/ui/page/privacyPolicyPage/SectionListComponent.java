package com.historycode.ui.page.privacyPolicyPage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SectionListComponent extends BaseComponent {

    @FindBy(xpath = "./section")
    private List<WebElement> sectionComponent;

    @FindBy(xpath = "./div[@class='content']")
    private List<WebElement> contentComponent;

    public SectionListComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<String> SectionsName() {
        List<String> ListOfSections = new ArrayList<>();
        for (WebElement element : sectionComponent) {
            WebElement titleElement = element.findElement(By.xpath("./div[@class='title']"));
            ListOfSections.add(titleElement.getText());
        }
        return ListOfSections;
    }

    public List<String> SectionsText() {
        List<String> ListOfSectionsText = new ArrayList<>();
        for (WebElement element : sectionComponent) {
            WebElement contentElement = element.findElement(By.xpath("./div[@class='content']"));
            ListOfSectionsText.add(contentElement.getText());
        }
        return ListOfSectionsText;
    }

    public List<String> SectionsLinks() {
        List<String> ListOfSectionsLinks = new ArrayList<>();
        for (WebElement element : contentComponent) {
            List<WebElement> links = element.findElements(By.xpath("./a[@class='link']"));
            for (WebElement link : links) {
                ListOfSectionsLinks.add(link.getText());
            }
        }
        return ListOfSectionsLinks;
    }
}
