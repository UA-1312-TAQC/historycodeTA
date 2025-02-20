package com.historycode.api.adminPanel.partners;

import com.github.dockerjava.transport.DockerHttpClient;
import com.historycode.api.clients.PartnersClient;
import com.historycode.api.clients.TagClient;
import com.historycode.api.models.partners.PartnerRequestBody;
import com.historycode.api.models.partners.PartnerSourceLink;
import com.historycode.api.models.partners.PartnersStreetcodes;
import com.historycode.api.testRunners.ApiTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class PartnersTest extends ApiTestRunner {
    PartnersClient client;
    SoftAssert softAssert;

    @BeforeClass
    public void setUpClass() {
        client = new PartnersClient(testValueProvider.getBaseAPIUrl());
        client.setToken(testValueProvider.getAccessToken());
        softAssert = new SoftAssert();
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

        System.out.println("Response Body: " + response.getBody().asString());
        softAssert.assertEquals(response.statusCode(), 400, "Expected status code to be 400");
        softAssert.assertTrue(
                response.getBody().asString().contains("Обрані 'streetcodes' не існує у системі"),
                "Error message should contain 'Обрані 'streetcodes' не існує у системі'");
        softAssert.assertAll();
    }

}
