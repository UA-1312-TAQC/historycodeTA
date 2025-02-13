package com.historycode.api.models.adminPanel.news;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
public class News {
    private int id;
    private String title;
    private String text;
    private String imageId;
    private String url;
    private NewsImage image;
    private Date creationDate;

    @Override
    public String toString() {
        return "\n\nNews{" +
                "id=" + id +
                ",\n title='" + title + '\'' +
                ",\n text='" + text + '\'' +
                ",\n imageId='" + imageId + '\'' +
                ",\n url='" + url + '\'' +
                ",\n image=" + image +
                ",\n creationDate=" + creationDate +
                '}';
    }
}
