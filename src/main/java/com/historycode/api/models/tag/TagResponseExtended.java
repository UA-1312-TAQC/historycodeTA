package com.historycode.api.models.tag;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TagResponseExtended {
    private int id;
    private Boolean isVisible;
    private int index;
    private String title;
}
