package com.historycode.ui.page;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.BaseModal;
import com.historycode.ui.elements.BaseElement;
import com.historycode.ui.elements.BreadcrumbsElement;
import com.historycode.ui.elements.ScrollTopButtonElement;
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

    @FindBy(xpath = "///div[@class='partnerContainer']")
    private WebElement partnersNode;

    @FindBy(xpath = "//div[@class='tickerContainer']]")
    private WebElement runningLineNode;

    @FindBy(xpath = "//div[@class='progressBarContainer']")
    private WebElement verticalProgressNode;

    @FindBy(xpath = "//div[@class='scrollToTopBtnContainer']")
    private WebElement scrollTopButtonNode;

    @FindBy(xpath = "//div[@class='donateBtnContainer']")
    private WebElement quickDonateButtonNode;
    @FindBy(xpath = "//div[@class='ant-modal-content']")
    private WebElement surveyModalNode;

    private BreadcrumbsElement breadcrumbs;
    private ScrollTopButtonElement scrollTopButton;
    private QuickDonateButtonElement quickDonateButton;
    private MainCardComponent mainCard;
    private StreetCodeDetailsComponent details;
    private FactCardComponent facts;
    private TimelineComponent timeline;
    private RelatedFiguresComponent relatedFigures;
    private AdditionalInfoComponent additionalInfo;
    private PartnerComponent partners;
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
        this.facts = new FactCardComponent(driver, factsNode);
        this.timeline = new TimelineComponent(driver, timelineNode);
        this.relatedFigures = new RelatedFiguresComponent(driver, relatedFiguresNode);
        this.additionalInfo = new AdditionalInfoComponent(driver, additionalInfoNode);
        this.partners = new PartnerComponent(driver, partnersNode);
        this.runningLine = new RunningLineComponent(driver, runningLineNode);
        this.verticalProgress = new VerticalProgressComponent(driver, verticalProgressNode);
        this.surveyModal = new SurveyModal(driver, surveyModalNode);
    }
}

//elements
class QuickDonateButtonElement extends BaseElement {
    public QuickDonateButtonElement(WebDriver driver) {
        super(driver);
    }
}

//components
class MainCardComponent extends BaseComponent {
    private KeywordPersonsModal keywordPersonsModal;
    public MainCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.keywordPersonsModal = new KeywordPersonsModal(driver, rootElement);
    }
}

class StreetCodeDetailsComponent extends BaseComponent {
    public StreetCodeDetailsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class FactCardComponent extends BaseComponent {
    private FactCardModal factCardModal;
    public FactCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.factCardModal = new FactCardModal(driver, rootElement);
    }
}

class TimelineComponent extends BaseComponent {
    public TimelineComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class RelatedFiguresComponent extends BaseComponent {
    public RelatedFiguresComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class AdditionalInfoComponent extends BaseComponent {
    private AdditionalInfoModal additionalInfoModal;
    public AdditionalInfoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.additionalInfoModal = new AdditionalInfoModal(driver, rootElement);
    }
}

class PartnerComponent extends BaseComponent {
    public PartnerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class RunningLineComponent extends BaseComponent {
    public RunningLineComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class VerticalProgressComponent extends BaseComponent {
    public VerticalProgressComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

//modals
class KeywordPersonsModal extends BaseModal {
    public KeywordPersonsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class FactCardModal extends BaseModal {
    public FactCardModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class AdditionalInfoModal extends BaseModal {
    public AdditionalInfoModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class SurveyModal extends BaseModal {
    public SurveyModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

