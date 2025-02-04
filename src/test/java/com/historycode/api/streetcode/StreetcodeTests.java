package com.historycode.api.streetcode;

import com.historycode.api.clients.StreetcodeClient;
import com.historycode.api.models.Streetcode.GetAllResponse;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StreetcodeTests extends ApiTestRunner{
    StreetcodeClient client;


    @BeforeClass
    public void setUpClass(){
        client = new StreetcodeClient(testValueProvider.getBaseAPIUrl());
    }


    @Test
    public void testGetAll(){
        Response response = client.getAll();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        GetAllResponse getAllResponse = response.body().as( GetAllResponse.class);

        softAssert.assertEquals(getAllResponse.getStreetcodes().size(), 40);
        softAssert.assertEquals(getAllResponse.getTotalAmount(), 40);
        System.out.println(getAllResponse);
        softAssert.assertAll();

    }
    @Test
    public void testGetAllParams(){
        Response response = client.getAll(4, 3);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        GetAllResponse getAllResponse = response.body().as( GetAllResponse.class);

        softAssert.assertEquals(getAllResponse.getTotalAmount(), 40);
        softAssert.assertEquals(getAllResponse.getStreetcodes().size(), 3);
        softAssert.assertEquals(getAllResponse.getStreetcodes().get(0).getId(), 520);
        System.out.println(getAllResponse);
        softAssert.assertAll();

    }

}
