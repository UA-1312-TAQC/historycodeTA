package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.NewsClient;
import com.historycode.api.testRunners.ApiTestRunner;
import org.testng.annotations.BeforeClass;

public class APNewsDeleteTests extends ApiTestRunner {
    NewsClient client;

    @BeforeClass
    public void setUpClass() {
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
    }



}
