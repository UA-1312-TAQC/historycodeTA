package com.historycode.ui.page.privacyPolicyPage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SectionListComponent extends BaseComponent {

    @FindBy(xpath = "./section")
    private List<WebElement> sectionComponent;

//    @FindBy(xpath = "./section/div[@class='title']")
//    private List<WebElement> sectionComponentTitles;



//    @FindBy(xpath = "./div[@class='content']")
//    private List<WebElement> contentComponent;
    private List<SectionListItem> sectionListItems;

    public SectionListComponent(WebDriver driver, WebElement rootElement) {

        super(driver, rootElement);
        for (WebElement element : sectionComponent) {
            sectionListItems.add(new SectionListItem(driver, element));
        }
    }

    public SectionListItem getSectionByTitle(String title){
        for (SectionListItem item: sectionListItems){
            if (item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }


//
//    public List<String> SectionsName() {
//        List<String> ListOfSections = new ArrayList<>();
//        for (WebElement element : sectionComponentTitles) {
//            ListOfSections.add(element.getText());
//        }
//
//
//        return ListOfSections;
//    }
//
//    public List<String> SectionsText() {
//        List<String> ListOfSectionsText = new ArrayList<>();
//        for (WebElement element : sectionComponent) {
//            WebElement contentElement = element.findElement(By.xpath("./div[@class='content']"));
//            ListOfSectionsText.add(contentElement.getText());
//        }
//        return ListOfSectionsText;
//    }
//
//    public List<String> SectionsLinks() {
//        List<String> ListOfSectionsLinks = new ArrayList<>();
//        for (WebElement element : sectionComponent) {
//            List<WebElement> links = element.findElements(By.xpath("./div[@class='content']/a[@class='link']"));
//            for (WebElement link : links) {
//                ListOfSectionsLinks.add(link.getText());
//            }
//        }
//        return ListOfSectionsLinks;
//    }
}
