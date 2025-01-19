package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Getter
public class ChronologyYearsBarComponent extends BaseComponent {


    @FindBy(xpath = "//div[contains(@class, 'timeline-swiper')]")
    private WebElement redTimeline;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'timelineYearTick')]//span")
    private List<WebElement> yearsNode;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'tickContainer')]")
    private List<WebElement> selectedYearBoxContainer;

    public ChronologyYearsBarComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    public ChronologyYearsBarComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getRedTimeLine() {
        try {
            scrollToElement(redTimeline);
            wait.until(ExpectedConditions.visibilityOf(redTimeline));
            return redTimeline;
        } catch (TimeoutException e) {
            throw new IllegalStateException("Red timeline is not visible after scrolling.", e);
        }
    }

    public boolean isYearBoxLarger(int index) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", redTimeline);
        wait.until(ExpectedConditions.visibilityOf(redTimeline));

        WebElement selectedBox = selectedYearBoxContainer.get(index);

        Dimension selectedBoxSize = selectedBox.getSize();

        return selectedYearBoxContainer.stream()
                .allMatch(box -> {
                    Dimension otherBoxSize = box.getSize();
                    return selectedBoxSize.getHeight() > otherBoxSize.getHeight() &&
                            selectedBoxSize.getWidth() > otherBoxSize.getWidth();
                });
    }

    public boolean eventsChronologicallySorted() {
        if (yearsNode == null || yearsNode.isEmpty()) {
            return true;
        }
        for (int i = 0; i < yearsNode.size() - 1; i++) {

            WebElement currentElement = yearsNode.get(i);
            WebElement nextElement = yearsNode.get(i + 1);
            if (currentElement == null || nextElement == null) {
                continue;
            }
            try {
                int currentYear = Integer.parseInt(currentElement.getText().trim());
                int nextYear = Integer.parseInt(nextElement.getText().trim());
                if (currentYear > nextYear) {
                    return false;
                }
            } catch (NumberFormatException e) {
                throw new IllegalStateException("Invalid year format found in timeline", e);
            }
        }
        return true;
    }
}