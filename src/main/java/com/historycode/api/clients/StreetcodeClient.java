package com.historycode.api.clients;

import com.historycode.api.models.adminPanel.streetcode.StreetcodeRequestBody;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class StreetcodeClient extends BaseClient {
    protected String resourceUrl = "/Streetcode";
    public StreetcodeClient(String baseUrl) {
        super(baseUrl);
    }

    public StreetcodeClient(String baseUrl, ContentType contentType) {
        super(baseUrl, contentType);
    }

    public StreetcodeClient(String baseUrl, String contentType) {
        super(baseUrl, contentType);
    }
    public Response getAll() {
        return preparedRequest()
                .when()
                .get(resourceUrl+"/GetAll");
    }
    public Response getAll(int page, int amount) {
        return preparedRequest()
                .when()
                .queryParam("page", page)
                .queryParam("amount", amount)
                .get(resourceUrl + "/GetAll");
    }

    public Response getAll(String filter) {
        return preparedRequest()
                .when()
                .queryParam("Filter", filter)
                .get(resourceUrl + "/GetAll");
    }

    public Response getByIndex(int index) {
        return preparedRequest()
                .when()
                .pathParam("index", index)
                .get(resourceUrl + "/GetByIndex/{index}");
    }

    public Response createStreetcode(StreetcodeRequestBody requestBody){
        return preparedRequest()
                .when()
                .body(requestBody)
                .post(resourceUrl + "/Create");
    }

    public Response updateStreetcode(StreetcodeRequestBody requestBody){
        log.warn(baseAPIUrl + resourceUrl + "/Update");
        return preparedRequest()
                .when()
                .body(requestBody)
                .put(resourceUrl + "/Update");
    }

    public Response patchStage(int id, int status){
        return preparedRequest()
                .when()
                .pathParam("id", id)
                .pathParam("status", status)
                .put(resourceUrl + "/PatchStage/{id}/{status}");
    }

    public Response softDeleteStreetcode(int id) {
        return preparedRequest()
                .when()
                .pathParam("id", id)
                .delete(resourceUrl + "/SoftDelete/{id}");
    }

    public Response deleteStreetcode(int id) {
        return preparedRequest()
                .when()
                .pathParam("id", id)
                .delete(resourceUrl + "/Delete/{id}");
    }
}
