package com.historycode.ui.page.adminpanel.partnerspage.modal;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseCreateModal;
import com.historycode.ui.elements.adminPanel.CheckboxElement;
import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.elements.adminPanel.LogoElement;
import com.historycode.ui.elements.adminPanel.TextAreaElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePartnersModal extends BaseCreateModal {

    @FindBy(xpath = ".//label[@for = 'isKeyPartner']/../..")
    private WebElement keyPartnerCheckboxContainer;
    public CheckboxElement keyPartner;

    @FindBy(xpath = ".//label[@for = 'isVisibleEverywhere']/../..")
    private WebElement visiblePartnerCheckboxContainer;
    public CheckboxElement visiblePartner;

    @FindBy(xpath = ".//label[@for = 'title']/../..")
    private WebElement nameContainer;
    public InputElement name;

    @FindBy(xpath = ".//label[@for = 'url']/../..")
    private WebElement linkContainer;
    public InputElement link;

    @FindBy(xpath = ".//label[@for = 'urlTitle']/../..")
    private WebElement linkNameContainer;
    public InputElement linkName;

    //TODO Why do not find this webElement inside rootElement. Work only from general page
    @FindBy(xpath = ".//label[@for = 'description']/../..")
    private WebElement textAreaContainer;
    public TextAreaElement description;

    @FindBy(xpath = ".//label[@for = 'logo']/../..")
    private WebElement logoContainer;
    public LogoElement logo;

    public CreatePartnersModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.keyPartner = new CheckboxElement(driver, keyPartnerCheckboxContainer);
        this.visiblePartner = new CheckboxElement(driver, visiblePartnerCheckboxContainer);
        this.name = new InputElement(driver, nameContainer);
        this.link = new InputElement(driver, linkContainer);
        this.linkName = new InputElement(driver, linkNameContainer);
        this.description = new TextAreaElement(driver, textAreaContainer);
        this.logo = new LogoElement(driver, logoContainer);


    }
}
