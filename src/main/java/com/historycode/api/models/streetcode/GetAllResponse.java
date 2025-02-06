package com.historycode.api.models.streetcode;

import lombok.Data;

import java.util.ArrayList;

@Data
public class GetAllResponse {
    private int totalAmount;
    private ArrayList<StreetcodeResponse> streetcodes;
}
