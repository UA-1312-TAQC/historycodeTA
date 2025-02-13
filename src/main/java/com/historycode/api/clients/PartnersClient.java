package com.historycode.api.clients;

import com.historycode.api.models.partners.PartnerRequestBody;
import com.historycode.api.models.tag.TagRequestBody;
import io.restassured.response.Response;

public class PartnersClient extends BaseClient {
    protected String resourceUrl = "/Partners";

    public PartnersClient(String baseUrl) {
        super(baseUrl);
    }

    public Response getAll() {
        return preparedRequest()
                .when()
                .get(resourceUrl + "/GetAll");
    }

    public Response create(PartnerRequestBody partnerRequestBody) {
        return preparedRequest()
                .body(partnerRequestBody)
                .when()
                .post(resourceUrl + "/Create");
    }

    public Response update(PartnerRequestBody partnerRequestBody) {
        return preparedRequest()
                .body(partnerRequestBody)
                .when()
                .post(resourceUrl + "/Update");
    }
}
