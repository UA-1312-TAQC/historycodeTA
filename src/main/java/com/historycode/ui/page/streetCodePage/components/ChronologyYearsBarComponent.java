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

    @FindBy(xpath = ".//div[contains(@class, 'timeline-swiper')]")
    private WebElement redTimeline;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'timeSpanContainer')]//span")
    private WebElement yearNode;

//    @Getter - Селектор з виключенням помилки 1997 рік
//    @FindBy(xpath = "//div[contains(@id, 'timeline')]//div[contains(@class, 'timeSpanContainer')]//div[contains(@class, 'swiper-slide')][not(contains(@class, 'swiperEdgeBtn'))]//span")
//    private WebElement yearsNode;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'tickContainer')]//div[contains(@class, 'timelineYearTick')]")
    private WebElement selectedYearBoxContainer;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'tickContainer')]")
    private WebElement activeYearBox;

    public ChronologyYearsBarComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

        public WebElement getRedTimeline() {
            scrollToElement(redTimeline);
            wait.until(ExpectedConditions.visibilityOf(redTimeline));
            return redTimeline;
        }

        public List<String> getVisibleYears() {
            return rootElement.findElements(By.xpath(".//div[contains(@class, 'timeSpanContainer')]//span"))
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }

        public WebElement getSelectedYearBox() {
            scrollToElement(selectedYearBoxContainer);
            wait.until(ExpectedConditions.visibilityOf(selectedYearBoxContainer));
            return selectedYearBoxContainer;
        }

    public void clickYearBox() {
        try {
            wait.until(ExpectedConditions.visibilityOf(selectedYearBoxContainer));
            scrollToElement(selectedYearBoxContainer);
            wait.until(ExpectedConditions.elementToBeClickable(selectedYearBoxContainer));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectedYearBoxContainer);

            System.out.println("Successfully clicked on the active year box.");
        } catch (TimeoutException e) {
            throw new IllegalStateException("Timeout: The selected year box is not clickable.", e);
        } catch (JavascriptException e) {
            throw new IllegalStateException("JavaScript execution failed while clicking on the year box.", e);
        }
    }

        public boolean isYearBoxLarger() {
            scrollToElement(redTimeline);
            wait.until(ExpectedConditions.visibilityOf(redTimeline));

            Dimension selectedBoxSize = selectedYearBoxContainer.getSize();

            System.out.println("+++ Selected box size: " + selectedBoxSize +
                    " | Height: " + selectedYearBoxContainer.getCssValue("height"));

            List<WebElement> yearBoxes = rootElement.findElements(By.xpath(".//div[contains(@class, 'timelineYearTick')]"));

            for (WebElement current : yearBoxes) {
                System.out.println("Current box text: " + current.getText() +
                        " | Size: " + current.getSize() +
                        " | Height: " + current.getCssValue("height"));
            }

            return yearBoxes.stream()
                    .allMatch(box -> {
                        Dimension otherBoxSize = box.getSize();
                        return selectedBoxSize.getHeight() >= otherBoxSize.getHeight() &&
                                selectedBoxSize.getWidth() >= otherBoxSize.getWidth();
                    });
        }

        public String getActiveYearBoxText() {
            try {
                wait.until(ExpectedConditions.visibilityOf(activeYearBox));
                String activeYearText = activeYearBox.getText();
                System.out.println("Active year box found: " + activeYearText);
                return activeYearText;
            } catch (Exception e) {
                System.err.println("Error while retrieving active year box text: " + e.getMessage());
                throw e;
            }
        }

        public boolean yearsChronologicallySorted() {
            List<Integer> parsedYears = new ArrayList<>();

            List<WebElement> yearElements = rootElement.findElements(By.xpath(".//span"));
            System.out.println("Found year elements: " + yearElements.size());

            for (WebElement yearElement : yearElements) {
                String yearText = yearElement.getText().trim();

                if (yearText.isEmpty()) {
                    System.err.println("Empty year text. Skipping...");
                    continue;
                }

                try {
                    int year = Integer.parseInt(yearText);
                    parsedYears.add(year);
                    System.out.println("Parsed year: " + year);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid year format: " + yearText);
                }
            }

            if (parsedYears.isEmpty()) {
                System.err.println("No valid years found for chronological sorting check.");
                return false;
            }

            for (int i = 0; i < parsedYears.size() - 1; i++) {
                if (parsedYears.get(i) > parsedYears.get(i + 1)) {
                    System.err.println("Years are NOT sorted chronologically: " + parsedYears);
                    return false;
                }
            }

            System.out.println("Years ARE sorted chronologically: " + parsedYears);
            return true;

        }
    }

