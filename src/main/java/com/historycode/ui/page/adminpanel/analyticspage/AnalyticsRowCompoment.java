package com.historycode.ui.page.adminpanel.analyticspage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AnalyticsRowCompoment extends BaseComponent {

    //TODO add element search

    private WebElement address;

    private WebElement QRCodeTransitionsNumber;

    private WebElement  QRCodeNumber;

    private WebElement coordinates;

    public AnalyticsRowCompoment(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getAddressText(){return address.getText().trim();}

    public String getQRCodeTransitionsNumber(){return QRCodeTransitionsNumber.getText().trim();}

    public String getQRCodeNumber(){return QRCodeNumber.getText().trim();}

    public String getCoordinates(){return coordinates.getText().trim();}

}
