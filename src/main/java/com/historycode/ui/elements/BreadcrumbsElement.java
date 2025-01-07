package com.historycode.ui.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BreadcrumbsElement extends BaseElement{
    @FindBy(xpath = ".//nav[@class='ant-breadcrumb breadcrumbContainer css-k7429z']")
    private WebElement breadcrumbsContainer;

    @FindBy(xpath = ".//a[@class='ant-breadcrumb-link activeLink']")
    private WebElement catalogLink;

    @FindBy(xpath = ".//span[@class='ant-breadcrumb-link']")
    private WebElement currentPage;

    public BreadcrumbsElement(WebDriver driver) {
        super(driver);
    }

    public String getCurrentPageTitle() {
        return currentPage.getText();
    }

    public void clickCatalog() {
        catalogLink.click();
    }

    public String getCatalogTitle() {
        return catalogLink.getText();
    }

    public boolean isCatalogLinkDisplayed() {
        return catalogLink.isDisplayed();
    }

    public boolean isCurrentPageDisplayed() {
        return currentPage.isDisplayed();
    }

    public String getBreadcrumbsPath() {
        return breadcrumbsContainer.getText();
    }

    public boolean isBreadcrumbsDisplayed() {
        return breadcrumbsContainer.isDisplayed();
    }
}
