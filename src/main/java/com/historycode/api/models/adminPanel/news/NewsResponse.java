package com.historycode.api.models.adminPanel.news;

import lombok.Data;

@Data
public class NewsResponse {
    private int id;
    private String title;
    private String text;
    private int imageId;
    private String url;
    private String creationDate;
    private String image;
}
