package com.historycode.ui.page.adminpanel.editorpage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.EditorBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SectionsComponent extends BaseComponent {

    @FindBy(xpath = ".//div[@data-node-key='1']//div[@role='tab']")
    private WebElement categories;
    @FindBy(xpath = ".//div[@data-node-key='2']//div[@role='tab']")
    private WebElement tags;
    @FindBy(xpath = ".//div[@data-node-key='3']//div[@role='tab']")
    private WebElement contexts;
    @FindBy(xpath = ".//div[@data-node-key='4']//div[@role='tab']")
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
        moveToElement( categories);
        categories.click();
    }

    public void clickTags() {
        moveToElement( tags);
        tags.click();
    }

    public void clickPositions() {
        moveToElement( positions);
        positions.click();
    }

    public void clickContexts() {
        moveToElement( contexts);
        contexts.click();
    }
}
