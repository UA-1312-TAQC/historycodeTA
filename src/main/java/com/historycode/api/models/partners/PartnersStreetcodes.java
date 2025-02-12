package com.historycode.api.models.partners;

import lombok.Data;

@Data
public class PartnersStreetcodes {
    private int id;
    private String title;

    public PartnersStreetcodes(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
