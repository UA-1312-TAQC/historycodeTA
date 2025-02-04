package com.historycode.api.models.streetcode;

import com.historycode.api.models.tag.TagResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StreetcodeResponse {
    private int id;
    private int index;
    private String title;
    private String dateString;
    private String alias;
    private String transliterationUrl;
    private int status;
    private Date eventStartOrPersonBirthDate;
    private Date eventEndOrPersonDeathDate;
    private int viewCount;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private List<TagResponse> tags;
    private String teaser;
    private int streetcodeType;
}
