package com.historycode.api.streetcode;

import com.historycode.api.clients.NewsClient;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class APINewsTest extends ApiTestRunner {
    private NewsClient newsClient;

        @BeforeClass
        public void init() {
            newsClient = new NewsClient(testValueProvider.getBaseAPIUrl());
        }

    @Issue("190")
    @Test
    @Description("Verify if all news are displayed using GET method.")
        public void testGetAllNews() {
            Response response = newsClient.getAllNews();

            assertEquals(response.getStatusCode(), 200, "Response status is not 200 OK");

            assertTrue(response.getContentType().contains("application/json"), "Response is not in JSON format");

            assertNotNull(response.jsonPath().get("id"), "Key 'id' is missing in the response");
            assertNotNull(response.jsonPath().get("title"), "Key 'title' is missing in the response");
            assertNotNull(response.jsonPath().get("text"), "Key 'text' is missing in the response");
            assertNotNull(response.jsonPath().get("creationDate"), "Key 'creationDate' is missing in the response");

            assertNotNull(response.jsonPath().get("image.id"), "Key 'image.id' is missing in the response");
            assertNotNull(response.jsonPath().get("image.blobName"), "Key 'image.blobName' is missing in the response");
            assertNotNull(response.jsonPath().get("image.mimeType"), "Key 'image.mimeType' is missing in the response");

            assertNotNull(response.jsonPath().get("image.imageDetails.id"), "Key 'image.imageDetails.id' is missing in the response");
            assertNotNull(response.jsonPath().get("image.imageDetails.title"), "Key 'image.imageDetails.title' is missing in the response");
            assertNotNull(response.jsonPath().get("image.imageDetails.alt"), "Key 'image.imageDetails.alt' is missing in the response");

            System.out.println("Response: " + response.asString());
        }
    }

