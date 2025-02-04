package com.historycode.api.models.streetcode;

import com.historycode.api.models.tag.TagResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StreetcodeResponse {
    public int id;
    public int index;
    public String title;
    public String dateString;
    public String alias;
    public String transliterationUrl;
    public int status;
    public Date eventStartOrPersonBirthDate;
    public Date eventEndOrPersonDeathDate;
    public int viewCount;
    public Date createdAt;
    public Date updatedAt;
    public String createdBy;
    public List<TagResponse> tags;
    public String teaser;
    public int streetcodeType;
}
