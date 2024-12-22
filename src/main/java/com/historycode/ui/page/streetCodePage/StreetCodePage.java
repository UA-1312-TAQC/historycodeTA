package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.BaseModal;
import com.historycode.ui.elements.BaseElement;
import com.historycode.ui.elements.BreadcrumbsElement;
import com.historycode.ui.elements.ScrollTopButtonElement;
import com.historycode.ui.page.BasePage;
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

    private BreadcrumbsElement breadcrumbs;
    private ScrollTopButtonElement scrollTopButton;
    private QuickDonateButtonElement quickDonateButton;
    private StreetCodeMainCardComponent mainCard;
    private StreetCodeDetailsComponent details;
    private FactCardComponent facts;
    private TimelineComponent timeline;
    private RelatedFiguresComponent relatedFigures;
    private AdditionalInfoComponent additionalInfo;
    private PartnersCarouselComponent partners;
    private RunningLineComponent runningLine;
    private VerticalProgressComponent verticalProgress;
    public StreetCodePage(WebDriver driver){
        super(driver);
        this.breadcrumbs = new BreadcrumbsElement(driver);
        this.scrollTopButton = new ScrollTopButtonElement(driver);
        this.quickDonateButton = new QuickDonateButtonElement(driver);
        this.mainCard = new StreetCodeMainCardComponent(driver, mainCardNode);
        this.details = new StreetCodeDetailsComponent(driver, detailsNode);
        this.facts = new FactCardComponent(driver, factsNode);
        this.timeline = new TimelineComponent(driver, timelineNode);
        this.relatedFigures = new RelatedFiguresComponent(driver, relatedFiguresNode);
        this.additionalInfo = new AdditionalInfoComponent(driver, additionalInfoNode);
        this.partners = new PartnersCarouselComponent(driver, partnersNode);
        this.runningLine = new RunningLineComponent(driver, runningLineNode);
        this.verticalProgress = new VerticalProgressComponent(driver, verticalProgressNode);
    }
}

class QuickDonateButtonElement extends BaseElement {
    public QuickDonateButtonElement(WebDriver driver) {
        super(driver);
    }
}

class StreetCodeMainCardComponent extends BaseComponent {
    public StreetCodeMainCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class StreetCodeDetailsComponent extends BaseComponent {
    public StreetCodeDetailsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class FactCardComponent extends BaseComponent {
    public FactCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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
    public AdditionalInfoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

class PartnersCarouselComponent extends BaseComponent {
    public PartnersCarouselComponent(WebDriver driver, WebElement rootElement) {
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

