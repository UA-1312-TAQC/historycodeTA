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

    @FindBy(xpath = ".//section")
    private List<WebElement> sectionComponent;

    @FindBy(xpath = ".//div[@class='content']")
    private List<WebElement> contentComponent;

    public SectionListComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<String> SectionsName() {
        List<String> ListOfSections = new ArrayList<>();
        for (WebElement element : sectionComponent) {
            try {
                WebElement titleElement = element.findElement(By.xpath(".//div[@class='title']"));
                ListOfSections.add(titleElement.getText());
            } catch (NoSuchElementException e) {
                System.out.println("Section title not found: " + e.getMessage());
                ListOfSections.add("Title not found");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                ListOfSections.add("Error processing section");
            }
        }
        return ListOfSections;
    }

    public List<String> SectionsText() {
        List<String> ListOfSectionsText = new ArrayList<>();
        for (WebElement element : sectionComponent) {
            try {
                WebElement contentElement = element.findElement(By.xpath(".//div[@class='content']"));
                ListOfSectionsText.add(contentElement.getText());
            } catch (NoSuchElementException e) {
                System.out.println("Section content not found: " + e.getMessage());
                ListOfSectionsText.add("Content not found");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                ListOfSectionsText.add("Error processing content");
            }
        }
        return ListOfSectionsText;
    }

    public List<String> SectionsLinks() {
        List<String> ListOfSectionsLinks = new ArrayList<>();
        for (WebElement element : contentComponent) {
            try {
                List<WebElement> links = element.findElements(By.xpath(".//a[@class='link']"));

                if (!links.isEmpty()) {
                    for (WebElement link : links) {
                        ListOfSectionsLinks.add(link.getText());
                    }
                } else {
                    System.out.println("No links found in content.");
                    ListOfSectionsLinks.add("No links");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Link element not found: " + e.getMessage());
                ListOfSectionsLinks.add("Link not found");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                ListOfSectionsLinks.add("Error processing links");
            }
        }

        return ListOfSectionsLinks;
    }
}
