package com.historycode.ui.component.adminPanel.dropDownAdminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class DropdownComponent extends BaseComponent {
    @FindBy(xpath = "//td[contains(@class, 'ant-table-cell')]//button[contains(@class, 'ant-btn')]")
    private WebElement dropdownButton;
    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu')]")
    private WebElement dropdownMenuContainer;
    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu')]/li")
    private List<WebElement> options;
    @FindBy(xpath = "//div[@class='ant-space-item'][1]")
    private WebElement selectedOption;

    private final String OPTION_BY_TEXT_TEMPLATE = "//ul[contains(@class, 'ant-dropdown-menu')]/li[span[contains(text(), '%s')]]";

    public DropdownComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(rootElement, this);
    }

    public void openDropdown() {
        dropdownButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(dropdownMenuContainer));
    }

    public void clickOptionByText(String optionText) {
        String dynamicXpath = String.format(OPTION_BY_TEXT_TEMPLATE, optionText);
        WebElement option = dropdownButton.findElement(By.xpath(dynamicXpath));
        option.click();
    }

    public List<String> getOptionsTexts() {
        openDropdown();
        return options.stream().map(option -> option.getText().trim()).toList();
    }

    public String getSelectedOptionText() {
        return selectedOption.getText().trim();
    }
}
