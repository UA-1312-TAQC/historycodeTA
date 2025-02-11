package com.historycode.api.streetcode;

import com.historycode.api.clients.TagClient;
import com.historycode.api.models.tag.TagRequestBody;
import com.historycode.api.models.tag.TagResponse;
import com.historycode.api.testRunners.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TagTests extends ApiTestRunner {
    TagClient client;


    @BeforeClass
    public void setUpClass(){
        client = new TagClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
    }

    @Test
    public void testCreateTag(){
        TagRequestBody newTag = new TagRequestBody();
        String newTagTitle = "TestTag" + System.currentTimeMillis();
        newTag.setTitle(newTagTitle);
        Response response = client.create(newTag);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);

        TagResponse tagResponse = response.body().as(TagResponse.class);

        softAssert.assertEquals(tagResponse.getTitle(), newTagTitle);
        Response deleteResponse = client.delete(tagResponse.getId());
        softAssert.assertEquals(deleteResponse.getStatusCode(), 200);
        softAssert.assertAll();
    }

}
