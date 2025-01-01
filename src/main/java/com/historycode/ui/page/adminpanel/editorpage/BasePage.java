package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.editorpage.components.SectionsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage extends BasePageAdminPanel {
    private static final String ROOT_SECTIONS_XPATH = "//div[@class='ant-tabs-nav-list']";
    private static final String ROOT_ADD_BUTTON_XPATH = "//div[@class='ant-tabs-content-holder']//div[@class='container-justify-end']";
    private static final String ROOT_GRID_XPATH = "//div[contains(@class, 'ant-table-wrapper')]";
    public WebElement rootAddButton;
    private WebElement rootSections;
    public WebElement rootGrid;
    private SectionsComponent sections;

    public BasePage(WebDriver driver) {
        super(driver);
        this.rootAddButton = driver.findElement(By.xpath(ROOT_ADD_BUTTON_XPATH));
        this.rootSections = driver.findElement(By.xpath(ROOT_SECTIONS_XPATH));
        this.rootGrid = driver.findElement(By.xpath(ROOT_GRID_XPATH));
        this.sections = new SectionsComponent(driver, rootSections);
    }

    public CategoriesPage moveToCategories(){
        sections.clickCategories();
        //TODO Maybe add waiting
        return new CategoriesPage(driver);
    }

    public TagsPage moveToTags() throws InterruptedException {
        sections.clickTags();
        Thread.sleep(2000);
        return new TagsPage(driver);
    }

    public PositionsPage moveToPositions() throws InterruptedException {
        sections.clickPositions();
        Thread.sleep(2000);
        return new PositionsPage(driver);
    }

    public ContextsPage moveToContexts() throws InterruptedException {
        sections.clickContexts();
        Thread.sleep(2000);
        return new ContextsPage(driver);
    }
    //TODO Add pagination component
}
