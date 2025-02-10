package com.historycode.api.models.img;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class ImageDetails {
    private int id;
    private String title;
    private String alt;
    private String imageId;
}
