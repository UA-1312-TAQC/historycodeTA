package com.historycode.api.models.Streetcode;

import lombok.Data;

import java.util.ArrayList;

@Data
public class GetAllResponse {
    public int totalAmount;

    @Override
    public String toString() {
        return "GetAllModel{" +
                "totalAmount=" + totalAmount +
                ", streetcodes=" + streetcodes +
                '}';
    }

    ArrayList<StreetcodeResponse> streetcodes;
}
