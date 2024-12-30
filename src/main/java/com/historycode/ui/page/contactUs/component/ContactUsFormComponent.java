package com.historycode.ui.page.contactUs.component;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ContactUsFormComponent extends BaseComponent {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div/div/div/div[2]/div[2]/div/div[1]")
    private WebElement formTitle;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div/div/div/div[2]/div[2]/div/div[2]")
    private WebElement formSubTitle;
    @FindBy(xpath = "//*[@id=\"message\"]")
    private WebElement message;
    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement email;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div/div/div/div[2]/div[2]/form/div[4]/div/div/div/div/button")
    private WebElement button;
    public ContactUsFormComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
