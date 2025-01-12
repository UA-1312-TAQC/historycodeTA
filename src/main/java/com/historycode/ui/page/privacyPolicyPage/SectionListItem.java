package com.historycode.ui.page.privacyPolicyPage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SectionListItem extends BaseComponent {

    @FindBy(xpath = "./div[@class='title']")
    private WebElement title;

    @FindBy(xpath = "./div[@class='content']")
    private WebElement content;

    @FindBy(xpath = "./div[@class='content']/a[@class='link']")
    private List<WebElement> links;

    public SectionListItem(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public String getContent() {
        return content.getText();
    }

    public String getTitle() {
        return title.getText();
    }

    public List<String> getContentLinks() {
        List<String> ListOfSectionsLinks = new ArrayList<>();
        for (WebElement link : links) {

            ListOfSectionsLinks.add(link.getText());

        }
        return ListOfSectionsLinks;
    }

}
