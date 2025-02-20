package com.historycode.api.models.partners;

import lombok.Data;

import java.util.List;

@Data
public class PartnerRequestBody {
    private boolean isKeyPartner;
    private boolean isVisibleEverywhere;
    private String title;
    private String description;
    //TO DO Change Target Url. Is must to be object ?
    private String targetUrl;
    private int logoId;
    private String urlTitle;
    private List<PartnerSourceLink> partnerSourceLinks;
    private List<PartnersStreetcodes> streetcodes;
}
