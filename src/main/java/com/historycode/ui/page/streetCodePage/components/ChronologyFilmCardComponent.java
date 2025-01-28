package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChronologyFilmCardComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']")
    private WebElement filmCard;

    @Getter
    @FindBy(xpath = "//div[@id='timeline']//div[@class='slick-track']/div")
    private WebElement filmCardStyle;

    @Getter
    @FindBy(xpath = "//div[@class='slick-list']//div[@class='timelineItem']//p[@class='timelineItemMetadata']")
    private WebElement dataCards;

    @Getter
    @FindBy(xpath = "//div[@class='slick-slide']//p[@class='timelineItemMetadata']//span")
    private WebElement historicalContext;

    @Getter
    @FindBy(xpath = "//div[@class='slick-slide']//p[@class='timelineItemTitle']")
    private WebElement filmTitles;

    @Getter
    @FindBy(xpath = "//div[@class='slick-slide']//p[@class='timelineItemDescription']")
    private WebElement description;

    public ChronologyFilmCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public Map<String, String> getFilmCardData(int index) {
        getFilmCardByIndex(index);

        String title = filmTitles.get(index).getText();
        String year = dataCards.get(index).getText();
        String context = historicalContext.get(index).getText();
        String descriptionText = description.get(index).getText();

        Map<String, String> cardData = new HashMap<>();
        cardData.put("Title", title);
        cardData.put("dataCards", year);
        cardData.put("Context", context);
        cardData.put("Description", descriptionText);

        System.out.println("Card data at index " + index + ": " + cardData);
        return cardData;
    }

    public Map<String, String> getFilmCardData() {

        String title = filmTitles.getText();
        String year = dataCards.getText();
        String context = historicalContext.getText();
        String descriptionText = description.getText();

        Map<String, String> cardData = new HashMap<>();
        cardData.put("Title", title);
        cardData.put("dataCards", year);
        cardData.put("Context", context);
        cardData.put("Description", descriptionText);
        return cardData;
    }

    public WebElement getYearNodeByIndex(int index) {
        if (index < 0 || index >= dataCards.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        WebElement yearNode = dataCards.get(index);
        scrollToElement(yearNode);
        elementIsVisible(yearNode);

        return yearNode;
    }

    public WebElement getFilmCardByIndex(int index) {
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        WebElement filmCardElement = filmCard.get(index);
        scrollToElement(filmCardElement);
        wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(filmCardElement),
                ExpectedConditions.elementToBeClickable(filmCardElement)));
        return filmCardElement;
    }

    public WebElement getFilmCardTextByIndex(int index) {
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        try {
            WebElement filmCardElement = filmCard.get(index);
            scrollToElement(filmCardElement);
            wait.until(ExpectedConditions.visibilityOf(filmCardElement));
            return filmCardElement;
        } catch (TimeoutException e) {
            System.err.println("Timeout while waiting for visibility of film card at index: " + index);
            throw e;
        }
    }
        public void clickFilmCardByIndex ( int index){
            if (index < 0 || index >= filmCard.size()) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            try {
                WebElement card = getFilmCardByIndex(index);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
                wait.until(ExpectedConditions.visibilityOf(card));
                wait.until(ExpectedConditions.elementToBeClickable(card));
                card.click();
                System.out.println("Successfully clicked on film card at index: " + index);
            } catch (TimeoutException e) {
                throw new IllegalStateException("Timeout while trying to click on the film card at index " + index, e);
            } catch (JavascriptException e) {
                throw new IllegalStateException("JavaScript execution failed for the film card at index " + index, e);
            }
        }

    public WebElement getFilmCardByName(String name) {
        for (int i = 0; i < filmTitles.size(); i++) {
            WebElement titleElement = filmTitles.get(i);
            if (titleElement.getText().equalsIgnoreCase(name)) {
                WebElement filmCard = this.filmCard.get(i);
                scrollToElement(filmCard);
                wait.until(ExpectedConditions.visibilityOf(filmCard));
                return filmCard;
            }
        }
        throw new NoSuchElementException("Film card with name '" + name + "' not found!");
    }

    public void clickFilmCardByName(String name) {
        try {
            WebElement card = getFilmCardByName(name);
            wait.until(ExpectedConditions.elementToBeClickable(card));
            card.click();
        } catch (TimeoutException e) {
            throw new IllegalStateException("Film card with name '" + name + "' is not clickable.", e);
        }
    }

    public boolean isFilmCardProperlySeparated(int index) {
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        WebElement card = filmCard.get(index);
        scrollToElement(card);
        wait.until(ExpectedConditions.visibilityOf(card));
        String cardDisplay = card.getCssValue("display");
        System.out.println("Checking card at index " + index);
        System.out.println("Display: " + cardDisplay);
        boolean isDisplayCorrect = "block".equals(cardDisplay);
        if (!isDisplayCorrect) {
            System.err.println("Card at index " + index + " does not meet the display requirement!");
        } else {
            System.out.println("Card at index " + index + " has correct display.");
        }

        return isDisplayCorrect;
    }


    public boolean descriptionsWithinLimit(int maxLength) {
        for (int i = 0; i < description.size(); i++) {
            WebElement descriptionElement = description.get(i);
            String text = descriptionElement.getText();
            System.out.println("Description at index " + i + " has length: " + text.length() + " characters.");

            if (text.length() > maxLength) {
                return false;
            }
        }
        return true;
    }

    private String cleanDateText(String rawText) {
        if (rawText.contains(".")) {
            return rawText.substring(0, rawText.indexOf('.')).trim();
        }
        return rawText.trim();
    }

    private LocalDate dateFromText(String text, int index) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Date text is null or empty at index: " + index);
        }

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

    public boolean eventsChronologySorted() {
        List<LocalDate> dates = getEventDates(wait);

        if (dates.isEmpty()) {
            throw new IllegalStateException("No valid dates found for events.");
        }

        return IntStream.range(0, dates.size() - 2)
                .noneMatch(i -> {
                    boolean isAfter = dates.get(i).isAfter(dates.get(i + 1));
                    System.out.println(" i : " + dates.get(i) + "  i + 1 : " + dates.get(i + 1));
                    if (isAfter) {
                        System.err.println("Event at index " + i + " is after the event at index " + (i + 1));
                    }
                    return isAfter;
                });
    }

    private List<LocalDate> getEventDates(WebDriverWait wait) {
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < dataCards.size(); i++) {
            try {
                WebElement element = dataCards.get(i);
                String text = element.getText();

                if (text == null || text.trim().isEmpty()) {
                    continue;
                }
                LocalDate date = dateFromText(text, i);
                dates.add(date);
            } catch (TimeoutException e) {
                System.err.println("Timeout while waiting for visibility of element at index: " + i);
            } catch (IllegalArgumentException e) {
                System.err.println("Error parsing date at index " + i + ": " + e.getMessage());
            }
        }
        return dates;
    }

    public List<String> getBackgroundImageUrls() {
        return filmCardStyle.stream()
                .map(card -> {
                    return urlFromCssValue(card.getCssValue("background-image"));
                })
                .collect(Collectors.toList());
    }

    private String urlFromCssValue(String cssValue) {
        return cssValue.replace("url(\"", "").replace("\")", "").trim();
    }

    private void elementIsVisible(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isCardCentered(int index) {
        if (index < 0 || index >= filmCardStyle.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        WebElement card = filmCardStyle.get(index);
        wait.until(ExpectedConditions.visibilityOf(card));
        String cardClass = card.getAttribute("class");
        System.out.println("Card at index " + index + " has classes: " + cardClass);
        return cardClass.contains("slick-center");
    }

    public boolean borderColor(int index, String expectedColor) {
        if (index < 0 || index >= filmCardStyle.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        WebElement card = filmCardStyle.get(index);
        String borderColor = card.getCssValue("border-color");
        return expectedColor.equals(borderColor);
    }
    public ChronologyComponent click(){
        rootElement.click();
        return this;
    }
}



