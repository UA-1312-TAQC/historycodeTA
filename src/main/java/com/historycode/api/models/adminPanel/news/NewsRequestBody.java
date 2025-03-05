package com.historycode.api.models.adminPanel.news;

import lombok.Data;

@Data
public class NewsRequestBody {
    private int id;
    private String title;
    private String text;
    private int imageId;
    private String url;
    private String creationDate;

    public Integer getImageNull() {
        return imageId == 0 ? null : imageId;
    }
}

