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

    @FindBy(xpath = "//div[@id='text']")
    private WebElement textBlockRoot;

    @FindBy(xpath = "//div[@id='wow-facts']")
    private WebElement factsNode;

    @FindBy(xpath = "//div[@id='timeline']")
    private WebElement timelineNode;

    @FindBy(xpath = "//div[@class='relatedFiguresContainer']")
    private WebElement relatedFiguresNode;

    @FindBy(xpath = "//div[@class='sourcesContainer']")
    private WebElement sourcesNode;

    @FindBy(xpath = "//div[@class='art-gallery']")
    private WebElement artGalleryNode;

    @FindBy(xpath = "//div[@class='partnerContainer']")
    private WebElement partnersNode;

    @FindBy(xpath = "//div[@class='tickerContainer']")
    private WebElement runningLineNode;


    private BreadcrumbsElement breadcrumbs;
    private ScrollTopButtonElement scrollTopButton;
    private QuickDonateButtonElement quickDonateButton;
    private MainCardComponent mainCard;
    private StreetCodeTextBlockComponent textBlock;
    private InterestingFactsComponent facts;
    private ChronologyComponent timeline;
    private RelatedPersonasComponent relatedFigures;
    private SourcesComponent sources;
    private ArtGalleryComponent artGallery;
    private PartnerComponent partners;
    private RunningLineComponent runningLine;
    private PageNavigationBarComponent verticalProgress;

    public StreetCodePage(WebDriver driver) {
        super(driver);
    }

    public StreetCodePage setMainCard() {
        this.mainCard = new MainCardComponent(driver, mainCardNode);
        return this;
    }

    public StreetCodePage setQuickDonateButton() {
        this.quickDonateButton = new QuickDonateButtonElement(driver, quickDonateButtonNode);
        return this;
    }

    public BreadcrumbsElement getBreadcrumbs() {
        if (breadcrumbs == null) {
            breadcrumbs = new BreadcrumbsElement(driver);
        }
        return breadcrumbs;
    }

    public ScrollTopButtonElement getScrollTopButton() {
        if (scrollTopButton == null) {
            scrollTopButton = new ScrollTopButtonElement(driver);
        }
        return scrollTopButton;
    }

    public QuickDonateButtonElement getQuickDonateButton() {
        if (quickDonateButton == null) {
            quickDonateButton = new QuickDonateButtonElement(driver, quickDonateButtonNode);
        }
        return quickDonateButton;
    }

    public MainCardComponent getMainCard() {
        if (mainCard == null) {
            mainCard = new MainCardComponent(driver, mainCardNode);
        }
        return mainCard;
    }

    public StreetCodeTextBlockComponent getTextBlock() {
        if (textBlock == null) {
            textBlock = new StreetCodeTextBlockComponent(driver, textBlockRoot);
        }
        return textBlock;
    }

    public InterestingFactsComponent getFacts() {
        if (facts == null) {
            facts = new InterestingFactsComponent(driver, factsNode);
        }
        return facts;
    }

    public ChronologyComponent getTimeline() {
        if (timeline == null && timelineNode != null) {
            timeline = new ChronologyComponent(driver, timelineNode);
        }
        return timeline;
    }

    public RelatedPersonasComponent getRelatedFigures() {
        if (relatedFigures == null) {
            relatedFigures = new RelatedPersonasComponent(driver, relatedFiguresNode);
        }
        return relatedFigures;
    }

    public SourcesComponent getSources() {
        if (sources == null) {
            sources = new SourcesComponent(driver, sourcesNode);
        }
        return sources;
    }

    public ArtGalleryComponent getArtGallery() {
        if (artGallery == null) {
            artGallery = new ArtGalleryComponent(driver, artGalleryNode);
        }
        return artGallery;
    }

    public PartnerComponent getPartners() {
        if (partners == null) {
            partners = new PartnerComponent(driver, partnersNode);
        }
        return partners;
    }

    public RunningLineComponent getRunningLine() {
        if (runningLine == null) {
            runningLine = new RunningLineComponent(driver, runningLineNode);
        }
        return runningLine;
    }

    public PageNavigationBarComponent getVerticalProgress() {
        if (verticalProgress == null) {
            verticalProgress = new PageNavigationBarComponent(driver, verticalProgressNode);
        }
        return verticalProgress;
    }

    public void scrollToTop() {
        scrollTopButton.clickScrollTop();
    }

    public void toggleProgressBar() {
        verticalProgress.toggleProgressBar();
    }
}
