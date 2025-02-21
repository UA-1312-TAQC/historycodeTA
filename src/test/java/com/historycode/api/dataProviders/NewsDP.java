package com.historycode.api.dataProviders;

import org.testng.annotations.DataProvider;

public class NewsDP {
    @DataProvider(name = "specialSymbolsDataProvider")
    public Object[][] specialSymbolsDataProvider() {
        return new Object[][]{
                {"$"}, {"@"}, {"%"}, {"#"}, {"&"}, {"^"}
        };
    }

}
