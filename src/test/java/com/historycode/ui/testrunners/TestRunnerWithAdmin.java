package com.historycode.ui.testrunners;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.BeforeMethod;

public class TestRunnerWithAdmin extends BaseTestRunner {

    @BeforeMethod
    public void login() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        // using local storage
        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.setItem("AccessToken", testValueProvider.getAccessToken());
        localStorage.setItem("RefreshToken", testValueProvider.getRefreshToken());

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel");
//        LoginPageAdminPanel pageAdminPanel = new LoginPageAdminPanel(driver);
//        pageAdminPanel.enterLogin(testValueProvider.getAdminEmail())
//                .enterPassword(testValueProvider.getAdminPass())
//                .clickCaptcha()
//                .clickSighInButtonPositive();
    }
}
