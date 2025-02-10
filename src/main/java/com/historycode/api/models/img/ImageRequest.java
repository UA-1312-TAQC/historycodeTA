package com.historycode.api.models.img;

import lombok.Data;

@Data
public class ImageRequest {
    private String title;
    private String baseFormat;
    private String mimeType;
    private String extension;
    private String alt;
}
