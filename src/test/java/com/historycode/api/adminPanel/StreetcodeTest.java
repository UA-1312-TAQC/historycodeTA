package com.historycode.api.adminPanel;

import com.historycode.api.clients.StreetcodeClient;
import com.historycode.api.models.adminPanel.streetcode.ImageDetails;
import com.historycode.api.models.adminPanel.streetcode.StreetcodeRequestBody;
import com.historycode.api.models.adminPanel.streetcode.Subtitle;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StreetcodeTest extends ApiTestRunner {
    private StreetcodeClient client;
    private StreetcodeRequestBody requestBody;
    int streetcodeId;
    int id;
    @BeforeClass
    public void setUpClass() {
        client = new StreetcodeClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
    }

    @Test
    @Issue("260")
    public void createStreetcodeTest() {

        String dateString = "2024-03-31T13:46:48.769Z";
        Instant instant = Instant.parse(dateString);
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
                .setEventStartOrPersonBirthDate(instant)
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
        log.debug(requestBody.toString());
        Response response = client.createStreetcode(requestBody);
        Assert.assertEquals(response.getStatusCode(), 200);
        response.body().print();
        streetcodeId = response.jsonPath().getInt("id");
        Assert.assertNotEquals(streetcodeId, 0);
    }

    @AfterMethod
    public void deleteStreetcode(){
        if(id!= 0)
            client.softDeleteStreetcode(id);
    }
}
