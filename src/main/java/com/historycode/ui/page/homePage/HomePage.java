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

    @FindBy(xpath = "//div[contains(@class, 'newsSliderContainer')]//div[contains(@class, 'sliderClass')]")
    private WebElement newsCarouselElement;

    @FindBy(xpath = "//div[@class='streetcodeSliderContainer']")
    private WebElement personCarouselElement;

    @FindBy(xpath = "//div[@class='teamComponent']//div[@class='sliderClass']")
    private WebElement teamCarouselElement;

    @FindBy(css = ".partnersBlock")
    private List<WebElement> partnersElements;

    @FindBy(xpath = "//div[contains(@class, 'mainPageBlockStaticBanner')]")
    private List<WebElement> staticBannerElements;

    private TopCarousel topCarousel;
    private TeamCardCarousel teamCarousel;
    private NewsCardCarousel newsCarousel;
    private PersonCardCarousel personsCarousel;
    private List<PartnersComponent> partners;
    private List<StaticBannerComponent> banners;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public TopCarousel getTopCarousel() {
        if (topCarousel == null) {
            topCarousel = new TopCarousel(driver, topCarouselElement);
        }
        return topCarousel;
    }

    public TeamCardCarousel getTeamCarousel() {
        if (teamCarousel == null) {
            teamCarousel = new TeamCardCarousel(driver, teamCarouselElement);
        }
        return teamCarousel;
    }

    public NewsCardCarousel getNewsCarousel() {
        if (newsCarousel == null) {
            newsCarousel = new NewsCardCarousel(driver, newsCarouselElement);
        }
        return newsCarousel;
    }

    public PersonCardCarousel getPersonsCarousel() {
        if (personsCarousel == null) {
            personsCarousel = new PersonCardCarousel(driver, personCarouselElement);
        }
        return personsCarousel;
    }

    public List<PartnersComponent> getPartners() {
        if (partners == null) {
            partners = partnersElements.stream()
                    .map(e -> new PartnersComponent(driver, e))
                    .collect(Collectors.toList());
        }
        return partners;
    }

    public List<StaticBannerComponent> getBanners() {
        if (banners == null) {
            banners = staticBannerElements.stream()
                    .map(e -> new StaticBannerComponent(driver, e))
                    .collect(Collectors.toList());
        }
        return banners;
    }

    public StreetCodePage clickPersonCardCarouselItem(int index) {
        scrollToElement(personCarouselElement);
        sleep(3000);
        getPersonsCarousel().getCarouselItems().get(index).clickMore();
        return new StreetCodePage(driver);
    }
}
