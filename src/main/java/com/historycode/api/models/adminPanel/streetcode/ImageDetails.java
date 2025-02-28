package com.historycode.api.models.adminPanel.streetcode;

import lombok.Data;

@Data
public class ImageDetails {
    private int id;
    private int imageId;
    private String alt;

    public ImageDetails setId(int id) {
        this.id = id;
        return this;
    }

    public ImageDetails setImageId(int imageId) {
        this.imageId = imageId;
        return this;
    }

    public ImageDetails setAlt(String alt) {
        this.alt = alt;
        return this;
    }
}
