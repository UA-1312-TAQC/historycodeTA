package com.historycode.api.models.partners;

import java.util.List;

public class PartnersResponse {
    private int id;
    private boolean isKeyPartner;
    private boolean isVisibleEverywhere;
    private String title;
    private String description;
    private int logoId;
    private String targetUrl;
    private List<String> partnerSourceLinks;
    private List<String> streetcodes;
}
