package com.historycode.api.adminPanel;

import com.historycode.api.clients.StreetcodeClient;
import com.historycode.api.dataProviders.StreetcodeDataProvider;
import com.historycode.api.dataProviders.enums.Status;
import com.historycode.api.models.adminPanel.streetcode.ImageDetails;
import com.historycode.api.models.adminPanel.streetcode.StreetcodeRequestBody;
import com.historycode.api.models.adminPanel.streetcode.Subtitle;
import com.historycode.api.testRunners.ApiTestRunner;
import com.historycode.utils.ImageCreator;
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
    @BeforeClass
    public void setUpClass() {
        client = new StreetcodeClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
    }


    public void createStreetcode(){
        String dateString = "2024-03-31T13:46:48.769Z";
        int imageId = ImageCreator.createNewImg("src/test/resources/logo.jpeg");
        Instant instant = Instant.parse(dateString);
        ImageDetails imageDetails = new ImageDetails()
                .setId(0)
                .setImageId(imageId)
                .setAlt("1");
        requestBody = new StreetcodeRequestBody()
                .setIndex(625)
                .setStreetcodeType(1)
                .setTitle("qwe")
                .setFirstName("")
                .setLastName("")
                .setAlias("")
                .setTransliterationUrl("qwe-ewqfefd")
                .setDateString("65")
                .setEventStartOrPersonBirthDate(instant)
                .setTags(new ArrayList<>())
                .setTeaser("efasfdfdfd")
                .setImagesIds(List.of(imageId))
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
    }

    public int getNewStreetcodeId(){
        createStreetcode();
        Response response = client.createStreetcode(requestBody);
        streetcodeId =  Integer.parseInt(response.getBody().asString());
        return this.streetcodeId;
    }

    @Test
    @Issue("260")
    public void createStreetcodeTest() {
        createStreetcode();
        log.debug(requestBody.toString());
        Response response = client.createStreetcode(requestBody);
        response.body().print();
        Assert.assertEquals(response.getStatusCode(), 200);
        streetcodeId = Integer.parseInt(response.getBody().asString());
        log.debug(String.valueOf(streetcodeId));
        Assert.assertNotEquals(streetcodeId, 0);
    }



    @Issue("263")
    @Test(dataProvider = "streetcodeStatusDataProvider",  dataProviderClass = StreetcodeDataProvider.class)
    public void streetcodeStatusChangeTest(Status status){
        createStreetcode();
        requestBody.setStatus(status.getCode());
        Response response = client.updateStreetcode(requestBody);
        response.body().print();
        Assert.assertEquals(response.getStatusCode(), 200);
        streetcodeId = Integer.parseInt(response.getBody().asString());
        log.debug(String.valueOf(streetcodeId));
    }

    @Issue("263")
    @Test(dataProvider = "streetcodeStatusDataProvider",  dataProviderClass = StreetcodeDataProvider.class)
    public void streetcodePatchStatusTest(Status status){
        int id = getNewStreetcodeId();
        Response response = client.patchStage(id, status.getCode());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @AfterMethod
    public void deleteStreetcode(){
        if(streetcodeId!= 0)
            client.deleteStreetcode(streetcodeId);
    }
}
