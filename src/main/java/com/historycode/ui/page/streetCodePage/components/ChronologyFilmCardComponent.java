package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ChronologyFilmCardComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']")
    private List<WebElement> filmCard;

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemMetadata']")
    private List<WebElement> yearNodes;

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemMetadata']/span[@class='historicalContext']")
    private List<WebElement> historicalContext;

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemTitle']")
    private List<WebElement> filmTitles;

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemDescription']")
    private List<WebElement> description;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'timelineContentContainer')]//div[contains(@class, 'slick-track')]")
    private List<WebElement> backgroundImages;

    public ChronologyFilmCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    public ChronologyFilmCardComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getFilmCardByIndex(int index) {
        sleep(10000);
        if (index >= 0 && index < filmCard.size()) {
            WebElement filmCard = this.filmCard.get(index);
            scrollToElement(filmCard);
            return filmCard;
        }
        throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    public void clickFilmCardByIndex(int index) {
        sleep(10000);
        try {
            WebElement card = getFilmCardByIndex(index);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(card));
            card.click();
        } catch (TimeoutException e) {
            throw new IllegalStateException("Card at index " + index + " is not clickable after waiting.", e);
        }
    }

    public WebElement getFilmCardByName(String name) {
        for (int i = 0; i < filmTitles.size(); i++) {
            if (filmTitles.get(i).getText().equalsIgnoreCase(name)) {
                WebElement filmCard = this.filmCard.get(i);

                scrollToElement(filmCard);

                return filmCard;
            }
        }
        throw new NoSuchElementException("Film card with name '" + name + "' not found!");
    }

    public void clickFilmCardByName(String name) {
        WebElement card = getFilmCardByName(name);
        card.click();
    }

    public boolean allFilmCardsVisible() {
        sleep(10000);
        return filmCard.stream().allMatch(WebElement::isDisplayed);
    }

    public int getFilmCardCount() {
        return filmCard.size();
    }

    public boolean filmCardsUnique() {
        Set<WebElement> uniqueCards = new HashSet<>(filmCard);
        return uniqueCards.size() == filmCard.size();
    }

    public boolean isCardComplete(int index) {
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid event index: " + index);
        }

        WebElement yearElement = yearNodes.get(index);
        WebElement contextElement = historicalContext.get(index);
        WebElement titleElement = filmTitles.get(index);
        WebElement descriptionElement = description.get(index);

        boolean hasYear = yearElement.isDisplayed() && !yearElement.getText().isEmpty();
        boolean hasHistoricalContext = contextElement.isDisplayed() && !contextElement.getText().isEmpty();
        boolean hasTitle = titleElement.isDisplayed() && !titleElement.getText().isEmpty();
        boolean hasDescription = descriptionElement.isDisplayed() && !descriptionElement.getText().isEmpty();

        return hasYear && hasHistoricalContext && hasTitle && hasDescription;
    }

    public boolean descriptionsWithinLimit(int maxLength) {
        for (WebElement description : description) {
            String text = description.getText();
            if (text.length() > maxLength) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> getDescriptionLengths() {
        return description.stream()
                .map(description -> description.getText().length())
                .collect(Collectors.toList());

    }

    public List<String> getBackgroundImageUrls() {
        return filmCard.stream()
                .map(card -> urlFromCssValue(card.getCssValue("background-image")))
                .collect(Collectors.toList());
    }

    private String urlFromCssValue(String cssValue) {
        return cssValue.replace("url(\"", "").replace("\")", "").trim();
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

    public boolean eventsChronologicallySorted() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<LocalDate> dates = getEventDates(wait);

        if (dates.isEmpty()) {
            throw new IllegalStateException("No valid dates found for events.");
        }

        IntStream.range(0, dates.size() - 1)
                .noneMatch(i -> {
                    boolean isAfter = dates.get(i).isAfter(dates.get(i + 1));
                    if (isAfter) {
                        System.err.println("Event at index " + i + " is after the event at index " + (i + 1));
                    }
                    return isAfter;
                });
        return false;
    }

    private void elementIsVisible(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private List<LocalDate> getEventDates(WebDriverWait wait) {
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < yearNodes.size(); i++) {
            try {
                WebElement element = yearNodes.get(i);
                elementIsVisible(element);
                String text = element.getText();

                if (text == null || text.trim().isEmpty()) {
                    throw new IllegalStateException("Date text is null or empty at index: " + i);
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

    public boolean isCardCentered(int index) {
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        WebElement card = filmCard.get(index);
        String displayStyle = card.getCssValue("display");
        return "inline-block".equals(displayStyle);
    }

    public boolean borderColor(int index, String expectedColor) {
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        WebElement card = filmCard.get(index);
        String borderColor = card.getCssValue("border-color");
        return expectedColor.equals(borderColor);
    }
}



