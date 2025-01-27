package com.historycode.ui.data_provider;

import org.testng.annotations.DataProvider;

public class StreetCodeDP {
    //TODO: change data
    @DataProvider(name = "urlTeaserSetProvider")
    public Object[][] urlTeaserSetProvider() {
        return new Object[][]{
                {"/sichovi-striltsi"},
                {"/khrystyna-skachkivska-sushko"},
                {"/braty-chyzhevski"},
                {"/ivan-mazepa"}
        };
    }

    @DataProvider(name = "urlWowFactSetProvider")
    public Object[][] urlWowFactSetProvider() {
        return new Object[][]{
                {"/sichovi-striltsi"},
                {"/khrystyna-skachkivska-sushko"}
        };
    }

    @DataProvider(name = "urlProviderForTextBlock")
    public Object[][] urlProviderForTextBlock(){
        return new Object[][]{
                {"/roman-ratushnyi-seneka"},
                {"/vasyl-stus"}
        };
    }
}
