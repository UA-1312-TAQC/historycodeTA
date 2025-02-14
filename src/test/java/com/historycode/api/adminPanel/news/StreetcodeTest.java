package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.StreetcodeClient;
import com.historycode.api.dataProviders.StreetcodeDataProvider;
import com.historycode.api.dataProviders.enums.Status;
import com.historycode.api.models.adminPanel.streetcode.StreetcodeByIndexResponse;
import com.historycode.api.models.streetcode.GetAllResponse;
import com.historycode.api.models.streetcode.StreetcodeResponse;
import com.historycode.api.testRunners.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StreetcodeTest extends ApiTestRunner {
    StreetcodeClient client;
    @BeforeClass
    public void setUpClass(){
        client = new StreetcodeClient(testValueProvider.getBaseAPIUrl());
    }

    @Test(dataProvider = "streetcodeStatusDataProvider",  dataProviderClass = StreetcodeDataProvider.class)
    public void streetcodeFilterTest(Status status){
        Response response = client.getAll(status.getQueryParameter());
        Assert.assertEquals(response.getStatusCode(), 200);
        GetAllResponse getAllResponse = response.body().as( GetAllResponse.class);

        SoftAssert softAssert = new SoftAssert();
        for(StreetcodeResponse streetcode : getAllResponse.getStreetcodes()){
            softAssert.assertEquals(streetcode.getStatus(), status.getCode(),
                    String.format("Streetcode with id %d has status id %d but must have status id %d",streetcode.getId(), streetcode.getStatus(), status.getCode()));
        }
        softAssert.assertAll();
    }

    @Test
    public void streetcodePageTest(){
        int validStreetcodeIndex = 1;
        Response response = client.getByIndex(validStreetcodeIndex);
        Assert.assertEquals(response.getStatusCode(), 200);
        response.getBody().print();
        StreetcodeByIndexResponse streetcodeResponse = response.body().as(StreetcodeByIndexResponse.class);

        System.out.println(streetcodeResponse.toString());
        Assert.assertEquals(streetcodeResponse.getIndex(), validStreetcodeIndex);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(streetcodeResponse.getTitle().isEmpty(), "The streetcode name is empty");
        softAssert.assertFalse(streetcodeResponse.getCreatedBy().isEmpty(), "The streetcode author field is empty");
        softAssert.assertAll();
    }
}
