package com.historycode.api.models.adminPanel.news;

import com.historycode.api.models.img.ImageApi;
import lombok.Getter;

import java.util.Date;

@Getter
public class News {
    private int id;
    private String title;
    private String text;
    private String imageId;
    private String url;
    private ImageApi imageApi;
    private Date creationDate;

    @Override
    public String toString() {
        return "\n\nNews{" +
                "id=" + id +
                ",\n title='" + title + '\'' +
                ",\n text='" + text + '\'' +
                ",\n imageId='" + imageId + '\'' +
                ",\n url='" + url + '\'' +
                ",\n image=" + imageApi +
                ",\n creationDate=" + creationDate +
                '}';
    }
}
