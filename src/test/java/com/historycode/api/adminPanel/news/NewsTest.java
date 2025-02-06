package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.adminPanel.NewsClient;
import com.historycode.api.models.adminPanel.news.GetAllNewsResponse;
import com.historycode.api.testRunners.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewsTest extends ApiTestRunner {
    NewsClient client;
    @BeforeClass
    public void setUpClass(){
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
    }

    @Test
    public void testGetAllNews(){
        Response response = client.getAll();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        GetAllNewsResponse getAllNewsResponse = response.body().as(GetAllNewsResponse.class);
        softAssert.assertFalse(getAllNewsResponse.getNews() == null || getAllNewsResponse.getNews().isEmpty(), "The news ");
        softAssert.assertAll();
    }

}
