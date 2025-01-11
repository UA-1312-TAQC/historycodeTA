package com.historycode.ui.page.adminpanel.partnerspage.modal;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseCreateModal;
import com.historycode.ui.elements.adminPanel.CheckboxElement;
import com.historycode.ui.elements.adminPanel.InputElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePartnersModal extends BaseCreateModal {

    @FindBy(xpath = "./label[@for = 'isKeyPartner']/../..")
    private WebElement keyPartnerCheckboxContainer;
    @Getter
    private CheckboxElement KeyPartner = new CheckboxElement(driver, keyPartnerCheckboxContainer);

    @FindBy(xpath = "./label[@for = 'isVisibleEverywhere']/../..")
    private WebElement visiblePartnerCheckboxContainer;
    @Getter
    private CheckboxElement visiblePartner = new CheckboxElement(driver, visiblePartnerCheckboxContainer);

    @FindBy(xpath = "./label[@for = 'title']/../..")
    private WebElement partnerNameContainer;
    @Getter
    private InputElement partnerName = new InputElement(driver, partnerNameContainer);

    @FindBy(xpath = "./label[@for = 'url']/../..")
    private WebElement partnerLinkContainer;
    @Getter
    private InputElement partnerLink = new InputElement(driver, partnerLinkContainer);

    @FindBy(xpath = "./label[@for = 'urlTitle']/../..")
    private WebElement partnerLinkNameContainer;
    @Getter
    private InputElement partnerLinkName = new InputElement(driver, partnerLinkNameContainer);



    public CreatePartnersModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
