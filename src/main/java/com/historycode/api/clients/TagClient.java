package com.historycode.api.clients;

import com.historycode.api.models.tag.TagRequestBody;
import io.restassured.response.Response;

public class TagClient extends BaseClient{
    protected String resourceUrl = "/Tag";
    public TagClient(String baseUrl) {
        super(baseUrl);
    }

    public Response getAll() {
        return preparedRequest()
                .when()
                .get(resourceUrl + "/GetAll");
    }
    public Response create(TagRequestBody newTag) {
        return preparedRequest()
                .body(newTag)
                .when()
                .post(resourceUrl + "/Create");
    }
    public Response delete(int tagId) {
        return preparedRequest()
                .when()
                .delete(resourceUrl + "/Delete/"+ tagId );
    }
}
