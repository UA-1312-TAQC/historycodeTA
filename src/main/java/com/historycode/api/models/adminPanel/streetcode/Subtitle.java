package com.historycode.api.models.adminPanel.streetcode;

import lombok.Data;

@Data
public class Subtitle {
    private String subtitleText;

    public Subtitle(String subtitleText) {
        this.subtitleText = subtitleText;
    }
}
