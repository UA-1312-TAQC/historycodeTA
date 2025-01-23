package com.historycode.ui.page.homePage;

import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HomePage extends BasePage {

    @FindBy(css = ".slick-slider.top-carousel")
    private WebElement topCarouselElement;

    @FindBy(xpath = "//div[contains(@class, 'newsSliderContainer')]//div[contains(@class, 'slider-item-container')]")
    private WebElement newsCarouselElement;

    @FindBy(xpath = "//div[@class='streetcodeSliderContainer']")
    private WebElement personCarouselElement;

    @FindBy(xpath = "//div[@class='teamComponent']//div[@class='sliderClass']")
    private WebElement teamCarouselElement;

    @FindBy(css = ".partnersBlock")
    private List<WebElement> partnersElements;

    @FindBy(xpath = "//div[contains(@class, 'mainPageBlockStaticBanner')]")
    private List<WebElement> staticBannerElements;

    private final TopCarousel topCarousel;
    private final TeamCardCarousel teamCarousel;
    private final NewsCardCarousel newsCarousel;
    private final PersonCardCarousel personsCarousel;

    public HomePage(WebDriver driver) {
        super(driver);

        topCarousel = new TopCarousel(driver, topCarouselElement);
        teamCarousel = new TeamCardCarousel(driver, teamCarouselElement);
        newsCarousel = new NewsCardCarousel(driver, newsCarouselElement);
        personsCarousel = new PersonCardCarousel(driver, personCarouselElement);
    }

    public List<PartnersComponent> getPartners() {
        return partnersElements.stream()
                .map(e -> new PartnersComponent(driver, e))
                .collect(Collectors.toList());
    }

    public List<StaticBannerComponent> getBanners() {
        return staticBannerElements.stream()
                .map(e -> new StaticBannerComponent(driver, e))
                .collect(Collectors.toList());
    }

    public StreetCodePage clickPersonCardCarouselItem(int index) {
        scrollToElement(personCarouselElement);
        sleep(3000);
        personsCarousel.getCarouselItems().get(index).clickMore();
        return new StreetCodePage(driver);
    }
}
