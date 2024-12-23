package com.historycode.ui.elements.news;

import com.historycode.ui.elements.BaseElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class RelatedNewsElement extends BaseElement {

    @FindBy(xpath = "//div[contains(@class,'randomNewsTitleAndButtn')]")
    private WebElement relatedNewsTitle;

    @FindBy(xpath = "//div[contains(@class,'newsButtonContainer')]")
    private WebElement relatedNewsButton;

    public RelatedNewsElement(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickRelatedNewsButton(){
        relatedNewsButton.click();
    }
}
