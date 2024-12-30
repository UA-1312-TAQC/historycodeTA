package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class PersonCardComponent extends BaseComponent {


    @FindBy(css = ".person-name")
    private WebElement personName;

    @FindBy(css = ".person-category")
    private WebElement category;

    @FindBy(css = ".person-description")
    private WebElement description;

    @FindBy(css = ".person-more-link")
    private WebElement moreLink;

    public PersonCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public String getPersonName() {
        return personName.getText().trim();
    }

    public String getCategory() {
        return category.getText().trim();
    }

    public String getDescription() {
        return description.getText().trim();
    }

    public void clickMore() {
        moreLink.click();
    }
}
