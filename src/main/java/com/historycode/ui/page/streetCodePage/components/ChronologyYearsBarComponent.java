package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class ChronologyYearsBarComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'timelineYearTick')]")
    private List<WebElement> selectedYearBoxContainer;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'active')]")
    private WebElement activeYearBox;

    public ChronologyYearsBarComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    public ChronologyYearsBarComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getIdYearBox(int index) {
        if (index >= 0 && index < selectedYearBoxContainer.size()) {
            return selectedYearBoxContainer.get(index);
        }
        throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    public boolean isYearBoxVisible(int index) {
        WebElement yearBox = getIdYearBox(index);
        return yearBox.isDisplayed();
    }

    public boolean isYearBoxLarger(int index) {
        WebElement selectedBox = getIdYearBox(index);
        Dimension selectedBoxSize = selectedBox.getSize();

        return selectedYearBoxContainer.stream()
                .allMatch(box -> {
                    Dimension otherBoxSize = box.getSize();
                    return selectedBoxSize.getHeight() > otherBoxSize.getHeight() &&
                            selectedBoxSize.getWidth() > otherBoxSize.getWidth();
                });
    }

    public boolean isYearBoxActive(int index) {
        if (index >= 0 && index < selectedYearBoxContainer.size()) {
            WebElement yearBox = selectedYearBoxContainer.get(index);
            return yearBox.getAttribute("class").contains("active");
        }
        throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    public boolean isActiveYearBoxVisible() {
        sleep(2000);
        return activeYearBox != null && activeYearBox.isDisplayed();
    }
}


//    public void selectYear(String year) {
//        yearNodes.stream()
//                .filter(node -> node.getText().equals(year))
//                .findFirst()
//                .ifPresent(WebElement::click);
//    }
//
//    public List<String> getAllYears() {
//        return yearNodes.stream()
//                .map(WebElement::getText)
//                .collect(Collectors.toList());
//    }
//
//    public boolean isYearSelected(String year) {
//        return year.equals(getSelectedYear());
//    }