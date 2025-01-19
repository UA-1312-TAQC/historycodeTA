package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
        sleep(20000);
        try {
            scrollToElement(redTimeline);
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOf(redTimeline));
            return redTimeline;
        } catch (TimeoutException e) {
            throw new IllegalStateException("Red timeline is not visible after scrolling.", e);
        }
    }

    public boolean isYearBoxLarger(int index) {
        sleep(40000);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", redTimeline);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(redTimeline));

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
        for (int i = 0; i < yearsNode.size() - 1; i++) {
            String currentYear = yearsNode.get(i).getText().trim();
            String nextYear = yearsNode.get(i + 1).getText().trim();

            if (currentYear.compareTo(nextYear) > 0) {
                return false;
            }
        }
        return true;
    }
}