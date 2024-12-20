package com.historycode.ui.page;

import com.historycode.ui.Base;
import com.historycode.ui.component.header.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Getter
public abstract class BasePage extends Base {

    @FindBy(xpath = "//div[@class='HeaderBlock']")
    private WebElement headerNode;



    protected HeaderComponent header;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver, this.headerNode);

    }
}
