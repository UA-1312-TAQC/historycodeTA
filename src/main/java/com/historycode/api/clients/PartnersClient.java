package com.historycode.api.clients;

import com.historycode.api.models.partners.PartnerRequestBody;
import com.historycode.api.models.partners.PartnerUpdateRequest;
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

    public Response update(PartnerUpdateRequest partnerUpdateRequest) {
        return preparedRequest()
                .body(partnerUpdateRequest)
                .when()
                .put(resourceUrl + "/Update");
    }

    public Response delete(int id) {
        return preparedRequest()
                .when()
                .pathParam("id", id)
                .delete(resourceUrl + "/Delete/{id}");
    }
}
