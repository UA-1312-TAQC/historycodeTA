package com.historycode.api.models.adminPanel.news;

import lombok.Getter;
import lombok.ToString;

@Getter
public class NewsImage {
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
