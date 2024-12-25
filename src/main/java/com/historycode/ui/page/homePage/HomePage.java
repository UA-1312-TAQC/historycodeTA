package com.historycode.ui.page.homePage;

import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HomePage extends BasePage {

    @FindBy(css = ".news-carousel")
    private WebElement newsCarouselElement;

    @FindBy(css = ".person-carousel")
    private WebElement personCarouselElement;

    @FindBy(css = ".team-carousel")
    private WebElement teamCarouselElement;

    @FindBy(css = ".partners")
    private List<WebElement> partnersElements;

    @FindBy(css = ".static-banner")
    private List<WebElement> staticBannerElements;

    private CarouselComponent<NewsCardComponent> newsCarousel;
    private CarouselComponent<PersonCardComponent> personCarousel;
    private CarouselComponent<TeamCardComponent> teamCarousel;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        newsCarousel = new CarouselComponent<>(driver, newsCarouselElement, NewsCardComponent.class);
        personCarousel = new CarouselComponent<>(driver, personCarouselElement, PersonCardComponent.class);
        teamCarousel = new CarouselComponent<>(driver, teamCarouselElement, TeamCardComponent.class);
    }

    public List<PartnersComponent> getPartners() {
        return partnersElements.stream()
                .map(e -> new PartnersComponent(driver, e))
                .collect(Collectors.toList());
    }

    public List<StaticBannerComponent> getStaticBanners() {
        return staticBannerElements.stream()
                .map(e -> new StaticBannerComponent(driver, e))
                .collect(Collectors.toList());
    }
}
