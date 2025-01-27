package com.historycode.ui.page.streetCodePage.modals;

import com.historycode.ui.component.BaseModal;
import com.historycode.ui.page.streetCodePage.components.RelatedPersonasCardComponent;
import com.historycode.ui.page.streetCodePage.components.TagsPersonasCardComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class KeywordPersonasModal extends BaseModal {
    @FindBy(xpath = ".//div[@class='tagModalContainer']//button")
    private List<WebElement> tagsNode;

    @Getter
    @FindBy(xpath = ".//div[@class='relatedFiguresByTagsContentContainer']")
    private WebElement personsCardsContainer;

    @FindBy(xpath = ".//div[@class='relatedFiguresByTagsContentContainer']//a[1]")
    private List<WebElement> personCards;

    @Getter
    private List<RelatedPersonasCardComponent> persons;

    public KeywordPersonasModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<TagsPersonasCardComponent> getPersons() {
        //wait.until(ExpectedConditions.visibilityOf(personsCardsContainer));
        waitUntilElementVisible(personsCardsContainer);
        List<WebElement> currentPersonCards = driver.findElements(
                By.xpath(".//div[@class='relatedFiguresByTagsContentContainer']/a")
        );

        return currentPersonCards.stream()
                .map(card -> new TagsPersonasCardComponent(driver, card))
                .collect(Collectors.toList());
    }

    @Step("Get selected tag in modal")
    public String getSelectedTag() {
        try {
            WebElement selectedTag = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'slick-active')]//button[contains(@class, 'tagModalItem')]")
            ));
            return selectedTag.getText();
        } catch (Exception e) {
            return null;
        }
    }

}
