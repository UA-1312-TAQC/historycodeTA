package com.historycode.ui.page.adminpanel.editorpage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SectionsComponent extends BaseComponent {
    private static final String CATEGORY_XPATH = ".//div[@data-node-key='1']//div[@role='tab']";
    private static final String TAGS_XPATH = ".//div[@data-node-key='2']//div[@role='tab']";
    private static final String CONTEXTS_XPATH = ".//div[@data-node-key='3']//div[@role='tab']";
    private static final String POSITIONS_XPATH = ".//div[@data-node-key='4']//div[@role='tab']";

    @FindBy(xpath = CATEGORY_XPATH)
    private WebElement categories;
    @FindBy(xpath = TAGS_XPATH)
    private WebElement tags;
    @FindBy(xpath = CONTEXTS_XPATH)
    private WebElement contexts;
    @FindBy(xpath = POSITIONS_XPATH)
    private WebElement positions;

    public SectionsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getCategoriesText() {
        return categories.getText();
    }

    public String getTagsText() {
        return tags.getText();
    }

    public String getContextsText() {
        return contexts.getText();
    }

    public String getPositionsText() {
        return positions.getText();
    }

    public void clickCategories() {
        BasePage.moveToElement(driver, categories);
        categories.click();
    }

    public void clickTags() {
        BasePage.moveToElement(driver, tags);
        tags.click();
    }

    public void clickPositions() {
        BasePage.moveToElement(driver, positions);
        positions.click();
    }

    public void clickContexts() {
        BasePage.moveToElement(driver, contexts);
        contexts.click();
    }
}
