package com.historycode.ui.page.adminpanel.teampage.editModal;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SocialMediaDropdownComponent extends BaseComponent {

    public SocialMediaDropdownComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @FindBy(css="#logotype")
    private WebElement dropdownButton;

    @FindBy(css=".rc-virtual-list-holder-inner")
    private WebElement optionsContainer;
    @FindBy(css=".rc-virtual-list-holder .ant-select-item")
    private List<WebElement> options;

    private final String OPTION_BY_TEXT_TEMPLATE = "//div[@class='rc-virtual-list-holder']//div[@title='%s']";
    public void openDropdown() {
        dropdownButton.click();
    }

    public void clickOptionByText(String optionText) {
        String dynamicXpath = String.format(OPTION_BY_TEXT_TEMPLATE, optionText);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        option.click();
    }
}
