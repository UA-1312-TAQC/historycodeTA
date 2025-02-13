package com.historycode.api.dataProviders;

import com.historycode.api.dataProviders.enums.Status;
import org.testng.annotations.DataProvider;

public class StreetcodeDataProvider {

    @DataProvider(name = "streetcodeStatusDataProvider")
    public Object[][] streetcodeStatusDataProvider() {
        Object[] values = Status.values();
        Object[][] res = new Object [values.length][];
        for(int i = 0; i< values.length; i++){
            res[i] = new Object[]{values[i]};
        }
        return res;
    }
}
