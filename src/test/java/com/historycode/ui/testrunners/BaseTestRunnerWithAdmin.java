package com.historycode.ui.testrunners;

import io.qameta.allure.Step;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestRunnerWithAdmin extends BaseTestRunner{

    @Step("set AccessToken")
    public void setAccessToken() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.setItem("AccessToken", testValueProvider.getAccessToken());
        localStorage.setItem("RefreshToken", testValueProvider.getRefreshToken());
    }

    @Step("remove AccessToken")
    public void removeAccessToken() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.removeItem("AccessToken");
        localStorage.removeItem("RefreshToken");
    }

    @BeforeClass
    public void BeforeClass() {
        driver.get(testValueProvider.getBaseUIUrl());
        setAccessToken();
    }
    @BeforeMethod
    public void beforeMethod() {
        if (driver == null){
            initDriver();
        }
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel");
    }

    @AfterClass
    public void AfterClass() {
        removeAccessToken();
    }


}
