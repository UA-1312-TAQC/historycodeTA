package com.historycode.ui.page.adminpanel.editorpage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SectionsComponent extends BaseComponent {
    private static final String CATEGORY_XPATH = "//div[@data-node-key='1']//div[@role='tab']";
    private static final String TAGS_XPATH = "//div[@data-node-key='2']//div[@role='tab']";
    private static final String CONTEXTS_XPATH = "//div[@data-node-key='3']//div[@role='tab']";
    private static final String POSITIONS_XPATH = "//div[@data-node-key='4']//div[@role='tab']";

    private WebElement categories;
    private WebElement tags;
    private WebElement contexts;
    private WebElement positions;

    public SectionsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.categories = driver.findElement(By.xpath(CATEGORY_XPATH));
        this.tags = driver.findElement(By.xpath(TAGS_XPATH));
        this.contexts = driver.findElement(By.xpath(CONTEXTS_XPATH));
        this.positions = driver.findElement(By.xpath(POSITIONS_XPATH));
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

    public void clickCategories(){
        categories.click();
    }

    public void clickTags(){
        tags.click();
    }

    public void clickPositions(){
        positions.click();
    }

    public void clickContexts(){
        contexts.click();
    }
}
