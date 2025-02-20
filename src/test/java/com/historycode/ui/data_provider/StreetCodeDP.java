package com.historycode.ui.data_provider;

import com.historycode.ui.data_provider.enums.SocialMedia;
import org.testng.annotations.DataProvider;

public class StreetCodeDP {
    @DataProvider(name = "indexTeaserSetProvider")
    public Object[][] indexTeaserSetProvider() {
        return new Object[][]{
                {0}, {1}, {5}, {6}, {7}
        };
    }

    @DataProvider(name = "urlWowFactSetProvider")
    public Object[][] urlWowFactSetProvider() {
        return new Object[][]{
                {0}, {1}, {3}
        };
    }

    @DataProvider(name = "urlOneWowFactSetProvider")
    public Object[][] urlOneWowFactSetProvider() {
        return new Object[][]{
                {0}
        };
    }

    @DataProvider(name = "socialMediaDataProvider")
    public Object[][] socialMediaDataProvider() {
        Object[] values = SocialMedia.values();
        Object[][] res = new Object[values.length][];
        for (int i = 0; i < values.length; i++) {
            res[i] = new Object[]{values[i]};
        }
        return res;
    }

    @DataProvider(name = "urlProviderForTextBlock")
    public Object[][] urlProviderForTextBlock() {
        return new Object[][]{
                {"/roman-ratushnyi-seneka"},
                {"/vasyl-stus"}
        };
    }
}
