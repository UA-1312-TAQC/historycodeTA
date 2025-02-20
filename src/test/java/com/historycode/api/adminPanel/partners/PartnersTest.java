package com.historycode.api.adminPanel.partners;

import com.github.dockerjava.transport.DockerHttpClient;
import com.historycode.api.clients.PartnersClient;
import com.historycode.api.clients.TagClient;
import com.historycode.api.models.partners.PartnerRequestBody;
import com.historycode.api.models.partners.PartnerSourceLink;
import com.historycode.api.models.partners.PartnerUpdateRequest;
import com.historycode.api.models.partners.PartnersStreetcodes;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class PartnersTest extends ApiTestRunner {
    PartnersClient client;
    SoftAssert softAssert;
    private final List<Integer> createdPartnerIds = new ArrayList<>();

    @BeforeClass
    public void setUpClass() {
        client = new PartnersClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        for (Integer id : createdPartnerIds) {
            client.deletePartner(id);
        }
        createdPartnerIds.clear();
    }
  
  
    @Issue("264")
    @Test
    @Description("Verify create a partner with a mandatory fields")
    public void createPartnerWithMandatoryField() {
        PartnerRequestBody partnerRequestBody = new PartnerRequestBody();

        partnerRequestBody.setKeyPartner(false);
        partnerRequestBody.setVisibleEverywhere(false);
        partnerRequestBody.setTitle("Enzo Fernandez");
        partnerRequestBody.setDescription("");
        partnerRequestBody.setTargetUrl(null);
        partnerRequestBody.setLogoId(6868);
        partnerRequestBody.setUrlTitle(null);
        partnerRequestBody.setPartnerSourceLinks(List.of());
        partnerRequestBody.setStreetcodes(List.of());

        Response response = client.create(partnerRequestBody);

        softAssert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        softAssert.assertEquals(response.jsonPath().getString("title"), "Enzo Fernandez", "Title mismatch");
        softAssert.assertEquals(response.jsonPath().getBoolean("isKeyPartner"), false, "isKeyPartner should be false");
        softAssert.assertEquals(response.jsonPath().getBoolean("isVisibleEverywhere"), false, "isVisibleEverywhere should be false");
        softAssert.assertEquals(response.jsonPath().getString("description"), "", "Description should be empty");
        softAssert.assertEquals(response.jsonPath().getInt("logoId"), 6868, "logoId mismatch");
        softAssert.assertNull(response.jsonPath().getMap("targetUrl").get("title"), "targetUrl.title should be null");
        softAssert.assertNull(response.jsonPath().getMap("targetUrl").get("href"), "targetUrl.href should be null");;
        softAssert.assertNull(response.jsonPath().get("urlTitle"), "urlTitle should be null");
        softAssert.assertTrue(response.jsonPath().getList("partnerSourceLinks").isEmpty(), "partnerSourceLinks should be empty");
        softAssert.assertTrue(response.jsonPath().getList("streetcodes").isEmpty(), "streetcodes should be empty");

        softAssert.assertAll();
    }

    @Issue("273")
    @Test
    @Description("Try to create a partner with an existing name in the system.")
    public void createPartnerWithExistingName() {
        PartnerRequestBody partnerRequestBody = new PartnerRequestBody();

        partnerRequestBody.setKeyPartner(false);
        partnerRequestBody.setVisibleEverywhere(false);
        partnerRequestBody.setTitle("Reece James");
        partnerRequestBody.setDescription("");
        partnerRequestBody.setTargetUrl(null);
        partnerRequestBody.setLogoId(6878);
        partnerRequestBody.setUrlTitle(null);
        partnerRequestBody.setPartnerSourceLinks(List.of(new PartnerSourceLink(0, 1, "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVu")));
        partnerRequestBody.setStreetcodes(List.of());

        Response response = client.create(partnerRequestBody);

        softAssert.assertEquals(response.statusCode(), 400, "Expected status code to be 400");
        softAssert.assertTrue(response.getBody().asString().contains("Поле 'Назва' має бути унікальним"), "Error message should contain 'Поле 'Назва' має бути унікальним'");

        softAssert.assertAll();
    }

    @Issue("274")
    @Test
    @Description("Try to create a partner with a long targetUrl (256 characters) in the partnerSourceLinks.")
    public void createPartnerWithLongTargetUrl() {
        String longUrl = "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVu"
                + "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVu"
                + "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVu"
                + "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGF1";

        PartnerRequestBody partnerRequestBody = new PartnerRequestBody();
        partnerRequestBody.setKeyPartner(false);
        partnerRequestBody.setVisibleEverywhere(false);
        partnerRequestBody.setTitle("Reece James");
        partnerRequestBody.setDescription("");
        partnerRequestBody.setTargetUrl(null);
        partnerRequestBody.setLogoId(6879);
        partnerRequestBody.setUrlTitle(null);
        partnerRequestBody.setPartnerSourceLinks(List.of(new PartnerSourceLink(0, 1, longUrl)));
        partnerRequestBody.setStreetcodes(List.of());

        Response response = client.create(partnerRequestBody);

        softAssert.assertEquals(response.statusCode(), 400, "Expected status code to be 400");
        softAssert.assertTrue(response.getBody().asString().contains("Максимальна довжина поля 'Посилання на соціальну мережу' - 255"),
                "Error message should be returned for an overly long URL");

        softAssert.assertAll();
    }

    @Issue("272")
    @Test
    @Description("Try to add two identical links to the 'partnerSourceLinks' field")
    public void createPartnersWithIdenticalLinks() {
        PartnerRequestBody partnerRequestBody = new PartnerRequestBody();

        partnerRequestBody.setKeyPartner(false);
        partnerRequestBody.setVisibleEverywhere(false);
        partnerRequestBody.setTitle("Reece James");
        partnerRequestBody.setDescription("");
        partnerRequestBody.setTargetUrl(null);
        partnerRequestBody.setLogoId(6877);
        partnerRequestBody.setUrlTitle(null);
        partnerRequestBody.setPartnerSourceLinks(List.of(
                new PartnerSourceLink(0, 1, "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVu"),
                new PartnerSourceLink(1, 1, "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVu")
        ));
        partnerRequestBody.setStreetcodes(List.of());

        Response response = client.create(partnerRequestBody);

        softAssert.assertEquals(response.statusCode(), 400, "Expected status code to be 400");
        softAssert.assertTrue(
                response.getBody().asString().contains("Посилання на таку соціальну мережу вже додано"),
                "Error message should contain 'Посилання на таку соціальну мережу вже додано'");
        softAssert.assertAll();
    }

    @Issue("276")
    @Test
    @Description("Try to create a partner with non-existent streetcodes.")
    public void createPartnerWithNonExistentStreetcodes() {
        PartnerRequestBody partnerRequestBody = new PartnerRequestBody();

        partnerRequestBody.setKeyPartner(false);
        partnerRequestBody.setVisibleEverywhere(false);
        partnerRequestBody.setTitle("Diego Lopes");
        partnerRequestBody.setDescription("");
        partnerRequestBody.setTargetUrl(null);
        partnerRequestBody.setLogoId(6882);
        partnerRequestBody.setUrlTitle(null);
        partnerRequestBody.setPartnerSourceLinks(List.of(
                new PartnerSourceLink(0, 1, "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVuhttps")
        ));
        partnerRequestBody.setStreetcodes(List.of(
                new PartnersStreetcodes(100, "Дієго Лопес"),
                new PartnersStreetcodes(200, "Алекс Перейра")
        ));

        Response response = client.create(partnerRequestBody);

        softAssert.assertEquals(response.statusCode(), 400, "Expected status code to be 400");
        softAssert.assertTrue(
                response.getBody().asString().contains("Немає існуючого стріткоду з ідентифікатором:"),
                "Error message should contain 'Немає існуючого стріткоду з ідентифікатором:'");
        softAssert.assertAll();
    }

    @Issue("271")
    @Test
    @Description("Try to create a partner with an invalid link in 'partnerSourceLinks'.")
    public void createPartnerWithInvalidLink() {
        PartnerRequestBody partnerRequestBody = new PartnerRequestBody();

        partnerRequestBody.setKeyPartner(false);
        partnerRequestBody.setVisibleEverywhere(false);
        partnerRequestBody.setTitle("Test Partner");
        partnerRequestBody.setDescription("");
        partnerRequestBody.setTargetUrl("http://www.google.com/");
        partnerRequestBody.setLogoId(5960);
        partnerRequestBody.setUrlTitle("");
        partnerRequestBody.setPartnerSourceLinks(List.of(
                new PartnerSourceLink(0, 1, "https://x.com/tekkersfoot?s=11&t=hchba8oCpC6HXII8gCCBQQ")
        ));
        partnerRequestBody.setStreetcodes(List.of());

        Response response = client.create(partnerRequestBody);
        createdPartnerIds.add(response.jsonPath().getInt("id"));

        softAssert.assertEquals(response.statusCode(), 400, "Expected status code to be 400");
        softAssert.assertTrue(response.getBody().asString().contains("Логотип має відповідати посиланню"),
                "Error message should be returned for an invalid link in 'partnerSourceLinks'");

        softAssert.assertAll();
    }

    @Issue("270")
    @Test
    @Description("Try to update a partner and add a link to the 'partnerSourceLinks' field.")
    public void updatePartnerWithLink() {
        PartnerUpdateRequest partnerUpdateRequest = new PartnerUpdateRequest();

        partnerUpdateRequest.setKeyPartner(false);
        partnerUpdateRequest.setVisibleEverywhere(false);
        partnerUpdateRequest.setTitle("Test Chelsea 1");
        partnerUpdateRequest.setDescription("");
        partnerUpdateRequest.setTargetUrl("http://www.google.com/");
        partnerUpdateRequest.setLogoId(5960);
        partnerUpdateRequest.setUrlTitle("");
        partnerUpdateRequest.setPartnerSourceLinks(List.of(
                new PartnerSourceLink(0, 1, "https://www.instagram.com/transfermarkt_official?igsh=MWR6eTEzZWc4MXNiNg==")
        ));
        partnerUpdateRequest.setStreetcodes(List.of());
        partnerUpdateRequest.setId(3102);

        Response response = client.updatePartner(partnerUpdateRequest);
        createdPartnerIds.add(response.jsonPath().getInt("id"));

        softAssert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        softAssert.assertTrue(response.getBody().asString().contains("Test Chelsea 1"),
                "Response should contain the title 'Test Chelsea 1'");
        softAssert.assertTrue(response.getBody().asString().contains("https://www.instagram.com/transfermarkt_official?igsh=MWR6eTEzZWc4MXNiNg=="),
                "Response should contain the link added to 'partnerSourceLinks'");

        softAssert.assertAll();
    }

    @Issue("275")
    @Test
    @Description("Try to create a partner with existing streetcodes.")
    public void createPartnerWithExistingStreetcodes() {
        PartnerRequestBody partnerRequestBody = new PartnerRequestBody();

        partnerRequestBody.setKeyPartner(false);
        partnerRequestBody.setVisibleEverywhere(false);
        partnerRequestBody.setTitle("Reece James");
        partnerRequestBody.setDescription("");
        partnerRequestBody.setTargetUrl(null);
        partnerRequestBody.setLogoId(6881);
        partnerRequestBody.setUrlTitle(null);
        partnerRequestBody.setPartnerSourceLinks(List.of(
                new PartnerSourceLink(0, 1, "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVuhttps")
        ));
        partnerRequestBody.setStreetcodes(List.of(
                new PartnersStreetcodes(438, "Роман Рáтушний «Сенека»"),
                new PartnersStreetcodes(439, "Олекса (Олексій) Алмазов (Алмазів)")
        ));

        Response response = client.create(partnerRequestBody);
        createdPartnerIds.add(response.jsonPath().getInt("id"));

        softAssert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        softAssert.assertTrue(response.getBody().asString().contains("Reece James"),
                "Response should contain the title 'Reece James'");
        softAssert.assertTrue(response.getBody().asString().contains("Роман Рáтушний «Сенека»"),
                "Response should contain the first streetcode 'Роман Рáтушний «Сенека»'");
        softAssert.assertTrue(response.getBody().asString().contains("Олекса (Олексій) Алмазов (Алмазів)"),
                "Response should contain the second streetcode 'Олекса (Олексій) Алмазов (Алмазів)'");

        softAssert.assertAll();
    }
}
