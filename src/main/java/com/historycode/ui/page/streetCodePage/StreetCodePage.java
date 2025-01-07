package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.elements.BreadcrumbsElement;
import com.historycode.ui.elements.ScrollTopButtonElement;
import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.streetCodePage.components.*;
import com.historycode.ui.page.streetCodePage.elememts.QuickDonateButtonElement;
import com.historycode.ui.page.streetCodePage.modals.SurveyModal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;
import java.util.Optional;

public class StreetCodePage extends BasePage {

    @FindBy(xpath = "//div[@class='card']")
    private WebElement mainCardNode;

    @FindBy(xpath = "//div[@id='text']")
    private WebElement detailsNode;

    @FindBy(xpath = "//div[@id='wow-facts']")
    private WebElement factsNode;

    @FindBy(xpath = "//div[@id='timeline']")
    private WebElement timelineNode;

    @FindBy(xpath = "//div[@class='relatedFiguresContainer']")
    private WebElement relatedFiguresNode;

    @FindBy(xpath = "")
    private WebElement additionalInfoNode;

    @FindBy(xpath = "//div[@class='art-gallery']")
    private WebElement artGalleryNode;

    @FindBy(xpath = "//div[@class='partnerContainer']")
    private WebElement partnersNode;

    @FindBy(xpath = "//div[@class='tickerContainer']")
    private WebElement runningLineNode;

    @FindBy(xpath = "//div[@class='progressBarContainer']")
    private WebElement verticalProgressNode;

    @FindBy(xpath = "//div[@class='scrollToTopBtnContainer']")
    private WebElement scrollTopButtonNode;

    @FindBy(xpath = "//div[@class='donateBtnContainer']")
    private WebElement quickDonateButtonNode;

    @FindBy(xpath = "//div[@class='ant-modal-content']")
    private WebElement surveyModalNode;

    @Getter
    private BreadcrumbsElement breadcrumbs;
    @Getter
    private ScrollTopButtonElement scrollTopButton;
    @Getter
    private QuickDonateButtonElement quickDonateButton;

    @Getter
    private MainCardComponent mainCard;
    @Getter
    private StreetCodeTextBlockComponent details;
    @Getter
    private InterestingFactsComponent facts;
    @Getter
    private ChronologyComponent timeline;
    @Getter
    private RelatedPersonasComponent relatedFigures;
    @Getter
    private SourcesComponent additionalInfo;
    private ArtGalleryComponent artGallery;
    @Getter
    private PartnerComponent partners;
    @Getter
    private RunningLineComponent runningLine;
    private PageNavigationBarComponent verticalProgress;
    private SurveyModal surveyModal;

    public StreetCodePage(WebDriver driver){
        super(driver);
        this.breadcrumbs = new BreadcrumbsElement(driver);
        this.scrollTopButton = new ScrollTopButtonElement(driver);
        this.quickDonateButton = new QuickDonateButtonElement(driver);
        this.mainCard = new MainCardComponent(driver, mainCardNode);
        this.details = new StreetCodeTextBlockComponent(driver, detailsNode);
        this.facts = new InterestingFactsComponent(driver, factsNode);
        this.timeline = new ChronologyComponent(driver, timelineNode);
        this.relatedFigures = new RelatedPersonasComponent(driver, relatedFiguresNode);
        this.additionalInfo = new SourcesComponent(driver, additionalInfoNode);
        this.partners = new PartnerComponent(driver, partnersNode);
        this.runningLine = new RunningLineComponent(driver, runningLineNode);
        this.verticalProgress = new PageNavigationBarComponent(driver, verticalProgressNode);
        this.surveyModal = new SurveyModal(driver, surveyModalNode);
        initializeArtGallery();
    }

    private void initializeArtGallery() {
        if (isElementPresent(By.xpath("//div[@class='art-gallery']"))) {
            this.artGallery = new ArtGalleryComponent(driver, artGalleryNode);
        }
    }

    public Optional<ArtGalleryComponent> getArtGallery() {
        return Optional.ofNullable(artGallery);
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void openDonateModal() {
        quickDonateButton.clickDonateButton();
    }

    public void closeSurveyModal() {
        surveyModal.close();
    }

    public void scrollToTop() {
        scrollTopButton.clickScrollTop();
    }

    public void toggleProgressBar() {
        verticalProgress.toggleProgressBar();
    }

    public boolean isSurveyModalOpen() {
        return surveyModal.isDisplayed();
    }
}

