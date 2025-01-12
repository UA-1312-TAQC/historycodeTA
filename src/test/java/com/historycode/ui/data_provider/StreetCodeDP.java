package com.historycode.ui.data_provider;

import org.testng.annotations.DataProvider;

public class StreetCodeDP {
    @DataProvider(name = "urlSetProvider")
    public Object[][] urlSetProvider() {
        return new Object[][]{
                {"sichovi-striltsi"},
                {"khrystyna-skachkivska-sushko"},
                {"braty-chyzhevski"}
        };
    }

    @DataProvider(name = "urlProvider")
    public Object[][] urlProvider() {
        return new Object[][]{
                {"sichovi-striltsi"}
        };
    }
}
