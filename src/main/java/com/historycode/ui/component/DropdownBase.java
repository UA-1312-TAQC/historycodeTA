package com.historycode.ui.component;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DropdownBase extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='ant-select-selection-overflow-item']//span[@class='ant-select-selection-item-content']")
    private List<WebElement> chosenElements;

    @FindBy(xpath = ".//input")
    private WebElement input;

    public DropdownBase(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void selectOption(int dropDownIndex, int currentOptionIndex, int targetOptionIndex) {

        input.click();

        // Обчислити кількість кроків
        int stepsToMove = targetOptionIndex - currentOptionIndex;

        if (stepsToMove > 0) {
            // Переміститися вниз
            for (int i = 0; i < stepsToMove; i++) {
                input.sendKeys(Keys.ARROW_DOWN);
            }
        } else if (stepsToMove < 0) {
            // Переміститися вгору
            for (int i = 0; i < Math.abs(stepsToMove); i++) {
                input.sendKeys(Keys.ARROW_UP);
            }
        }

        // DropDownIndex - select index of dropdown element, for example (0 - DD-MM-YYYY, 2 - TAGS,  3 - CONNECTIONS IN HISTORY, 4 - PARTNERS)
        String expectedOptionId = "rc_select_" + dropDownIndex + "_list_" + targetOptionIndex;

        // Чекати, поки `aria-activedescendant` оновиться
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until((ExpectedCondition<Boolean>) d -> {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) d;
            String activeDescendant = (String) jsExecutor.executeScript(
                    "return arguments[0].getAttribute('aria-activedescendant');",
                    input
            );
            return expectedOptionId.equals(activeDescendant);
        });

        // Натиснути Enter, щоб вибрати опцію
        input.sendKeys(Keys.ENTER);
    }

    public boolean isChosenOptionCorrect(String expectedText) {
        WebElement chosenOption = rootElement.findElement(By.xpath("./span[@class='ant-select-selection-item']"));
        String actualText = chosenOption.getText().trim();
        return expectedText.equals(actualText);
    }
    
}
