package com.historycode.api.models.adminPanel.news;

import lombok.Data;

@Data
public class NewsRequestBody {
    private String title;
    private String text;
    private int imageId;
    private String url;
    private String creationDate;
}