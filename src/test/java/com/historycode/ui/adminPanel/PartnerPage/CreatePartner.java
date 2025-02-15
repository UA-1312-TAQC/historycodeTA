package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.component.DropdownBase;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.page.adminpanel.streetcodeeditpage.StreetcodeEditPage;
import com.historycode.ui.page.partnerPage.PartnerPage;

import io.qameta.allure.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.historycode.ui.utils.ImageLoader.getBase64FromFile;

public class CreatePartner extends TestAdminPartnerPage {

    @Test
    @Epic("AdminPartners")
    @Issue("127")
    @Story("64")
    @Description("Verify that admin can add new partner via 'Додати' button in the 'Партнери' block ")
    public void CreateNotKeyPartner() {
        String testName = "1SpongeBob";
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
        softAssert.assertEquals(getBase64FromFile(testLogo), newPartner.getLogoSrc(),
                "Img code base64 don't match");

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/new-streetcode");
        StreetcodeEditPage editPage = new StreetcodeEditPage(driver);
        editPage.scrollToElementJs(editPage.getPartnersRootElement());

        softAssert.assertTrue(editPage.getPartnersDropdown().isOptionPresent(testName),
                String.format("New partner tag %s not found", testName));
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

        Assert.assertEquals(getBase64FromFile(testLogo), basePage.getLogoSrc(testName, PartnerPage.PartnerType.NOT_KEY),
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

        Assert.assertEquals(getBase64FromFile(testLogo), basePage.getLogoSrc(testName, PartnerPage.PartnerType.KEY),
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

        Assert.assertEquals(getBase64FromFile(testLogo), newPartner.getLogoSrc(),
                "The image code doesn't match");
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
