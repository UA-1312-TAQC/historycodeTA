package com.historycode.api.clients;

import com.historycode.api.models.img.ImageRequest;
import io.restassured.response.Response;

public class ImageClient extends BaseClient {

    protected String resourceUrl = "/Image";

    public ImageClient(String baseUrl) {
        super(baseUrl);
    }

    public Response getAll() {
        return preparedRequest()
                .when()
                .get(resourceUrl + "/GetAll");
    }

    public Response post(ImageRequest img) {
        return preparedRequest()
                .when()
                .body(img)
                .post(resourceUrl + "/Create");
    }

    public Response delete(int imgId) {
        return preparedRequest()
                .when()
                .delete(resourceUrl + "/Delete/"+ imgId );
    }
}
