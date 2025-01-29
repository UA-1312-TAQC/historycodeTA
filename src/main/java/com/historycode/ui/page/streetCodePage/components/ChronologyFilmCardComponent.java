package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChronologyFilmCardComponent extends BaseComponent {

    @FindBy(xpath = ".//div[@class='timelineItem']")
    private WebElement filmCard;

    @Getter
    @FindBy(xpath = "//div[@id='timeline']//div[@class='slick-track']/div")
    private WebElement filmCardStyle;

    @Getter
    @FindBy(xpath = "//div[@class='slick-list']//div[@class='timelineItem']//p[@class='timelineItemMetadata']")
    private WebElement dataCard;

    @Getter
    @FindBy(xpath = "//div[@class='slick-slide']//p[@class='timelineItemMetadata']//span")
    private WebElement historicalContext;

    @Getter
    @FindBy(xpath = "//div[@class='slick-slide']//p[@class='timelineItemTitle']")
    private WebElement filmTitle;

    @Getter
    @FindBy(xpath = "//div[@class='slick-slide']//p[@class='timelineItemDescription']")
    private WebElement description;

    public ChronologyFilmCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getFilmCard() {
        scrollToElement(filmCard);
        wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(filmCard),
                ExpectedConditions.elementToBeClickable(filmCard)));
        return filmCard;
    }

    public void clickFilmCard() {
        try {
            scrollToElement(filmCard);
            wait.until(ExpectedConditions.visibilityOf(filmCard));
            wait.until(ExpectedConditions.elementToBeClickable(filmCard));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filmCard);

            System.out.println("Successfully clicked on the film card.");
        } catch (TimeoutException e) {
            throw new IllegalStateException("The film card is not clickable or visible after waiting.", e);
        } catch (JavascriptException e) {
            throw new IllegalStateException("Failed to execute JavaScript click on the film card.", e);
        }
    }

    public Map<String, String> getFilmCardData() {
        String title = filmTitle.getText();
        String year = dataCard.getText();
        String context = historicalContext.getText();
        String descriptionText = description.getText();

        Map<String, String> cardData = new HashMap<>();
        cardData.put("Title", title);
        cardData.put("dataCard", year);
        cardData.put("Context", context);
        cardData.put("Description", descriptionText);
        return cardData;
    }

    public Dimension getFilmCardSize() {
        scrollToElement(filmCard);
        wait.until(ExpectedConditions.visibilityOf(filmCard));
        Dimension size = filmCard.getSize();

        System.out.println("Film Card Size: Width = " + size.getWidth() + "px, Height = " + size.getHeight() + "px");
        return size;
    }

    public boolean isFilmCardSeparated() {
        Dimension size = getFilmCardSize();

        boolean isWidthCorrect = size.getWidth() == 299;
        boolean isHeightCorrect = size.getHeight() == 208;

        if (!isWidthCorrect || !isHeightCorrect) {
            System.err.println("Film card size is incorrect! Expected: 299x208 px, Actual: " + size.getWidth() + "x" + size.getHeight() + " px");
        } else {
            System.out.println("Film card has the correct size: 299x208 px");
        }

        return isWidthCorrect && isHeightCorrect;
    }

    public boolean descriptionsWithinLimit(int maxLength) {
        String text = description.getText();
        System.out.println("Description length: " + text.length() + " characters.");
        return text.length() <= maxLength;
    }

    public List<String> getBackgroundImageUrls() {
        String cssValue = filmCardStyle.getCssValue("background-image");
        return List.of(urlFromCssValue(cssValue));
    }

    private String urlFromCssValue(String cssValue) {
        return cssValue.replace("url(\"", "").replace("\")", "").trim();
    }

    public boolean isCardCentered() {
        scrollToElement(filmCardStyle);
        wait.until(ExpectedConditions.visibilityOf(filmCardStyle));
        String cardClass = filmCardStyle.getAttribute("class");
        System.out.println("Film card has classes: " + cardClass);
        return cardClass.contains("slick-center");
    }

    public boolean borderColor(String expectedColor) {
        String borderColor = filmCardStyle.getCssValue("border-color");
        return expectedColor.equals(borderColor);
    }

    public boolean eventsChronologySorted() {
        LocalDate currentDate = dateFromText(dataCard.getText(), 0);
        LocalDate nextDate = currentDate;

        if (nextDate.isBefore(currentDate)) {
            System.out.println("Events are not sorted chronologically.");
            return false;
        }
        return true;
    }

    private LocalDate dateFromText(String text, int index) {
        text = cleanDateText(text);
        if (text.matches("\\d{4}")) {
            return LocalDate.of(Integer.parseInt(text), 1, 1);
        } else if (text.matches("\\d{4}, (весна|літо|осінь|зима)")) {
            String[] parts = text.split(", ");
            int year = Integer.parseInt(parts[0]);
            Month seasonMonth = switch (parts[1].toLowerCase()) {
                case "весна" -> Month.MARCH;
                case "літо" -> Month.JUNE;
                case "осінь" -> Month.SEPTEMBER;
                case "зима" -> Month.DECEMBER;
                default -> throw new IllegalArgumentException("Unknown season: " + parts[1] + " at index: " + index);
            };
            return LocalDate.of(year, seasonMonth, 1);
        } else if (text.matches("\\d{4}, \\d{1,2} [а-яА-Я]+")) {
            String[] parts = text.split(", ");
            int year = Integer.parseInt(parts[0]);
            String[] dayMonth = parts[1].split(" ");
            int day = Integer.parseInt(dayMonth[0]);
            Month month = allMonth(dayMonth[1]);
            return LocalDate.of(year, month, day);
        } else if (text.matches("\\d{4}, [а-яА-Я]+")) {
            String[] parts = text.split(", ");
            int year = Integer.parseInt(parts[0]);
            Month month = allMonth(parts[1]);
            return LocalDate.of(year, month, 1);
        }
        throw new IllegalArgumentException("Unknown date format: " + text + " at index: " + index);
    }

    private Month allMonth(String monthText) {
        return switch (monthText.toLowerCase()) {
            case "січень", "січня" -> Month.JANUARY;
            case "лютий", "лютого" -> Month.FEBRUARY;
            case "березень", "березня" -> Month.MARCH;
            case "квітень", "квітня" -> Month.APRIL;
            case "травень", "травня" -> Month.MAY;
            case "червень", "червня" -> Month.JUNE;
            case "липень", "липня" -> Month.JULY;
            case "серпень", "серпня" -> Month.AUGUST;
            case "вересень", "вересня" -> Month.SEPTEMBER;
            case "жовтень", "жовтня" -> Month.OCTOBER;
            case "листопад", "листопада" -> Month.NOVEMBER;
            case "грудень", "грудня" -> Month.DECEMBER;
            default -> throw new IllegalArgumentException("Unknown month: " + monthText);
        };
    }

    private String cleanDateText(String rawText) {
        if (rawText.contains(".")) {
            return rawText.substring(0, rawText.indexOf('.')).trim();
        }
        return rawText.trim();
    }
}
