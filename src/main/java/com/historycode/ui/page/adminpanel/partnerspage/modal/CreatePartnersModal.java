package com.historycode.ui.page.adminpanel.partnerspage.modal;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseCreateModal;
import com.historycode.ui.elements.adminPanel.CheckboxElement;
import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.elements.adminPanel.LogoElement;
import com.historycode.ui.elements.adminPanel.TextAreaElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePartnersModal extends BaseCreateModal {

    @FindBy(xpath = ".//label[@for = 'isKeyPartner']/../..")
    private WebElement keyPartnerCheckboxContainer;
    @Getter
    private CheckboxElement KeyPartner = new CheckboxElement(driver, keyPartnerCheckboxContainer);

    @FindBy(xpath = ".//label[@for = 'isVisibleEverywhere']/../..")
    private WebElement visiblePartnerCheckboxContainer;
    @Getter
    private CheckboxElement visiblePartner = new CheckboxElement(driver, visiblePartnerCheckboxContainer);

    @FindBy(xpath = ".//label[@for = 'title']/../..")
    private WebElement nameContainer;
    @Getter
    private InputElement name = new InputElement(driver, nameContainer);

    @FindBy(xpath = ".//label[@for = 'url']/../..")
    private WebElement linkContainer;
    @Getter
    private InputElement link = new InputElement(driver, linkContainer);

    @FindBy(xpath = ".//label[@for = 'urlTitle']/../..")
    private WebElement linkNameContainer;
    @Getter
    private InputElement linkName = new InputElement(driver, linkNameContainer);

    //TODO Why do not find this webElement inside rootElement. Work only from general page
    @FindBy(xpath = ".//label[@for = 'description']/../..")
    private WebElement textAreaContainer;
    @Getter
    private TextAreaElement discription = new TextAreaElement(driver, textAreaContainer);

    @FindBy(xpath = ".//label[@for = 'logo']/../..")
    private WebElement logoContainer;
    @Getter
    private LogoElement logo = new LogoElement(driver, logoContainer);

    public CreatePartnersModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
