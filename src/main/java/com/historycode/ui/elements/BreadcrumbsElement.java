package com.historycode.ui.elements;

//import com.historycode.ui.page.adminpanel.editorpage.BaseEditorPage;
import com.historycode.ui.page.streetcodecatalogpage.StreetCodeCatalogPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BreadcrumbsElement extends BaseElement {

    @FindBy(xpath = ".//a[@class='ant-breadcrumb-link activeLink']")
    private WebElement catalogLink;

    @FindBy(xpath = ".//span[@class='ant-breadcrumb-link']")
    private WebElement currentPage;

    public BreadcrumbsElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public String getCurrentPageTitle() {
        return currentPage.getText();
    }

    public StreetCodeCatalogPage clickCatalog() {
        actions.moveToElement(catalogLink).click().perform();
        //catalogLink.click();
        return new StreetCodeCatalogPage(driver);
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
        return rootElement.getText();
    }

    public boolean isBreadcrumbsDisplayed() {
        return rootElement.isDisplayed();
    }
}
