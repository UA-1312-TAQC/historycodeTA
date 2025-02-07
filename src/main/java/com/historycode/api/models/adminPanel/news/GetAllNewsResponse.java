package com.historycode.api.models.adminPanel.news;


import lombok.Getter;

import java.util.List;

@Getter
public class GetAllNewsResponse {
    private int totalAmount;
    private List<News> news;

    @Override
    public String toString() {
        return "GetAllNewsResponse{" +
                "totalAmount=" + totalAmount +
                ", news=" + news +
                '}';
    }
}
