package com.historycode.ui.page.adminpanel.analyticspage;

import org.openqa.selenium.WebElement;

public class AnalyticsRowCompoment {

    //TODO add element search

    private WebElement address;

    private WebElement QRCodeTransitionsNumber;

    private WebElement  QRCodeNumber;

    private WebElement coordinates;

    public String getAddressText(){return address.getText();}

    public String getQRCodeTransitionsNumber(){return QRCodeTransitionsNumber.getText();}

    public String getQRCodeNumber(){return QRCodeNumber.getText();}

    public String getCoordinates(){return coordinates.getText();}

}
