package com.historycode.api.models.adminPanel.news;

import lombok.Data;

@Data
public class NewsUpdateRequestBody {
    private String title;
    private String text;
    private int imageId;
    private String url;
    private String creationDate;
    private int id;


    public Integer getImageNull() {
        return imageId == 0 ? null : imageId;
    }
}
