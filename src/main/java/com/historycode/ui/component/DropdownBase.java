package com.historycode.ui.component;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DropdownBase extends BaseComponent {

    public DropdownBase(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void selectOptionFromDropdown(int currentOptionIndex, int targetOptionIndex) {
        try {
            WebElement input = rootElement.findElement(By.tagName("input"));
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

            String expectedOptionId = "rc_select_0_list_" + targetOptionIndex;

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
            } catch (Exception e) {
            System.err.println("Не вдалося вибрати опцію: " + e.getMessage());
            }
        }

        public boolean isChosenOptionCorrect(String expectedText) {
            WebElement chosenOption = rootElement.findElement(By.xpath("./span[@class='ant-select-selection-item']"));
            String actualText = chosenOption.getText().trim();
            return expectedText.equals(actualText);
        }

    }
