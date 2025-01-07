package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ChronologyYearsBarComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='tickContainer']//span")
    private List<WebElement> yearNodes;

    @FindBy(xpath = ".//div[@class='tickContainer active']//span")
    private WebElement selectedYearNode;

    public ChronologyYearsBarComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void selectYear(String year) {
        yearNodes.stream()
                .filter(node -> node.getText().equals(year))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public String getSelectedYear() {
        return selectedYearNode.getText();
    }

    public List<String> getAllYears() {
        return yearNodes.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isYearSelected(String year) {
        return year.equals(getSelectedYear());
    }
}
