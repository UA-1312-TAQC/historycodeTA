package com.historycode.api.models.img;

import lombok.Data;

@Data
public class ImageApi {
    private int id;
    private String blobName;
    private String base64;
    private String mimeType;
    private ImageDetails imageDetails;

    @Override
    public String toString() {
        return "NewsImage{" +
                "id=" + id +
                ", blobName='" + blobName + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", imageDetails=" + imageDetails +
                '}';
    }
}
