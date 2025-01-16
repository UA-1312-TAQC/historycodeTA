package com.historycode.ui.page.adminpanel.loginpage;

import com.historycode.ui.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    public LoginPageAdminPanel enterLogin(String inputData) {
        login.fillInput(inputData);
        return this;
    }

    public LoginPageAdminPanel enterPassword(String inputData) {
        password.fillInput(inputData);
        return this;
    }


    public void clickSighInButtonPositive() {
        sighInButton.click();
//        return new StreetCodeCatalogPageAdminPanel
    }


    public LoginPageAdminPanel clickSighInButtonNegative() {
        sighInButton.click();
        return this;
    }

    public LoginPageAdminPanel clickCaptcha() {
        WebElement passwordLabel = driver.findElement(By.xpath("//*[@id='root']/div/div[4]/div[2]/form/div[2]/div/div[1]/label"));
        sleep(3000);
        new Actions(driver).moveToElement(passwordLabel).moveByOffset(30, 70).click().perform();
        sleep(10000);
        return this;
    }

}

