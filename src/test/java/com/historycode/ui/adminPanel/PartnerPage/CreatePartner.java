package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.page.partnerPage.PartnerPage;

import io.qameta.allure.*;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreatePartner extends TestAdminPartnerPage {

    @Test
    @Epic("AdminPartners")
    @Issue("127")
    @Story("64")
    @Description("Verify that admin can add new partner via 'Додати' button in the 'Партнери' block ")
    public void CreateNotKeyPartner() {
        SoftAssert softAssert = new SoftAssert();

        CreatePartnersModal createModal = openCreateModal();
        createModal.name.setInputField(testName);
        createModal.logo.uploadLogo(testLogo);
        createModal.clickSaveButton();
        createModal.clickCloseButton();

        PartnersRowComponent newPartner = new PartnersPageAdminPanel(driver)
                .clickLastPage()
                .getPartnersPageGridComponent()
                .findPartnerByName(testName);

        softAssert.assertEquals(newPartner.getNameText(), testName);
        softAssert.assertEquals(newPartner.getLogoSrc(), testLogoSrc);
        softAssert.assertAll();
    }

    @Test
    @Epic("AdminPartners")
    @Issue("130")
    @Story("64")
    @Description("Verify that the admin can add a description to a partner's card")
    public void addDescriptionNotKeyPartner() {
        CreatePartnersModal createModal = openCreateModal();
        createModal.name.setInputField(testName);
        createModal.description.setInputField(testDescription);
        createModal.logo.uploadLogo(testLogo);
        createModal.clickSaveButton();
        createModal.clickCloseButton();

        PartnerPage basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().goToPartnerPage();
        basePage.scrollToEndOfPage();
        basePage.hoverOverPartner(testName, PartnerPage.PartnerType.NOT_KEY);

        Assert.assertEquals(basePage.getPopoverDescription(), testDescription);
    }

    @Test
    @Epic("AdminPartners")
    @Issue("130")
    @Story("64")
    @Description("Verify that the admin can add a description to a Key partner's card")
    public void addDescriptionKeyPartner() {
        CreatePartnersModal createModal = openCreateModal();
        createModal.keyPartner.check();
        createModal.name.setInputField(testName);
        createModal.description.setInputField(testDescription);
        createModal.logo.uploadLogo(testLogo);
        createModal.clickSaveButton();
        createModal.clickCloseButton();

        PartnerPage basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().goToPartnerPage();
        basePage.scrollToElementJs(basePage.getNotKeyPartners().getFirst());
        basePage.hoverOverPartner(testName, PartnerPage.PartnerType.KEY);

        Assert.assertEquals(basePage.getPopoverDescription(), testDescription);
    }

    @Test
    @Epic("AdminPartners")
    @Issue("131")
    @Story("64")
    @Description("Verify that the admin can add logo as an image to a partner's card")
    public void addLogoNotKeyPartner() {
        CreatePartnersModal createModal = openCreateModal();
        createModal.name.setInputField(testName);
        createModal.logo.uploadLogo(testLogo);
        createModal.clickSaveButton();
        createModal.clickCloseButton();

        PartnerPage basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().goToPartnerPage();
        basePage.scrollToEndOfPage();

        Assert.assertEquals(basePage.getLogoSrc(testName, PartnerPage.PartnerType.NOT_KEY), testLogoSrc,
                        "Img code base64 don't match");
    }

    @Test
    @Epic("AdminPartners")
    @Issue("131")
    @Story("64")
    @Description("Verify that the admin can add logo as an image to a partner's card")
    public void addLogoKeyPartner() {
        SoftAssert softAssert = new SoftAssert();

        CreatePartnersModal createModal = openCreateModal();
        createModal.keyPartner.check();
        createModal.name.setInputField(testName);
        createModal.logo.uploadLogo(testLogo);
        createModal.clickSaveButton();
        createModal.clickCloseButton();

        PartnerPage basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().goToPartnerPage();
        basePage.scrollToElementJs(basePage.getNotKeyPartners().getFirst());

        softAssert.assertEquals(basePage.getLogoSrc(testName, PartnerPage.PartnerType.KEY), testLogoSrc,
                "Img code base64 don't match");
        softAssert.assertTrue(basePage.isGoodLogoSize(testName, PartnerPage.PartnerType.KEY),
                "The image size doesn't match the mockup");
        softAssert.assertAll();

    }

    @Test
    @Epic("AdminPartners")
    @Issue("132")
    @Story("64")
    @Description("Verify that the admin can add only one logo as an image to a partner's card")
    public void addOnlyOneLogo() {
        CreatePartnersModal createModal = openCreateModal();
        createModal.name.setInputField(testName);
        createModal.logo.uploadLogo(testLogo);
        createModal.logo.uploadLogo(testLogoNew);
        createModal.clickSaveButton();
        createModal.clickCloseButton();

        PartnersRowComponent newPartner = new PartnersPageAdminPanel(driver)
                .clickLastPage()
                .getPartnersPageGridComponent()
                .findPartnerByName(testName);

        Assert.assertEquals(newPartner.getLogoSrc(), testLogoNewSrc);
    }

    @AfterMethod
    public void cleanUp() {
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel");
        PartnersRowComponent newPartner = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickLastPage()
                .getPartnersPageGridComponent()
                .findPartnerByName(testName);

        if (newPartner != null) {
            newPartner.clickDelete().clickOkButton();
            Allure.step(String.format("Партнер '%s' успішно видалений.", testName));
            
        } else {
            Allure.step(String.format("Партнер '%s' не знайдений у системі.", testName));
        }
    }
}
