package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.elements.BreadcrumbsElement;
import com.historycode.ui.elements.ScrollTopButtonElement;
import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.streetCodePage.components.*;
import com.historycode.ui.page.streetCodePage.elememts.QuickDonateButtonElement;
import com.historycode.ui.page.streetCodePage.modals.SurveyModal;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//div[@class='sourcesContainer']")
    private WebElement additionalInfoNode;

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
    private StreetCodeDetailsComponent details;
    @Getter
    private FactsComponent facts;
    @Getter
    private TimelineComponent timeline;
    @Getter
    private RelatedFiguresComponent relatedFigures;
    @Getter
    private AdditionalInfoComponent additionalInfo;
    @Getter
    private PartnerComponent partners;
    @Getter
    private RunningLineComponent runningLine;
    private VerticalProgressComponent verticalProgress;
    private SurveyModal surveyModal;

    public StreetCodePage(WebDriver driver){
        super(driver);
        this.breadcrumbs = new BreadcrumbsElement(driver);
        this.scrollTopButton = new ScrollTopButtonElement(driver);
        this.quickDonateButton = new QuickDonateButtonElement(driver);
        this.mainCard = new MainCardComponent(driver, mainCardNode);
        this.details = new StreetCodeDetailsComponent(driver, detailsNode);
        this.facts = new FactsComponent(driver, factsNode);
        this.timeline = new TimelineComponent(driver, timelineNode);
        this.relatedFigures = new RelatedFiguresComponent(driver, relatedFiguresNode);
        this.additionalInfo = new AdditionalInfoComponent(driver, additionalInfoNode);
        this.partners = new PartnerComponent(driver, partnersNode);
        this.runningLine = new RunningLineComponent(driver, runningLineNode);
        this.verticalProgress = new VerticalProgressComponent(driver, verticalProgressNode);
        this.surveyModal = new SurveyModal(driver, surveyModalNode);
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

