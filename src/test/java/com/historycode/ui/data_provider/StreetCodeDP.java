package com.historycode.ui.data_provider;

import org.testng.annotations.DataProvider;

public class StreetCodeDP {
    /**
     * Provides a set of URLs as test data for TestNG data-driven testing.
     *
     * @return A two-dimensional array of URL strings to be used in test scenarios
     * @see DataProvider
     */
    @DataProvider(name = "urlSetProvider")
    public Object[][] urlSetProvider() {
        return new Object[][]{
                {"/sichovi-striltsi"},
                {"/khrystyna-skachkivska-sushko"},
                {"/braty-chyzhevski"}
        };
    }

    /**
     * Provides a single URL for testing purposes.
     *
     * @return A two-dimensional array containing one URL string for use in test data
     */
    @DataProvider(name = "urlProvider")
    public Object[][] urlProvider() {
        return new Object[][]{
                {"/sichovi-striltsi"}
        };
    }
}
