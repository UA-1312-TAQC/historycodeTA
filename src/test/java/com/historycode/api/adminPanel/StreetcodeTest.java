package com.historycode.api.adminPanel;

import com.historycode.api.clients.StreetcodeClient;
import com.historycode.api.models.adminPanel.news.NewsRequestBody;
import com.historycode.api.models.adminPanel.streetcode.ImageDetails;
import com.historycode.api.models.adminPanel.streetcode.StreetcodeRequestBody;
import com.historycode.api.models.adminPanel.streetcode.Subtitle;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StreetcodeTest extends ApiTestRunner {
    private StreetcodeClient client;
    private StreetcodeRequestBody requestBody;

    int id;
    @BeforeClass
    public void setUpClass() {
        client = new StreetcodeClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
    }

    @Test
    @Issue("260")
    public void createStreetcodeTest() {
        ImageDetails imageDetails = new ImageDetails()
                .setId(0)
                .setImageId(5879)
                .setAlt("1");
        requestBody = new StreetcodeRequestBody()
                .setIndex(623)
                .setStreetcodeType(1)
                .setTitle("qwe")
                .setFirstName("")
                .setLastName("")
                .setAlias("")
                .setTransliterationUrl("qwe-ewq")
                .setEventStartOrPersonBirthDate(new Date("2024-03-31T13:46:48.769Z"))
                .setTags(new ArrayList<>())
                .setTeaser("efasf")
                .setImagesIds(List.of(5879))
                .setImagesDetails(List.of(imageDetails))
                .setVideos(new ArrayList<>())
                .setFacts(new ArrayList<>())
                .setTimelineItems(new ArrayList<>())
                .setArts(new ArrayList<>())
                .setStreetcodeArtSlides(new ArrayList<>())
                .setRelatedFigures(new ArrayList<>())
                .setStreetcodeCategoryContents(new ArrayList<>())
                .setPartners(new ArrayList<>())
                .setSubtitles(List.of(new Subtitle("")))
                .setViewCount(0)
                .setCoordinates(new ArrayList<>())
                .setToponyms(new ArrayList<>())
                .setStatisticRecords(new ArrayList<>())
                .setStatus(0);
        Response response = client.createStreetcode(requestBody);
        Assert.assertEquals(response.getStatusCode(), 200);
        response.body().print();
        Assert.fail("Test is not fully implemented");
    }

    @AfterMethod
    public void deleteStreetcode(){
        //TODO finish this method
        //client.softDeleteStreetcode(id);
    }
}
