package com.historycode.ui.component.adminPanel.dropDownAdminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DropdownComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = "//td[contains(@class, 'ant-table-cell')]//button[contains(@class, 'ant-btn')]")
    private WebElement dropdownButton;

    @Getter
    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu')]")
    private WebElement dropdownMenuContainer;

    @Getter
    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu')]/li")
    private List<WebElement> options;

    @Getter
    @FindBy(xpath = "//div[@class='ant-space-item'][1]")
    private WebElement selectedOption;

    @FindBy(xpath = "//div[@class='ant-select-selection-overflow-item']")
    private List<WebElement> selectedOptions;

    private final String OPTION_BY_TEXT_TEMPLATE = "//ul[contains(@class, 'ant-dropdown-menu')]/li[span[contains(text(), '%s')]]";

    public DropdownComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(rootElement, this);
    }

    public void openDropdown() {
        dropdownButton.click();
        waitUntilElementVisible(dropdownMenuContainer);
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

    /**
     * Selects multiple options in the dropdown by their visible texts.
     * @param optionTexts a list of option texts to select.
     */
    public void selectMultipleOptions(List<String> optionTexts) {
        openDropdown();
        for (String optionText : optionTexts) {
            String dynamicXpath = String.format(OPTION_BY_TEXT_TEMPLATE, optionText);
            WebElement option = dropdownMenuContainer.findElement(By.xpath(dynamicXpath));
            option.click();
        }
    }

    /**
     * Fetches all selected options as a list of strings by dynamically querying the DOM.
     * @return a list of texts of the selected options.
     */
    public List<String> getSelectedMultipleOptions() {
        List<WebElement> dynamicallyFetchedSelectedOptions = rootElement.findElements(By.xpath("//div[@class='ant-select-selection-overflow-item']//span[@class='ant-select-selection-item-content']"));
        return dynamicallyFetchedSelectedOptions.stream()
                .map(option -> option.getText().trim())
                .collect(Collectors.toList());
    }
}
