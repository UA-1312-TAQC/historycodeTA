package com.historycode.ui.page;

import com.historycode.ui.Base;
import com.historycode.ui.component.footer.FooterComponent;
import com.historycode.ui.component.header.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@Getter
public abstract class BasePage extends Base {

    @FindBy(xpath = "//div[@class='HeaderBlock']")
    private WebElement headerNode;

    @FindBy(css = ".footerContainer")
    private WebElement footerNode;


    protected HeaderComponent header;
    protected FooterComponent footer;

    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.header = new HeaderComponent(driver, headerNode);
        this.footer = new FooterComponent(driver, footerNode);

    }
}
