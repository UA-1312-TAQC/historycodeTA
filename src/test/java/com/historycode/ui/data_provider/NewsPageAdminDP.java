package com.historycode.ui.data_provider;

import org.testng.annotations.DataProvider;

public class NewsPageAdminDP {
    @DataProvider(name = "invalidLinks")
    public static Object[][] getInvalidLinks() {
        return new Object[][] {
            {"TESTLINK", "158.1"},
            {"Тестлінк", "158.2"},
            {"№\"?:*", "158.3"}
        };
    }
}
