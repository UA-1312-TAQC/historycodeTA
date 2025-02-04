package com.historycode.api.models.tag;

import lombok.Data;

import java.util.List;

@Data
public class GetAllTagResponse {
    int totalAmount;
    List<TagResponse> tags;
}
