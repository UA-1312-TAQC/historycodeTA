package com.historycode.api.models.partners;

import lombok.Data;

@Data
public class PartnerSourceLink {
    private int id;
    private int logoType;
    private String targetUrl;

    public PartnerSourceLink(int id, int logoType, String targetUrl) {
        this.id = id;
        this.logoType = logoType;
        this.targetUrl = targetUrl;
    }
}
