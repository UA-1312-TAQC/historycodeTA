package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.elements.BreadcrumbsElement;
import com.historycode.ui.elements.ScrollTopButtonElement;
import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.streetCodePage.components.*;
import com.historycode.ui.page.streetCodePage.elememts.QuickDonateButtonElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

public class StreetCodePage extends BasePage {

    @FindBy(xpath = "//div[@class='card']")
    private WebElement mainCardNode;

    @FindBy(xpath = "//div[@class='progressBarContainer']")
    private WebElement verticalProgressNode;

    @FindBy(xpath = "//div[@class='scrollToTopBtnContainer']")
    private WebElement scrollTopButtonNode;

    @FindBy(xpath = "//div[@class='donateBtnContainer']")
    private WebElement quickDonateButtonNode;


    @Getter
    private BreadcrumbsElement breadcrumbs;
    @Getter
    private ScrollTopButtonElement scrollTopButton;
    @Getter
    private QuickDonateButtonElement quickDonateButton;

    @Getter
    private MainCardComponent mainCard;
    @Getter
    private StreetCodeTextBlockComponent textBlock;
    @Getter
    private InterestingFactsComponent facts;
    @Getter
    private ChronologyComponent timeline;
    @Getter
    private RelatedPersonasComponent relatedFigures;
    @Getter
    private SourcesComponent sources;
    private ArtGalleryComponent artGallery;
    @Getter
    private PartnerComponent partners;
    @Getter
    private RunningLineComponent runningLine;
    private PageNavigationBarComponent verticalProgress;


    public StreetCodePage(WebDriver driver){
        super(driver);

        this.breadcrumbs = new BreadcrumbsElement(driver);
        this.scrollTopButton = new ScrollTopButtonElement(driver);
        this.quickDonateButton = new QuickDonateButtonElement(driver);
        this.mainCard = new MainCardComponent(driver, mainCardNode);
        this.verticalProgress = new PageNavigationBarComponent(driver, verticalProgressNode);
        initializeOptionalComponents(driver);
    }

    public StreetCodePage(WebDriver driver, boolean testMode){
        super(driver);

        //TODO: remove testMode
        if  (testMode) {
            this.mainCard = new MainCardComponent(driver, mainCardNode);
            return;
        }

        this.breadcrumbs = new BreadcrumbsElement(driver);
        this.scrollTopButton = new ScrollTopButtonElement(driver);
        this.quickDonateButton = new QuickDonateButtonElement(driver);
        this.mainCard = new MainCardComponent(driver, mainCardNode);
        this.verticalProgress = new PageNavigationBarComponent(driver, verticalProgressNode);
        initializeOptionalComponents(driver);
    }

    private WebElement findElement(String xpath) {
        return driver.findElements(By.xpath(xpath)).stream().findFirst().orElse(null);
    }

    private void initializeOptionalComponents(WebDriver driver) {
        Optional.ofNullable(findElement("//div[@id='text']"))
                .ifPresent(element -> textBlock = new StreetCodeTextBlockComponent(driver, element));

        Optional.ofNullable(findElement("//div[@id='wow-facts']"))
                .ifPresent(element -> facts = new InterestingFactsComponent(driver, element));

        Optional.ofNullable(findElement("//div[@id='timeline']"))
                .ifPresent(element -> timeline = new ChronologyComponent(driver, element));

        Optional.ofNullable(findElement("//div[@class='relatedFiguresContainer']"))
                .ifPresent(element -> relatedFigures = new RelatedPersonasComponent(driver, element));

        Optional.ofNullable(findElement("//div[@class='sourcesContainer']"))
                .ifPresent(element -> sources = new SourcesComponent(driver, element));

        Optional.ofNullable(findElement("//div[@class='art-gallery']"))
                .ifPresent(element -> artGallery = new ArtGalleryComponent(driver, element));

        Optional.ofNullable(findElement("//div[@class='partnerContainer']"))
                .ifPresent(element -> partners = new PartnerComponent(driver, element));

        Optional.ofNullable(findElement("//div[@class='tickerContainer']"))
                .ifPresent(element -> runningLine = new RunningLineComponent(driver, element));
    }

    public Optional<StreetCodeTextBlockComponent> getTextBlock() {
        return Optional.ofNullable(textBlock);
    }

    public Optional<InterestingFactsComponent> getFacts() {
        return Optional.ofNullable(facts);
    }

    public Optional<ChronologyComponent> getTimeline() {
        return Optional.ofNullable(timeline);
    }

    public Optional<RelatedPersonasComponent> getRelatedFigures() {
        return Optional.ofNullable(relatedFigures);
    }

    public Optional<SourcesComponent> getSources() {
        return Optional.ofNullable(sources);
    }

    public Optional<ArtGalleryComponent> getArtGallery() {
        return Optional.ofNullable(artGallery);
    }

    public Optional<PartnerComponent> getPartners() {
        return Optional.ofNullable(partners);
    }

    public Optional<RunningLineComponent> getRunningLine() {
        return Optional.ofNullable(runningLine);
    }

    public void openDonateModal() {
        quickDonateButton.clickDonateButton();
    }

    public void scrollToTop() {
        scrollTopButton.clickScrollTop();
    }

    public void toggleProgressBar() {
        verticalProgress.toggleProgressBar();
    }
}

