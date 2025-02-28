package com.historycode.utils;

import com.historycode.TestValueProvider;
import com.historycode.api.clients.ImageClient;
import com.historycode.api.models.img.ImageRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import static com.historycode.utils.ImageProcessor.encodeImage;

public class ImageCreator {
    @Step("Creating a new image for a request")
    public static int createNewImg(String path) {
        ImageClient imageClient = new ImageClient(new TestValueProvider().getBaseAPIUrl());
        ImageRequest newsImage = new ImageRequest();

        newsImage.setTitle("TestImg" + System.currentTimeMillis());
        newsImage.setBaseFormat(encodeImage(path));
        newsImage.setMimeType("image/jpeg");
        newsImage.setExtension("jpeg");
        newsImage.setAlt("1");

        Response response = imageClient.post(newsImage);
        Assert.assertEquals(response.getStatusCode(), 200, "Image was not created");

        return response.getBody().jsonPath().getInt("id");
    }
}
