package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ChronologyYearsBarComponent extends BaseComponent {


    @FindBy(xpath = "//div[contains(@class, 'timeline-swiper')]")
    private WebElement redTimeline;

    @Getter
    @FindBy(xpath = "//div[contains(@id, 'timeline')]//div[contains(@class, 'timeSpanContainer')]//span")
    private List<WebElement> yearsNode;

//    @Getter - Селектор з виключенням помилки 1997 рік
//    @FindBy(xpath = "//div[contains(@id, 'timeline')]//div[contains(@class, 'timeSpanContainer')]//div[contains(@class, 'swiper-slide')][not(contains(@class, 'swiperEdgeBtn'))]//span")
//    private List<WebElement> yearsNode;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'tickContainer')]//div[contains(@class, 'timelineYearTick')]")
    private List<WebElement> selectedYearBoxContainer;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'tickContainer')]")
    private List<WebElement> activeYearBox;

    public ChronologyYearsBarComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getRedTimeLine() {
        scrollToElement(redTimeline);
        wait.until(ExpectedConditions.visibilityOf(redTimeline));
        return redTimeline;
    }

    public List<String> getVisibleYears() {
        return yearsNode.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getSelectedYearBoxByIndex(int index) {
        if (index < 0 || index >= selectedYearBoxContainer.size()) {
        }

        WebElement yearBox = selectedYearBoxContainer.get(index);
        scrollToElement(yearBox);
        wait.until(ExpectedConditions.visibilityOf(yearBox));
        return yearBox;
    }

    public void clickYearBoxByIndex(int index) {
        WebElement yearBox = getSelectedYearBoxByIndex(index);
        wait.until(ExpectedConditions.elementToBeClickable(yearBox));
        threadJs.executeScript("arguments[0].click();", yearBox);
    }

    public boolean isYearBoxLarger(int index) {

        scrollToElement(redTimeline);
        wait.until(ExpectedConditions.visibilityOf(redTimeline));

        WebElement selectedBox = selectedYearBoxContainer.get(index);

        Dimension selectedBoxSize = selectedBox.getSize();

        System.out.println("+++selectedBox.getSize() = " + selectedBox.getSize() +
                "  selectedBox.getCssValue height = " + selectedBox.getCssValue("height"));
        for (WebElement current : selectedYearBoxContainer) {
            System.out.println("current text = " + current.getText()
                    + "  current.getSize() = " + current.getSize()
                    + "  current.getCssValue height = " + current.getCssValue("height"));
        }

        return selectedYearBoxContainer.stream()
                .allMatch(box -> {
                    Dimension otherBoxSize = box.getSize();
                    return selectedBoxSize.getHeight() >= otherBoxSize.getHeight() &&
                            selectedBoxSize.getWidth() >= otherBoxSize.getWidth();
                });

    }

    public String getActiveYearBoxText() {
        try {
            WebElement yearsNode = activeYearBox.stream()
                    .filter(box -> box.getAttribute("class").contains("active"))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No active year box found!"));

            System.out.println("Active year box found with text: " + yearsNode.getText());

            return yearsNode.getText();
        } catch (Exception e) {
            System.err.println("Error while retrieving active year box text: " + e.getMessage());
            throw e;
        }
    }

    private boolean yearsChronologicallySorted() {

        List<Integer> parsedYears = new ArrayList<>();
        for (WebElement element : yearsNode) {
            System.out.println(" : " + element.getText());
        }
        List<WebElement> visibleYears = yearsNode.stream()
                .filter(WebElement::isDisplayed)
                .toList();
        System.out.println("Visible years count: " + visibleYears.size());

        for (WebElement yearElement : visibleYears) {
            String yearText = yearElement.getText().trim();
            if (yearText.isEmpty()) {
                System.err.println("Empty year text found. Skipping this element.");
                continue;
            }
            try {
                int year = Integer.parseInt(yearText);
                parsedYears.add(year);
            } catch (NumberFormatException e) {
                System.err.println("Invalid year format: " + yearText);
            }
        }
        for (int i = 0; i < parsedYears.size() - 1; i++) {
            if (parsedYears.get(i) > parsedYears.get(i + 1)) {
                System.err.println("Years are not sorted chronologically: " + parsedYears);
                return false;
            }
        }

        System.out.println("Years are sorted chronologically: " + parsedYears);
        return true;
    }

    public void verifyCarouselChronology() {
        int currentIndex = 4;
        int firstIndex = 0;
        int lastIndex = 8;

        while (currentIndex > firstIndex) {
            clickYearBoxByIndex(currentIndex - 1);
            currentIndex--;
        }

        while (currentIndex < lastIndex) {
            clickYearBoxByIndex(currentIndex + 1);
            currentIndex++;
            yearsChronologicallySorted();
        }
    }
}