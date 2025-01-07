package com.historycode.ui.page.adminpanel.loginpage;

import com.historycode.ui.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageAdminPanel extends BasePage {

    private final String LOGIN_CSS = "form .ant-form-item:first-child div";
    private final String PASSWORD_CSS = "form .ant-form-item:nth-child(2) div";


    private final LoginInputComponent login;
    private final LoginInputComponent password;


    @FindBy(css = "button.loginBtn")
    private WebElement sighInButton;


    public LoginPageAdminPanel(WebDriver driver) {
        super(driver);
        login = new LoginInputComponent(driver, driver.findElement(By.cssSelector(LOGIN_CSS)));
        password = new LoginInputComponent(driver, driver.findElement(By.cssSelector(PASSWORD_CSS)));
    }

    public String getLoginLabel() {
        return login.getLabel();
    }

    public String getPasswordLabel() {
        return password.getLabel();
    }

    public void enterLogin(String inputData) {
        login.fillInput(inputData);
    }

    public void enterPassword(String inputData) {
        password.fillInput(inputData);
    }


    public void clickSighInButtonPositive() {
        sighInButton.click();
//        return new StreetCodeCatalogPageAdminPanel
    }


    public LoginPageAdminPanel clickSighInButtonNegative() {
        sighInButton.click();
        return this;
    }

}

