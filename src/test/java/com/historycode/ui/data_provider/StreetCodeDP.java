package com.historycode.ui.data_provider;

import com.historycode.ui.data_provider.enums.SocialMedia;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Iterator;

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

    @DataProvider(name = "socialMediaDataProvider")
    public Object[][] socialMediaDataProvider() {
        Object[] values = SocialMedia.values();
        Object[][] res = new Object [values.length][];
        for(int i = 0; i< values.length; i++){
            res[i] = new Object[]{values[i]};
        }
        return res;
    }
}
