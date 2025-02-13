package com.historycode.api.models.tag;


import lombok.Data;

@Data
public class TagResponseExtended {
    private String title;
    private int id;
    private boolean isVisible;
    private int index;
}
