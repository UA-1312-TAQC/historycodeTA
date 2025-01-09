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
    private WebElement topCarouselNode;

    @FindBy(xpath = "//div[contains(@class, 'newsSliderContainer')]//div[contains(@class, 'slider-item-container')]")
    private WebElement newsCarouselNode;

    @FindBy(xpath = "//div[@class='streetcodeSliderContainer']")
    private WebElement personCarouselNode;

    @FindBy(xpath = "//div[@class='teamComponent']//div[@class='sliderClass']")
    private WebElement teamCarouselNode;

    @FindBy(css = ".partnersBlock")
    private List<WebElement> partnersNodes;

    @FindBy(xpath = "//div[contains(@class, 'mainPageBlockStaticBanner')]")
    private List<WebElement> staticBannerNodes;

    private TopCarousel topCarousel;
    private TeamCardCarousel teamCarousel;
    private NewsCardCarousel newsCarousel;
    private PersonCardCarousel personsCarousel;

    public HomePage(WebDriver driver) {
        super(driver);

        topCarousel = new TopCarousel(driver, topCarouselNode);
        teamCarousel = new TeamCardCarousel(driver, teamCarouselNode);
        newsCarousel = new NewsCardCarousel(driver, newsCarouselNode);
        personsCarousel = new PersonCardCarousel(driver, personCarouselNode);
    }

    public List<PartnersComponent> getPartners() {
        return partnersNodes.stream()
                .map(e -> new PartnersComponent(driver, e))
                .collect(Collectors.toList());
    }

    public List<StaticBannerComponent> getBanners() {
        return staticBannerNodes.stream()
                .map(e -> new StaticBannerComponent(driver, e))
                .collect(Collectors.toList());
    }

    public StreetCodePage clickPersonCardCarouselItem(int index) {
        scrollToElement(personCarouselNode);
        personsCarousel.getCarouselItems().get(index).clickMore();
        return new StreetCodePage(driver, "test");
    }
}